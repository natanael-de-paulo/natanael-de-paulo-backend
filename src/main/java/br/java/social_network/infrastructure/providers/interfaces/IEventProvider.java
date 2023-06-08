package br.java.social_network.infrastructure.providers.interfaces;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IEventProvider {
    void send(String topic, String event);
    void consume(ConsumerRecord<String, String> event);
}
