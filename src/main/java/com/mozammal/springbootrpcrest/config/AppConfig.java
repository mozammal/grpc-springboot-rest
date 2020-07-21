package com.mozammal.springbootrpcrest.config;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.mozammal.proto.code.*;
import java.util.Arrays;

@Configuration
public class AppConfig {

    @Value("${grpc.host}")
    private String target;
    @Bean
    Channel channel() {
        return ManagedChannelBuilder.
                forTarget(target).
                usePlaintext().build();
    }

    @Bean
    EmployeeServiceGrpc.EmployeeServiceBlockingStub employeeServiceBlockingStub(Channel channel) {
        return EmployeeServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(Arrays.asList(hmc));
    }
}
