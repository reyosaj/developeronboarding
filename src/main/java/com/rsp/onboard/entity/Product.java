package com.rsp.onboard.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
	@Id
	private Integer id;
	private String productName;
	private Float pricePerYear;

}
