package com.rsp.onboard.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsp.onboard.entity.Customer;
import com.rsp.onboard.repository.CustomerRepository;
import com.rsp.onboard.requests.RiskRequest;
import com.rsp.onboard.response.RiskResponse;
import com.rsp.onboard.response.RiskStatus;
import com.rsp.onboard.service.ComputeRiskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Risk API")
@RestController
@RequestMapping("api/risk")
public class RiskController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ComputeRiskService computeRiskService;

	@ApiOperation(value = "", notes = "Calculate risk status", response = RiskResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = RiskResponse.class),
			@ApiResponse(code = 400, message = "Request validation failure"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@RequestMapping(method = RequestMethod.POST, path = "/status", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> computeRiskStatus(@Valid @RequestBody RiskRequest riskRequest) {
		Customer customer = customerRepository.findByCustomerAbn(riskRequest.getCustomerAbn());

		if (customer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Customer " + riskRequest.getCustomerAbn() + "  not found");
		}

		RiskStatus riskStatus = computeRiskService.compute(riskRequest.getCreditScore(),
				riskRequest.getTotalAnnualCost());

		return ResponseEntity.ok(new RiskResponse()
				.customerAbn(riskRequest.getCustomerAbn())
				.riskStatus(riskStatus));
	}

}
