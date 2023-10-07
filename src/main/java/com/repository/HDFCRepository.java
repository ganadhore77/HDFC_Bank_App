package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.HDFC_Entity;

@Repository
public interface HDFCRepository extends MongoRepository<HDFC_Entity , String> {
	
	@Query("{'account_number': ?0}")
	HDFC_Entity findByAccountNumber(String accountNumber);
}
