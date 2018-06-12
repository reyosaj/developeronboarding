package com.rsp.onboard.service;

import com.rsp.onboard.response.RiskStatus;

public interface ComputeRiskService {
	
	RiskStatus compute(Integer creditScore, Integer totalAnnualCost);

}
