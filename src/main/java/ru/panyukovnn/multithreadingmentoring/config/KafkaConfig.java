package ru.panyukovnn.multithreadingmentoring.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

@Configuration
public class KafkaConfig {

    @Bean
    public EmbeddedKafkaBroker broker() {
        return new EmbeddedKafkaBroker(1)
            .kafkaPorts(9092)
            .brokerListProperty("spring.kafka.bootstrap-servers");
    }

    @Bean
    public NewTopic topicIn() {
        return TopicBuilder.name("topicIn").partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic topicOut() {
        return TopicBuilder.name("topicOut").partitions(1).replicas(1).build();
    }

}

