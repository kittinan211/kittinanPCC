package com.pccth.pccsdmgrservice.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pccth.pccsdmgrservice.model.news;

import java.util.List;



public interface newsRepo extends JpaRepository<news, Long> {
	
	@Query(value = "SELECT * FROM employeemanager.news;", nativeQuery = true)
    List<news> allnews();
}
