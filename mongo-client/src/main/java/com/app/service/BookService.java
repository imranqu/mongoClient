package com.app.service;

import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.BookDao;

@Service
public class BookService {

	@Autowired
	BookDao bookDao;
	
	
	public String addBook(Map<String,Object> book)
	{
		Document doc = new Document(book);
		return bookDao.addBook(doc);
			
	}
	
	
}
