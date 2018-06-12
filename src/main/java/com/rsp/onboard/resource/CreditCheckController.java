package com.rsp.onboard.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsp.onboard.entity.Customer;
import com.rsp.onboard.repository.CustomerRepository;
import com.rsp.onboard.response.CreditResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Credit API")
@RestController
@RequestMapping("api/creditscore")
public class CreditCheckController {

	@Autowired
	private CustomerRepository customerRepository;

	@ApiOperation(value = "", notes = "Do credit check", response = CreditResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = CreditResponse.class),
			@ApiResponse(code = 400, message = "Request validation failure"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@RequestMapping(method = RequestMethod.GET, path = "/abn/{customerabn}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> doCreditCheck(@PathVariable("customerabn") String customerAbn) {
		try {
			Thread.sleep(600);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		Customer customer = customerRepository.findByCustomerAbn(customerAbn);

		if (customer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}

		return ResponseEntity.ok(new CreditResponse()
				.creditScore(customer.getCreditScore())
				.customerAbn(customer.getCustomerAbn())
				.entityname(customer.getEntityName())
				.startDate(customer.getStartDate())
				.lLastUpdateDate(customer.getLastUpdateDate())
				.postcode(customer.getPostcode())
				.status(customer.getStatus()));
		// @formatter:on

	}
}
