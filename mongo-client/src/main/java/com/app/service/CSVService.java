package com.app.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.BookDao;
import com.app.helper.CSVHelper;
import com.app.model.Tutorial;

@Service
public class CSVService {

	@Autowired
	BookDao bookrepo;
	
	public void save(MultipartFile file) {
		try {
			List<Tutorial> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
			//repository.saveAll(tutorials);
			System.out.println("Tutorials from List");
			
			tutorials.stream().forEach(e -> System.out.println(e));
		
			bookrepo.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("Fail to store csv data: " + e.getMessage());
		}
	}
	
}
