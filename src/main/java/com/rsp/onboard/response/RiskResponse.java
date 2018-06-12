package com.rsp.onboard.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RiskResponse {
	private String customerAbn;
	private Integer riskScore;
	private RiskStatus riskStatus;

	public RiskResponse customerAbn(String customerAbn) {
		this.customerAbn = customerAbn;
		return this;
	}

	public RiskResponse riskScore(Integer riskScore) {
		this.riskScore = riskScore;
		return this;
	}

	public RiskResponse riskStatus(RiskStatus riskStatus) {
		this.riskStatus = riskStatus;
		return this;
	}

}
