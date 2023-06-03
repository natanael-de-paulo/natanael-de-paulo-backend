package br.java.social_network.application.models.infra_interfaces;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IEventProvider {
    void send(String topic, String event);
    void consume(ConsumerRecord<String, String> event);
}
