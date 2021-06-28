package br.com.devschool.collaboratorcore.infrastructure.config;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;


@Configuration
public class AWSConfig {

    @Value("${camel.component.aws2-sqs.proxy-host}")
    private  String sqsHost;

    @Value("${camel.component.aws2-sqs.proxy-port}")
    private  Integer sqsPort;

    @Value("${camel.component.aws2-sqs.region}")
    private String sqsRegion;

//    @Bean
//    public ProducerTemplate producerTemplate() {
//        return new ProducerTemplate();
//    }

    @Bean("sqsClient")
    public AmazonSQS amazonSQS(){
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProxyHost(sqsHost);
        clientConfiguration.setProxyPort(sqsPort);
        clientConfiguration.setProtocol(Protocol.HTTP);

        return AmazonSQSClientBuilder.standard().withRegion(sqsRegion).withClientConfiguration(clientConfiguration).build();
    }

}
