package com.rsp.onboard.service;

import org.springframework.stereotype.Service;

import com.rsp.onboard.response.RiskStatus;

@Service
public class ComputeRiskServiceImpl implements ComputeRiskService {

	@Override
	public RiskStatus compute(Integer creditScore, Integer totalAnnualCost) {
		if (creditScore < 500) {
			if (totalAnnualCost <= 500) {
				return RiskStatus.LOW;
			} else if (creditScore <= 1000) {
				return RiskStatus.MEDIUM;
			} else {
				return RiskStatus.HIGH;
			}
		} else if (creditScore < 650) {
			if (totalAnnualCost <= 15000) {
				return RiskStatus.LOW;
			} else if (creditScore <= 25000) {
				return RiskStatus.MEDIUM;
			} else {
				return RiskStatus.HIGH;
			}
		}
		if (creditScore <= 780) {
			if (totalAnnualCost <= 50000) {
				return RiskStatus.LOW;
			} else if (creditScore <= 100000) {
				return RiskStatus.MEDIUM;
			} else {
				return RiskStatus.HIGH;
			}
		} else {
			if (totalAnnualCost <= 100000) {
				return RiskStatus.LOW;
			} else if (creditScore <= 1000000) {
				return RiskStatus.MEDIUM;
			} else {
				return RiskStatus.HIGH;
			}
		}
	}

}
