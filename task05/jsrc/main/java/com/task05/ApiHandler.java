package com.task05;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import com.google.gson.Gson;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;
import com.task05.model.Event;
import com.task05.model.EventDTO;
import com.task05.model.EventRequestFromAPIGateway;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "api_handler",
        roleName = "api_handler-role"
)
@DependsOn(resourceType = ResourceType.DYNAMODB_TABLE,
        name = "Events")
public class ApiHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private static final int SC_CREATED = 201;
    private static final int SC_BAD_REQUEST = 400;
    private final Gson gson = new Gson();
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        context.getLogger().log(request.toString());
        String body = request.getBody();
        EventRequestFromAPIGateway eventRequestFromAPIGateway = gson.fromJson(body, EventRequestFromAPIGateway.class);

        System.out.println(eventRequestFromAPIGateway);

        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBClientBuilder.
                standard().
                withRegion("eu-central-1").
                build();

        Event event = new Event(eventRequestFromAPIGateway.getPrincipalId(), eventRequestFromAPIGateway.getContent());

        System.out.println(event);
        try {
            Map<String, AttributeValue> item = new HashMap<>();
            item.put("id", new AttributeValue().withS(event.getId().toString()));
            item.put("principalId", new AttributeValue().withN(event.getPrincipalId().toString()));
            item.put("createdAt", new AttributeValue().withS(event.getCreatedAt()));
            item.put("body", new AttributeValue().withS(gson.toJson(event.getBody())));

            PutItemRequest requestToDB = new PutItemRequest()
                    .withTableName("cmtr-048d7043-Events-test")
                    .withItem(item);

            amazonDynamoDB.putItem(requestToDB);

            EventDTO eventDTO = new EventDTO(SC_CREATED, event);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(SC_CREATED)
                    .withBody(gson.toJson(eventDTO, EventDTO.class));
        } catch (IllegalArgumentException exception) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(SC_BAD_REQUEST)
                    .withBody("error");
        }
    }
}
