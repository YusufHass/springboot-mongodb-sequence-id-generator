package com.springboot.springbootmongodbsequenceidgenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springbootmongodbsequenceidgenerator.entity.Book;
import com.springboot.springbootmongodbsequenceidgenerator.repository.BookRepository;
import com.springboot.springbootmongodbsequenceidgenerator.service.SequenceGenerator;

@SpringBootApplication
//makes the connect and able data to the database
@RestController 
public class SpringbootMongodbSequenceIdGeneratorApplication {

    @Autowired
	private BookRepository bookRepository;
	@Autowired
	private SequenceGenerator sequenceGenerator;

	@PostMapping("/books")
	public Book save (@RequestBody Book book){
		//to generate the sequence 

		book.setId(sequenceGenerator.getSequenceNumber(Book.SEQUENCE_NAME));
		return bookRepository.save(book);

	}
	@GetMapping("/books")
	public List<Book> getBooks(){

		return bookRepository.findAll();
	}




	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbSequenceIdGeneratorApplication.class, args);
	}

}
