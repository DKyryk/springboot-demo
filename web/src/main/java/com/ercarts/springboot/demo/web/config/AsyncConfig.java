package com.ercarts.springboot.demo.web.config;

import com.ercarts.springboot.demo.web.async.RoomListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dkyryk
 */
@Configuration
public class AsyncConfig {
    private static final String QUEUE_NAME = "room-queue";
    private static final String EXCHANGE_NAME = "queries";

    //because boolean method arguments are evil
    private static final boolean NON_DURABLE_QUEUE = false;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, NON_DURABLE_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("rooms.key ");
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RoomListener roomListener) {
        return new MessageListenerAdapter(roomListener, "receiveMessage");
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory,
                                                            MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setMessageListener(listenerAdapter);
        listenerContainer.setQueueNames(QUEUE_NAME);
        return listenerContainer;
    }
}