package br.java.social_network.infrastructure.providers;

import br.java.social_network.domain.post.Post;
import br.java.social_network.infrastructure.providers.interfaces.IEventProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EventProviderImpl implements IEventProvider {
    @Autowired
    private KafkaTemplate<Object, byte[]> _kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();
    @Value("${topic.name}")
    private String topic;

    @Override
    @Async
    public void send(String topic, Post event){
        try {
            _kafkaTemplate.send(topic, objectMapper.writeValueAsBytes(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @KafkaListener(topics = "${topic.name}", groupId = "ms-demo")
    public void consume(ConsumerRecord<String, String> event){
        System.out.println(event.value());
    }
}
