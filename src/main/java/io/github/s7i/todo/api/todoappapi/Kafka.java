package io.github.s7i.todo.api.todoappapi;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ConfigurationProperties("kafka")
@Data
public class Kafka {

    @Data
    public static class Entry {
        String key;
        Object value;
    }

    List<Entry> properties;

    public Map<String, Object> toMap() {
        return properties.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    }
}
