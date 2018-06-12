package com.rsp.onboard.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditResponse {

	private String customerAbn;
	private Date startDate;
	private Date lastUpdateDate;
	private String status;
	private String postcode;
	private String entityname;
	private Integer creditScore;

	public CreditResponse customerAbn(String customerAbn) {
		this.customerAbn = customerAbn;
		return this;
	}

	public CreditResponse startDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}

	public CreditResponse lLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
		return this;
	}

	public CreditResponse status(String status) {
		this.status = status;
		return this;
	}

	public CreditResponse postcode(String postcode) {
		this.postcode = postcode;
		return this;
	}

	public CreditResponse entityname(String entityname) {
		this.entityname = entityname;
		return this;
	}

	public CreditResponse creditScore(Integer creditScore) {
		this.creditScore = creditScore;
		return this;
	}

}
