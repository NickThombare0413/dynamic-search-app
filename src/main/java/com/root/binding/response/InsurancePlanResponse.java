package com.root.binding.response;

import lombok.Data;

@Data
public class InsurancePlanResponse {

	private Integer planId;
	private String planName;
	private String planHolderName;
	private Long planHolderSsn;
	private String planStatus;
}
