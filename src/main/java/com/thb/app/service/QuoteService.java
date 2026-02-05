package com.thb.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.Random;

@Service
public class QuoteService {

    private final DynamoDbClient dynamoDbClient;
    private final String tableName;
    private final Random random = new Random();

    public QuoteService(
            DynamoDbClient dynamoDbClient,
            @Value("${aws.dynamodb.table-name}") String tableName
    ) {
        this.dynamoDbClient = dynamoDbClient;
        this.tableName = tableName;
    }

    public String getRandomQuote() {

        ScanRequest request = ScanRequest.builder()
                .tableName(tableName)
                .build();

        var items = dynamoDbClient.scan(request).items();

        if (items.isEmpty()) {
            return "Kein Spruch vorhanden";
        }

        int index = random.nextInt(items.size());
        return items.get(index).get("quote").s();
    }
}
