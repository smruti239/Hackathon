package com.SRK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AuthenticationController {
	 @Autowired
	    private UserRepository userRepository;

	    @PostMapping("/signup")
	    public ResponseEntity<String> signup(@RequestBody UserSignUpRequest request) {
	        User user = new User();
	        user.setName(request.getName());
	        user.setPassword(request.getPassword());
	        user.setApproved(false); // Wait for admin approval

	        userRepository.save(user);

	        // Optional: Log hostel and student/staff data
	        System.out.println("Hostel Name: " + request.getHostelDetail().getHostel_name());
	        System.out.println("Students: " + request.getStudentData().size());
	        System.out.println("Staff: " + request.getStaffDetail().size());

	        return ResponseEntity.ok("Signup received. Awaiting admin approval.");
	    }

	    @PostMapping("/signin")
	    public ResponseEntity<String> signin(@RequestBody User loginRequest) {
	        Optional<User> optionalUser = userRepository.findByName(loginRequest.getName());
	        if (optionalUser.isPresent()) {
	            User user = optionalUser.get();

	            if (!user.getPassword().equals(loginRequest.getPassword())) {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
	            }

	            if (!user.isApproved()) {
	                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin approval required.");
	            }

	            return ResponseEntity.ok("Login successful!");
	        }
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	    }
	}
	