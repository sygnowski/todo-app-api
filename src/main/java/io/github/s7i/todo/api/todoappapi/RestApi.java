package io.github.s7i.todo.api.todoappapi;

import io.github.s7i.todo.api.todoappapi.domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RestApi {


    @Autowired
    KafkaTemplate<String, Action> actionKafkaTemplate;
    @Value("${action.topic}")
    String actionTopic;


    @PostMapping(value = "/action")
    public Mono<Status> addAction(@RequestBody Action action) {

        actionKafkaTemplate.send(actionTopic, action);

        return Mono.just(Status.builder().message("ok").build());

    }

}
