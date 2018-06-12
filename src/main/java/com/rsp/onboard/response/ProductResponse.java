package com.rsp.onboard.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

	private Integer id;
	private String productName;
	private Float fixedAnnualPrice;

	public ProductResponse id(Integer id) {
		this.id = id;
		return this;
	}

	public ProductResponse productName(String productName) {
		this.productName = productName;
		return this;
	}

	public ProductResponse fixedAnnualPrice(Float fixedAnnualPrice) {
		this.fixedAnnualPrice = fixedAnnualPrice;
		return this;
	}

}
