package io.github.natanaeldepaulo.api.infrastructure.providers;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface IEventProvider {
    void send(String topic, String event);
    void consume(ConsumerRecord<String, String> event);
}
