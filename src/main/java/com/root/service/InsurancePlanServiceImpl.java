package com.root.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.root.binding.request.InsurancePlanRequest;
import com.root.binding.response.InsurancePlanResponse;
import com.root.entity.InsurancePlanEntity;
import com.root.repository.InsurancePlanRepository;

@Service
public class InsurancePlanServiceImpl implements InsurancePlanService {

	@Autowired
	private InsurancePlanRepository repo;

	@Override
	public List<InsurancePlanResponse> searchPlans(InsurancePlanRequest request) {
		InsurancePlanEntity entity = new InsurancePlanEntity();

		if (request !=null && request.getPlanName() != null && request.getPlanName() != "") {
			entity.setPlanName(request.getPlanName());
		}
		if (request !=null && request.getPlanStatus() != null && request.getPlanStatus() != "") {
			entity.setPlanStatus(request.getPlanStatus());
		}

		Example<InsurancePlanEntity> of = Example.of(entity);
		List<InsurancePlanEntity> entityPlanList = repo.findAll(of);

		List<InsurancePlanResponse> planlist = new ArrayList<InsurancePlanResponse>();

		for (InsurancePlanEntity ipentity : entityPlanList) {
			InsurancePlanResponse responseEntity = new InsurancePlanResponse();
			BeanUtils.copyProperties(ipentity, responseEntity);
			planlist.add(responseEntity);
		}

		return planlist;
	}

	@Override
	public List<String> getUniquePlanNames() {
		return repo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		return repo.getPlanStatus();
	}

	@Override
	public List<InsurancePlanResponse> findAllPlans() {
	 repo.findAll();
	 List<InsurancePlanEntity> entityPlanList = repo.findAll();

		List<InsurancePlanResponse> planlist = new ArrayList<InsurancePlanResponse>();

		for (InsurancePlanEntity ipentity : entityPlanList) {
			InsurancePlanResponse responseEntity = new InsurancePlanResponse();
			BeanUtils.copyProperties(ipentity, responseEntity);
			planlist.add(responseEntity);
		}

		return planlist;
		
	}

}
