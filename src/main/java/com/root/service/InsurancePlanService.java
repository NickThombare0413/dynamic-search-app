package com.root.service;

import java.util.List;

import com.root.binding.request.InsurancePlanRequest;
import com.root.binding.response.InsurancePlanResponse;


public interface InsurancePlanService {
	
	public List<InsurancePlanResponse> searchPlans(InsurancePlanRequest request);
	public List<String> getUniquePlanNames();
	public List<String> getUniquePlanStatus();
	public  List<InsurancePlanResponse> findAllPlans();
}
