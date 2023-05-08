package io.github.natanaeldepaulo.api.infrastructure.providers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProviderImpl implements IEventProvider {

    @Autowired
    private KafkaTemplate<String, String> _kafkaTemplate;

    @Value("${topic.name}")
    private String topic;

    @Override
    public void send(String topic, String event){
        _kafkaTemplate.send(topic, event);
    }

    @Override
    @KafkaListener(topics = "${topic.name}", groupId = "ms-demo")
    public void consume(ConsumerRecord<String, String> event){
        System.out.println(event.value());
    }

}
