package com.app.controller;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.helper.CSVHelper;
import com.app.service.BookService;
import com.app.service.CSVService;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


@RestController
@RequestMapping("/books")
public class HomeController {

	Logger logger = LoggerFactory.getLogger(HomeController.class);

	
	@Autowired
	CSVService fileService;
	
	
	@Value("${mygreet:Valuess}")
	private String greets;
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/add-book")
	public String addBook(@RequestBody Map<String,Object> book)
	{
		return bookService.addBook(book);
	}
	
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);
				
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(message);
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
			}			
		}
		
		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
	}
	
	
}
