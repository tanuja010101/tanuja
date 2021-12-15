
package com.rms.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rms.exception.ResponseNotFoundException;
import com.rms.model.UserDetails;
import com.rms.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;// Dependency injection

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to Our Restauranat ";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public UserDetails addDetails(@Valid @RequestBody UserDetails user) {
		return userService.addDetails(user);

	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<UserDetails> getAllDetails() {
		return userService.getAllDetails();
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public UserDetails getUserDetailsById(@PathVariable("userId") int uId) throws ResponseNotFoundException {
		Optional<UserDetails> user = userService.getDetailsById(uId);
		if (user.isEmpty()) {
			throw new ResponseNotFoundException("User not found with userid:");
		}
		return user.get();
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public UserDetails updateDetails(@RequestBody UserDetails user) {

		return userService.updateDetail(user);
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public String deleteUserDetails(@PathVariable("userId") int uId) {
		Optional<UserDetails> u = userService.getDetailsById(uId);
		if (u.isPresent()) {
			userService.deleteDetailsById(uId);
			return "The User Details deleted with the user Id: " + uId;
		}

		return "The User Details Not deleted with the user Id: " + uId;

	}

}
