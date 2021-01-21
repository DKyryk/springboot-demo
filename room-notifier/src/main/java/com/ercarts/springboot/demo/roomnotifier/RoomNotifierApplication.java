package com.ercarts.springboot.demo.roomnotifier;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.ercarts.springboot.demo.roomnotifier.QueueParams.EXCHANGE_NAME;
import static com.ercarts.springboot.demo.roomnotifier.QueueParams.QUEUE_NAME;
import static com.ercarts.springboot.demo.roomnotifier.QueueParams.ROUTING_KEY;

@SpringBootApplication
public class RoomNotifierApplication {
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
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

	public static void main(String[] args) {
		SpringApplication.run(RoomNotifierApplication.class, args);
	}

}