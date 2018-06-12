package com.rsp.onboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.rsp.onboard.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

	Customer findByCustomerAbn(String customerAbn);
}
