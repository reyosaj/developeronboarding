package com.rsp.onboard.requests;

import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Compute Pricing Request")
public class PricingRequest {

	@Pattern(regexp = "^ABN?[0-9]*$", message = "Should be a valid ABN")
	private String customerAbn;
	
	@NotNull
	@NotEmpty(message = "ProductId cannot be empty")
	private List<Integer> productIds;
	
	@NotNull
	@DecimalMin(value = "1.00", message = "Value is too low")
	private Float expectedAnnualTurnover;
}
