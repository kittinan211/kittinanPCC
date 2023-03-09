package com.pccth.pccsdmgrservice.service;

import com.pccth.jwt.JwtGenerator;
import com.pccth.pccsdmgrservice.exception.UserNotFoundException;
import com.pccth.pccsdmgrservice.model.Employee;
import com.pccth.pccsdmgrservice.model.news;
import com.pccth.pccsdmgrservice.model.newsResponse;
import com.pccth.pccsdmgrservice.model.newspdf;
import com.pccth.pccsdmgrservice.repo.EmployeeRepo;
import com.pccth.pccsdmgrservice.repo.newsPDFRepo;
import com.pccth.pccsdmgrservice.repo.newsRepo;

import dev.samstevens.totp.secret.SecretGenerator;
import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final JwtGenerator jwtGenerator;
    private final newsRepo NewsRepo;
    private final newsPDFRepo NewsPDFRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo , newsRepo NewsRepo, newsPDFRepo NewsPDFRepo) {
        this.employeeRepo = employeeRepo;
		this.jwtGenerator = new JwtGenerator();
		this.NewsRepo = NewsRepo;
		this.NewsPDFRepo = NewsPDFRepo;
    }
    
    @Autowired
	private SecretGenerator secretGenerator;

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        String pw = jwtGenerator.generatePasswordToken(employee.getPassword());
        employee.setPassword(pw);
        
        if (employee.isUsing2FA() == true) {
        	employee.setUsing2FA(true);
        	employee.setSecret(secretGenerator.generate());
		}
        
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    
    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
    
    public news addnews(news News) {
    	Date d = new Date();
    	News.setDatenews(d);
        return NewsRepo.save(News);
    }
    
    public List<newsResponse> findAllNews() {    	
    	List<newsResponse> ListNewsResponse = new ArrayList<>();;
    	
    	for(news n : NewsRepo.findAll()) {		
    		newsResponse NewsResponse = new newsResponse();
    		NewsResponse.setNewsid(n.getNewsid());
    		NewsResponse.setNewsname(n.getNewsname());
    		NewsResponse.setTextnews(n.getTextnews());
    		NewsResponse.setImgurl(n.getImgurl());
    		
    		SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
    		String stringDate= DateFor.format(n.getDatenews());
    		NewsResponse.setDatenews(stringDate);
    		
    		ListNewsResponse.add(NewsResponse);
    	}
    	
        return ListNewsResponse;
    }
    
    public newspdf addnewspdf(newspdf News) {
        return NewsPDFRepo.save(News);
    }
    
    public List<String> listnewspdf(String id) {
        return NewsPDFRepo.listnews(id);
    }


    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
    
    public Employee loginEmployee(String username,String password) {	
        return employeeRepo.loginEmployee(username);
    }
    
    public Claims loginEmployeeT(String token) {	
        return jwtGenerator.decodeJWT(token);
    }
}
