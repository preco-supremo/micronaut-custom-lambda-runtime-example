package com.example;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import io.micronaut.core.annotation.ReflectiveAccess;
import io.micronaut.function.aws.MicronautRequestHandler;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.Clock;

public class LambdaHandler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(LambdaHandler.class);

    @Inject
    @ReflectiveAccess
    private ProductRepository productRepository;

    @Override
    public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent input) {

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        response.setStatusCode(200);

        var clock = Clock.systemDefaultZone();
        var product = new Product("TV", new BigDecimal(999.90), clock);

        productRepository.save(product);

        var savedProducts = productRepository.findAll();

        assert savedProducts.size() == 1;
        savedProducts.forEach(System.out::println);

        return response;
    }
}
