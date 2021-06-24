package br.com.devschool.collaboratorcore.infrastructure.config;


import org.apache.camel.component.aws2.sqs.Sqs2Component;
import org.apache.camel.component.aws2.sqs.Sqs2Configuration;
import org.apache.camel.component.aws2.sqs.client.Sqs2ClientFactory;
import org.apache.camel.component.aws2.sqs.client.Sqs2InternalClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.core.Protocol;
import software.amazon.awssdk.services.sqs.SqsClient;

import javax.swing.plaf.synth.Region;

@Configuration
public class AWSConfig {

    @Value("${camel.component.aws2-sqs.proxy-host}")
    private  String sqsHost;

    @Value("${camel.component.aws2-sqs.proxy-port}")
    private  Integer sqsPort;

    @Value("${camel.component.aws2-sqs.region}")
    private String sqsRegion;

    @Value("${camel.component.aws2-sqs.protocol}")
    private String protocol;

    @Bean("sqsClient")
    public SqsClient createSQSClient(){
        Sqs2Configuration clientConfiguration = new Sqs2Configuration();
        clientConfiguration.setProxyHost(sqsHost);
        clientConfiguration.setProxyPort(sqsPort);
        clientConfiguration.setProtocol(protocol);
        clientConfiguration.setProxyProtocol(Protocol.HTTP);
        clientConfiguration.setRegion(sqsRegion);

        Sqs2Component sqs2Component = new Sqs2Component();
        sqs2Component.setConfiguration(clientConfiguration);

        Sqs2InternalClient internalClientConfiguration = Sqs2ClientFactory.getSqsClient(sqs2Component.getConfiguration());
        SqsClient sqsClient = internalClientConfiguration.getSQSClient();

        return sqsClient;
    }

}
