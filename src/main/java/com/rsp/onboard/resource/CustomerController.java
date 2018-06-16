package com.rsp.onboard.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsp.onboard.entity.Customer;
import com.rsp.onboard.repository.CustomerRepository;
import com.rsp.onboard.response.CustomerResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Customer API")
@RestController
@RequestMapping("api/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@ApiOperation(value = "", notes = "Get customer by ABN", response = CustomerResponse.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = CustomerResponse.class),
			@ApiResponse(code = 400, message = "Request validation failure"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@RequestMapping(method = RequestMethod.GET, path = "/abn/{customerabn}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getCustomerByAbn(@PathVariable("customerabn") String customerAbn,
			@RequestHeader("X-Authorization") String authHeader) {
		Customer customer = customerRepository.findByCustomerAbn(customerAbn);

		if (customer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}

		return ResponseEntity.ok(new CustomerResponse()
				.id(customer.getId())
				.customerAbn(customer.getCustomerAbn())
				.startDate(customer.getStartDate())
				.lastUpdateDate(customer.getLastUpdateDate())
				.entityName(customer.getEntityName())
				.postcode(customer.getPostcode())
				.status(customer.getStatus()));
	}

}
