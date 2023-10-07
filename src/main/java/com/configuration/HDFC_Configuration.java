package com.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HDFC_Configuration {

	/* ------- Queues ------*/
	@Bean
	public Queue axis_queue() {
		return new Queue("axis_queue");
	}
	
	@Bean
	public Queue hdfc_queue() {
		return new Queue("hdfc_queue");
	}
	
	@Bean
	public Queue icici_queue() {
		return new Queue("icici_queue");
	}
	
	@Bean
	public Queue sbi_queue() {
		return new Queue("sbi_queue");
	}

	@Bean
	public Queue all() {
		return new Queue("queue_all");
	}
	
	/* ------- Exchanges ------*/
	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange("fanout");
	}
	
	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange("TExchange");
	}

	/*----- Binding between Queue and Exchange using Routing Key ---- */
	@Bean
	public Binding axisBinding() {
		return BindingBuilder.bind(axis_queue()).to(topicExchange()).with("axis");
//		return BindingBuilder.bind(queue1()).to(fanoutExchange());
	}
	
	@Bean
	public Binding hdfcBinding() {
		return BindingBuilder.bind(hdfc_queue()).to(topicExchange()).with("hdfc");
	}
	
	@Bean
	public Binding iciciBinding() {
		return BindingBuilder.bind(icici_queue()).to(topicExchange()).with("icici");
//		return BindingBuilder.bind(queue3()).to(fanoutExchange());
	}
	
	@Bean
	public Binding sbiBinding() {
		return BindingBuilder.bind(sbi_queue()).to(topicExchange()).with("sbi");
	}
	
	@Bean
	public Binding allBinding() {
		return BindingBuilder.bind(all()).to(topicExchange()).with("producer.*");
	}
	
	
	
	@Bean
	public Jackson2JsonMessageConverter msgConverter() {
		return new Jackson2JsonMessageConverter();
	}
			
	@Bean
	public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate rabbitTemplate){
		return new AsyncRabbitTemplate(rabbitTemplate);
	}
	
}
