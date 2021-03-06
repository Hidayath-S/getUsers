package jar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

@RestController
public class UsersController {
	@Autowired
	UserRepository repo;
	
	

	@GetMapping(path="users")
	public List<User> getUsers() {

		List<User> users = (List<User>) repo.findAll();
		return users;

	}
	@GetMapping(path = "user/{userId}", produces = { "application/json" })
	
	public Object getUserById(@PathVariable int userId) {
		ErrorMessages er= new ErrorMessages();
		boolean status = repo.existsById(userId);
		if (status == false) {
			er.setCode(HttpStatus.BAD_REQUEST.toString());
			er.setMessage("No user found with userId=" + userId);
			
			JsonObject error= new JsonObject();
			error.addProperty("FaultCode", er.getCode());
			error.addProperty("FaultMessage", er.getMessage());
			
			JsonObject Fault= new JsonObject();
			Fault.add("FaultList", error);
			
			
			return Fault;
			
		}
		
		Optional<User> users =  repo.findById(userId);
		return Arrays.asList(users);
		
		

	}

	@PostMapping(path = "user", consumes = { "application/json" })
	public Object createUser(@RequestBody User user) {
		ErrorMessages er= new ErrorMessages();
		
		boolean status=repo.existsByEmail(user.getEmail());
		if(status==true) {
			//er.setCode((HttpStatus.BAD_REQUEST.value()));			
			er.setMessage("user found with email=" + user.getEmail());
			
			return  er;	
			
			
			  
			
		}
		int userCount = (int) repo.count();
		user.setUserId(userCount + 1);

		repo.save(user);
		return user;
	}
		
		
		
		
		   
		 
		  
		 
		
		
		

	

	@DeleteMapping(path = "user/{userId}", produces = { "application/json" })
	public ErrorMessages deleteUser(@PathVariable int userId) {
		// Integer u=(repo.findById(userId);
		ErrorMessages er= new ErrorMessages();
		boolean status = repo.existsById(userId);
		if (status == false) {
			//er.setCode(HttpStatus.BAD_REQUEST.value());
			er.setMessage("No user found with userId=" + userId);
			return er;
		}
		//er.setCode(HttpStatus.OK.value());
		er.setMessage("User deleted succefully!");
		repo.deleteById(userId);

		return er;

	}

}
