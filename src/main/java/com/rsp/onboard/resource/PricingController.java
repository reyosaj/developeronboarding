package com.rsp.onboard.resource;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsp.onboard.entity.Customer;
import com.rsp.onboard.entity.Product;
import com.rsp.onboard.repository.CustomerRepository;
import com.rsp.onboard.repository.ProductRepository;
import com.rsp.onboard.requests.PricingRequest;
import com.rsp.onboard.response.PricingResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Pricing API")
@RestController
@RequestMapping("api/pricing")
public class PricingController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@ApiOperation(value = "", notes = "Calculate pricing for products", response = PricingResponse.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success", response = PricingResponse.class),
			@ApiResponse(code = 400, message = "Request validation failure"),
			@ApiResponse(code = 404, message = "Customer not found") })
	@RequestMapping(method = RequestMethod.POST, path = "/compute", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> computePrice(@Valid @RequestBody PricingRequest pricingRequest) {

		Customer customer = customerRepository.findByCustomerAbn(pricingRequest.getCustomerAbn());

		if (customer == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
		}

		Map<Integer, Product> productMap = productRepository.findAllByIdIn(pricingRequest.getProductIds()).stream()
				.collect(Collectors.toMap(Product::getId, Function.identity()));

		List<Integer> unknownProductIds = pricingRequest.getProductIds().stream()
				.filter(id -> !productMap.containsKey(id)).collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(unknownProductIds)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknown_products :" + unknownProductIds);
		}

		try {
			Thread.sleep(600);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		float factor = pricingRequest.getExpectedAnnualTurnover() * .001f;

		Double totalPrice = pricingRequest.getProductIds()
				.stream().map(id -> productMap.get(id))
				.mapToDouble(p -> p.getPricePerYear() * factor).sum();

		return ResponseEntity.ok(new PricingResponse()
				.customerAbn(customer.getCustomerAbn())
				.expectedAnnualTurnover(pricingRequest.getExpectedAnnualTurnover())
				.productids(pricingRequest.getProductIds())
				.totalAnnualCost(totalPrice.floatValue()));
	}
}
