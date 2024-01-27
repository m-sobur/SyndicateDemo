package com.task04;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.events.SqsTriggerEventSource;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;

import java.util.HashMap;
import java.util.Map;

@LambdaHandler(lambdaName = "sqs_handler",
        roleName = "sqs_handler-role",
        timeout = 25
)
@SqsTriggerEventSource(batchSize = 1,
        targetQueue = "async_queue"
)
@DependsOn(resourceType = ResourceType.SQS_QUEUE,
        name = "async_queue")
public class SqsHandler implements RequestHandler<Object, Map<String, Object>> {

    public Map<String, Object> handleRequest(Object request, Context context) {
        System.out.println("Hello from lambda");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", 200);
        resultMap.put("body", "Hello from Lambda");
        return resultMap;
    }
}
