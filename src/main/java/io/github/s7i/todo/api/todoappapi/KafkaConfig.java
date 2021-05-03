package io.github.s7i.todo.api.todoappapi;

import io.github.s7i.todo.api.todoappapi.domain.Action;
import io.github.s7i.todo.api.todoappapi.domain.Reaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
@EnableConfigurationProperties(Kafka.class)
public class KafkaConfig {


    @Autowired
    Kafka kafka;


    @Bean
    ProducerFactory<String, Action> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafka.toMap());
    }

    @Bean
    KafkaTemplate<String, Action> actionTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Reaction> reactionFactory(ConsumerFactory<String, Reaction> consumerFactory) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Reaction>();
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }


}