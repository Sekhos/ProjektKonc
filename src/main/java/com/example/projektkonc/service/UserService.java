package com.example.projektkonc.service;

import com.example.projektkonc.model.User;
import com.example.projektkonc.exception.UserNotFoundException;
import com.example.projektkonc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> listAll() {
		return (List<User>) userRepository.findAll();
	}

	public void save(User user) {
		userRepository.save(user);
//		return "redirect:/users";
	}

	public User get(Integer id) throws UserNotFoundException {
		Optional<User> result = userRepository.findById(id);
		if (result.isPresent()) {
			return result.get();
		}
		throw new UserNotFoundException("Could not find any users with ID:" + id);
	}

	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}
}