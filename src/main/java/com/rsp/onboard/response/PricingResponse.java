package com.rsp.onboard.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricingResponse {
	private String customerAbn;
	private List<Integer> productids;
	private Float expectedAnnualTurnover;
	private Float totalAnnualCost;

	public PricingResponse customerAbn(String customerAbn) {
		this.customerAbn = customerAbn;
		return this;
	}

	public PricingResponse productids(List<Integer> productids) {
		this.productids = productids;
		return this;
	}

	public PricingResponse expectedAnnualTurnover(Float expectedAnnualTurnover) {
		this.expectedAnnualTurnover = expectedAnnualTurnover;
		return this;
	}

	public PricingResponse totalAnnualCost(Float totalAnnualCost) {
		this.totalAnnualCost = totalAnnualCost;
		return this;
	}

}
