package com.pccth.pccsdmgrservice.resource;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pccth.pccsdmgrservice.model.Employee;
import com.pccth.pccsdmgrservice.model.news;
import com.pccth.pccsdmgrservice.model.newsResponse;
import com.pccth.pccsdmgrservice.model.newspdf;
import com.pccth.pccsdmgrservice.repo.newsPDFRepo;
import com.pccth.pccsdmgrservice.service.EmployeeService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import com.pccth.pccsdmgrservice.service.EmployeeService;

@RestController
@RequestMapping("/file")
public class FileResource {
	 private final EmployeeService employeeService;

	 
	 
    public FileResource(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	// define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/upload/";

    // Define a method to upload files
    @PostMapping("/upload/{id}")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files")List<MultipartFile> multipartFiles ,@PathVariable("id") String id) throws IOException {
    	
        List<String> filenames = new ArrayList<>();
        for(MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = get(DIRECTORY, filename).toAbsolutePath().normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(filename);
            newspdf newspdf = new newspdf(null, filename,id);
            newspdf addnewspdf = employeeService.addnewspdf(newspdf);  
        }
        
        return ResponseEntity.ok().body(filenames);
    }
    
    @GetMapping("/allnewspdf/{id}")
    public ResponseEntity<List<String>> allnewspdf(@PathVariable("id") String id) throws IOException {
    	List<String> allnewspdf = employeeService.listnewspdf(id);	
    	  return new ResponseEntity<>(allnewspdf, HttpStatus.OK);
    }
    
    
    @PostMapping("/addnews")
    public ResponseEntity<news> addnews(@RequestBody news News) throws IOException {
    	news addnews = employeeService.addnews(News);
    	 return new ResponseEntity<>(addnews,HttpStatus.OK);
    }
    
    @GetMapping("/allnews")
    public ResponseEntity<List<newsResponse>> allnews() throws IOException {
    	List<newsResponse> addnews = employeeService.findAllNews();	
    	  return new ResponseEntity<>(addnews, HttpStatus.OK);
    }
    

    // Define a method to download files
    @GetMapping("download/{filename}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
        Path filePath = get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders).body(resource);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
    }

}
