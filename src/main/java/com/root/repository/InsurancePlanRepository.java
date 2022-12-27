package com.root.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.root.entity.InsurancePlanEntity;

@Repository
public interface InsurancePlanRepository extends JpaRepository<InsurancePlanEntity, Integer>
{

	@Query("select distinct planName from InsurancePlanEntity")
	public List<String> getPlanNames();
	
	@Query("select distinct planStatus from InsurancePlanEntity")
	public List<String> getPlanStatus();
}
