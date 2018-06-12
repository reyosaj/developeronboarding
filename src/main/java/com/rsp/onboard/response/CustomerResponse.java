package com.rsp.onboard.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
	private Integer id;
	private String customerAbn;
	private Date startDate;
	private Date lastUpdateDate;
	private String status;
	private String entityName;
	private String postcode;

	public CustomerResponse id(Integer id) {
		this.id = id;
		return this;
	}

	public CustomerResponse customerAbn(String customerAbn) {
		this.customerAbn = customerAbn;
		return this;
	}

	public CustomerResponse startDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}

	public CustomerResponse lastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
		return this;
	}

	public CustomerResponse status(String status) {
		this.status = status;
		return this;
	}

	public CustomerResponse entityName(String entityName) {
		this.entityName = entityName;
		return this;
	}

	public CustomerResponse postcode(String postcode) {
		this.postcode = postcode;
		return this;
	}

}
