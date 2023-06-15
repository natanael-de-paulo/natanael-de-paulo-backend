package br.java.social_network.infrastructure.providers.interfaces;

import br.java.social_network.domain.post.Post;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IEventProvider {
    void send(String topic, Post event);
    void consume(ConsumerRecord<String, String> event);
}
