package com.rsp.onboard.requests;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "Risk computation request")
public class RiskRequest {
	
	@Pattern(regexp = "^ABN?[0-9]*$", message = "Should be a valid ABN")
	private String customerAbn;
	
	@NotBlank
	private String creditStatus;
	
	@DecimalMin(value = "1", message = "Value is too small")
	@DecimalMax(value = "999", message = "Value is too big")
	private Integer creditScore;
	
	@NotNull
	@DecimalMin(value = "1.00", message = "Value is too small")
	private Float expectedAnnualTurnover;
	
	@NotNull
	@DecimalMin(value = "1", message = "Value is too low")
	private Integer totalAnnualCost;

}
