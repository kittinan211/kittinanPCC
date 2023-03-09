package com.pccth.pccsdmgrservice.resource;


import com.pccth.jwt.AuthResponse;
import com.pccth.jwt.JwtGenerator;
import com.pccth.pccsdmgrservice.model.ApiResponse;
import com.pccth.pccsdmgrservice.model.Employee;
import com.pccth.pccsdmgrservice.model.SignUpResponse;
import com.pccth.pccsdmgrservice.model.loginEmployee;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.pccth.pccsdmgrservice.service.EmployeeService;

import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.code.CodeVerifier;

import io.jsonwebtoken.Claims;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;
    private final JwtGenerator jwtGenerator;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.jwtGenerator = new JwtGenerator();
    }
    
	@Autowired
	private QrDataFactory qrDataFactory;

	@Autowired
	private QrGenerator qrGenerator;
	
	@Autowired
	private CodeVerifier verifier;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
    
    		try {
    			Employee newEmployee = employeeService.addEmployee(employee);
    			if (employee.isUsing2FA() == true) {
    	    		QrData data = qrDataFactory.newBuilder().label(newEmployee.getUsername()).secret(newEmployee.getSecret()).issuer("JavaChinna").build();
				    String qrCodeImage = getDataUriForImage(qrGenerator.generate(data), qrGenerator.getImageMimeType());
				    return ResponseEntity.ok().body(new SignUpResponse(true, qrCodeImage));
    			}
    			}catch(Exception e){
    				
    			}
    	
    		return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody loginEmployee employee) {
    	Employee loginEmployee = employeeService.loginEmployee(employee.getUsername(), employee.getPassword());
 
    	AuthResponse AR = null;
        if(loginEmployee != null) {
        	Claims passwordjwt = jwtGenerator.decodeJWT(loginEmployee.getPassword()); 
            
              var arr = passwordjwt.toString().split("=");
              var arr1 = arr[1].split("}");
              
        	if(arr1[0].equals(employee.getPassword())) {
        	 	String jwt = jwtGenerator.generateAccessToken(employee.getUsername());
           	 AR = new AuthResponse(loginEmployee.getName(),loginEmployee.getEmail(),loginEmployee.getSecret(),jwt,loginEmployee.isUsing2FA());
        	}else {
        	    String jwt = "error";
           	    AR = new AuthResponse(loginEmployee.getName(),null,null,jwt,loginEmployee.isUsing2FA());
        	}
       
        }else {
        	String jwt = "error";
        	 AR = new AuthResponse(loginEmployee.getName(),null,null,jwt,loginEmployee.isUsing2FA());
        }
    	
        return new ResponseEntity<>(AR,HttpStatus.OK);
    }
    
    
    @PostMapping("/logindecodeJWT")
    public ResponseEntity<?> loginEmployee(@RequestBody String token) {
    
    	Claims loginEmployee = employeeService.loginEmployeeT(token);
    	
    if(loginEmployee==null){
    	return new ResponseEntity<>("error!",HttpStatus.OK);
    }else {
    	 return new ResponseEntity<>(loginEmployee,HttpStatus.OK);
    }   
    }
    
    
    @PostMapping("/verify/{Code}")
    public ResponseEntity<?> verifyCode(@PathVariable("Code") String Code ,@RequestBody AuthResponse authResponse) {
    	if (!verifier.isValidCode(authResponse.getSecret(), Code)) {
    		return new ResponseEntity<>(new ApiResponse(false, "Invalid Code!"), HttpStatus.BAD_REQUEST);
    	}
    	String jwt = jwtGenerator.generateAccessToken(authResponse.getEmail());
 
    	 AuthResponse AR = new AuthResponse(authResponse.getName(),authResponse.getEmail(),authResponse.getSecret(),jwt,true);
    	 return new ResponseEntity<>(AR,HttpStatus.OK);
    }
    

    
    
    
}
