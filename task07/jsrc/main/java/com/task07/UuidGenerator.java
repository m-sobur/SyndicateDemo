package com.task07;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syndicate.deployment.annotations.events.RuleEventSource;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.Dependencies;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;
import com.task07.model.Ids;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@LambdaHandler(lambdaName = "uuid_generator",
        roleName = "uuid_generator-role"
)
@RuleEventSource(targetRule = "uuid_trigger")
@Dependencies({
        @DependsOn(resourceType = ResourceType.S3_BUCKET, name = "uuid-storage"),
        @DependsOn(resourceType = ResourceType.CLOUDWATCH_RULE, name = "uuid_trigger")
})
public class UuidGenerator implements RequestHandler<ScheduledEvent, Void> {
    private final AmazonS3 s3Client;
    private final Integer numberOfRandomGeneratedIds = 10;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UuidGenerator() {
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("eu-central-1")
                .build();
    }

    public Void handleRequest(ScheduledEvent cloudWatchRuleEvent, Context context) {
        System.out.println("Entry of the handleRequest method");
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < numberOfRandomGeneratedIds; i++) {
            ids.add(java.util.UUID.randomUUID().toString());
        }
        Ids idsContainer = new Ids(ids);

        String fileName = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);

        System.out.println("Putting object into s3: "+ idsContainer);
        s3Client.putObject("cmtr-048d7043-uuid-storage-test", fileName, parseIdContainerToJson(idsContainer));
        return null;
    }

    private String parseIdContainerToJson(Ids idContainer) {
        try {
            return objectMapper.writeValueAsString(idContainer) ;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
