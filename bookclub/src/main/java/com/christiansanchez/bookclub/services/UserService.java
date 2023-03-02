package com.christiansanchez.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.christiansanchez.bookclub.models.LoginUser;
import com.christiansanchez.bookclub.models.User;
import com.christiansanchez.bookclub.repositories.UserRepository;


@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	public User register(User newUser, BindingResult result) {
		Optional<User> userObject = userRepo.findByEmail(newUser.getEmail());
		if(userObject.isPresent()) {
			result.rejectValue("email", "Unique", "email is already taken");
		}
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "passwords must match");
		}
		if(result.hasErrors()) {
			return null;
		}
		String hash = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hash);
		return userRepo.save(newUser);
	}
	
	
	public User login(LoginUser newLoginObject, BindingResult result) {
		Optional<User> userObject = userRepo.findByEmail(newLoginObject.getEmail());
		if(!userRepo.findByEmail(newLoginObject.getEmail()).isPresent()) {
			result.rejectValue("email", "Matches", "Invalid email/password");
		}
		if(userObject.isPresent()) {
			if(!BCrypt.checkpw(newLoginObject.getPassword(), userObject.get().getPassword())) {
			    result.rejectValue("password", "Matches", "Invalid username/password");
			}
		}
		if(result.hasErrors()) {
			return null;
		}
		return userObject.get();
	}
		
	
	public User oneUser(Long id) {
		Optional<User> optionalUser = userRepo.findById(id);
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}
		return null;
		
	}
}
