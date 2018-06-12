package com.rsp.onboard.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rsp.onboard.entity.Product;



public interface ProductRepository extends CrudRepository<Product, Long>{
	
	List<Product> findAllByIdIn(List<Integer> ids);

}
