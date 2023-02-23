package com.pccth.pccsdmgrservice.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pccth.pccsdmgrservice.model.Employee;
import com.pccth.pccsdmgrservice.model.newspdf;

import java.util.List;
import java.util.Optional;
public interface newsPDFRepo extends JpaRepository<newspdf, Long>{
	
    @Query(value = "SELECT namepdf FROM employeemanager.newspdf where news_newsid = ?", nativeQuery = true)
    List<String> listnews(String id);
}
