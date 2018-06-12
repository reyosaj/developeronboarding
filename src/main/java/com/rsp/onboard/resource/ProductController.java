package com.rsp.onboard.resource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsp.onboard.repository.ProductRepository;
import com.rsp.onboard.response.ProductResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Products API")
@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@ApiOperation(value = "", notes = "Get all products", response = ProductResponse.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = ProductResponse.class, responseContainer = "List") })
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> getAllProducts() {

		List<ProductResponse> products = StreamSupport.stream(productRepository.findAll().spliterator(), false)
				.map(p ->  new ProductResponse()
						.id(p.getId())
						.productName(p.getProductName())
						.fixedAnnualPrice(p.getPricePerYear()))
				.collect(Collectors.toList());

		return ResponseEntity.ok(products);

	}
}
