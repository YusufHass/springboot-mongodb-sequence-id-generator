package com.springboot.springbootmongodbsequenceidgenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springbootmongodbsequenceidgenerator.entity.Student;
import com.springboot.springbootmongodbsequenceidgenerator.exception.NotFoundException;
import com.springboot.springbootmongodbsequenceidgenerator.repository.StudentRepository;
import com.springboot.springbootmongodbsequenceidgenerator.repository.StudentRepository;
import com.springboot.springbootmongodbsequenceidgenerator.service.SequenceGenerator;

@SpringBootApplication
// makes the connect and able data to the database
@RestController

/*
 * That is CORS(Cross origin resource sharing) issue you are facing which
 * happens when you send the requests from browser and in response, browser
 * expects some set of headers in response which indicates to browser that
 * server wants to serve requests from this current origin in browser or not.
 * To solve this, at the spring boot side, you have to use CrossOrigin
 * annotation which lets the backend add the required CORS headers in the
 * response for request coming from different origins.
 * 
 * so add this annotation
 * 
//  * @CrossOrigin(origins = "http://localhost:3000")
 * to your RestController class
 * 
 * If you want to indicate to browser that server allows request from any origin
 * on spring boot server, then modify the cross-origin annotation to
 * 
 * @CrossOrigin(origins = "*")
 */
//  @CrossOrigin(origins = "http://localhost:3000")

@CrossOrigin("*")
public class SpringbootMongodbSequenceIdGeneratorApplication {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private SequenceGenerator sequenceGenerator;
//posting file to the database
	@PostMapping("/students")
	public Student save(@RequestBody Student student) {
		// to generate the sequence

		student.setId(sequenceGenerator.getSequenceNumber(Student.SEQUENCE_NAME));
		return studentRepository.save(student);

	}
//getting the student data from the database
	@GetMapping("/students")
	public List<Student> getStudents() {

		return studentRepository.findAll();
	}

	// deleting student list from the database
		// @RequestMapping("/student/{id}")
	@RequestMapping(value = "/students/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int id) {
		Student students = studentRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Students Not Exist" + id));
		studentRepository.delete(students);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// @PostMapping("student/{id}")
	// // @RequestMapping(value = "/student/{id}", method = {RequestMethod.DELETE})
	// // @RequestMapping(value = "/student/{id}")
	// public void deleteStudent (@PathVariable int id){
	// 	studentRepository.deleteById(id);
	// }

	//searching student data based on student id
	// @GetMapping("{id}")
	@RequestMapping(value = "/students/{id}", method = {RequestMethod.GET})
	public ResponseEntity<Student>getStudentById(@PathVariable int id){
		Student students = studentRepository.findById(id).orElseThrow(()-> new NotFoundException("Student not exist with id"+id));
		return ResponseEntity.ok(students);
	}



	public static void main(String[] args) {
		SpringApplication.run(SpringbootMongodbSequenceIdGeneratorApplication.class, args);
	}

}
