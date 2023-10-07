package com.producer;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.RabbitConverterFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.entity.HDFC_Entity;
import com.mongodb.client.result.UpdateResult;
import com.repository.HDFCRepository;
import com.request.HDFCRequest;

@Service
public class HDFCProducer {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private HDFCRepository repository;

	@Autowired
	private TopicExchange tExchange;

	@Autowired
	public AsyncRabbitTemplate rabbitTemplate;

	public void send_to_axis(HDFCRequest msg) {

		String accountNumber = msg.getYour_bank_account_number();

		HDFC_Entity findByAccountNumber = repository.findByAccountNumber(accountNumber);
		Double balance = findByAccountNumber.getBalance();

		Double final_balance = (balance - msg.getBalance());

		Query query = new Query(Criteria.where("account_number").is(accountNumber));
		Update update = new Update().set("balance", final_balance);

		UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, HDFC_Entity.class);
		System.out.println(updateFirst);

		RabbitConverterFuture<String> receive = rabbitTemplate.convertSendAndReceive(tExchange.getName(), "axis", msg);

		receive.whenComplete((receivedMsg, exception) -> {
			if (exception != null) {
				System.out.println(exception);
			} else {
				System.out.println(receivedMsg);
			}
		});
	}

	public void send_to_icici(HDFCRequest msg) {
		
		String accountNumber = msg.getYour_bank_account_number();

		HDFC_Entity findByAccountNumber = repository.findByAccountNumber(accountNumber);
		Double balance = findByAccountNumber.getBalance();

		Double final_balance = (balance - msg.getBalance());

		Query query = new Query(Criteria.where("account_number").is(accountNumber));
		Update update = new Update().set("balance", final_balance);

		UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, HDFC_Entity.class);
		System.out.println(updateFirst);
		
		RabbitConverterFuture<String> receive = rabbitTemplate.convertSendAndReceive(tExchange.getName(), "icici", msg);

		receive.whenComplete((receivedMsg, exception) -> {
			if (exception != null) {
				System.out.println(exception);
			} else {
				System.out.println(receivedMsg);
			}
		});
	}

	public void send_to_sbi(HDFCRequest msg) {
		
		String accountNumber = msg.getYour_bank_account_number();

		HDFC_Entity findByAccountNumber = repository.findByAccountNumber(accountNumber);
		Double balance = findByAccountNumber.getBalance();

		Double final_balance = (balance - msg.getBalance());

		Query query = new Query(Criteria.where("account_number").is(accountNumber));
		Update update = new Update().set("balance", final_balance);

		UpdateResult updateFirst = mongoTemplate.updateFirst(query, update, HDFC_Entity.class);
		System.out.println(updateFirst);
		
		RabbitConverterFuture<String> receive = rabbitTemplate.convertSendAndReceive(tExchange.getName(), "sbi", msg);

		receive.whenComplete((receivedMsg, exception) -> {
			if (exception != null) {
				System.out.println(exception);
			} else {
				System.out.println(receivedMsg);
			}
		});
	}

}
