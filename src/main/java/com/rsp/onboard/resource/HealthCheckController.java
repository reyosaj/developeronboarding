package com.rsp.onboard.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("healthcheck")
@ApiIgnore
public class HealthCheckController {

	@RequestMapping()
	public ResponseEntity<?> getHealthStatus(){
		return ResponseEntity.ok().build();
	}
}
