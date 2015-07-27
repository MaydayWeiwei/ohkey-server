package com.ohkey.app.admin.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohkey.app.model.User;
import com.ohkey.app.repository.UserRepository;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/admin/user")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserRepository userRepository;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String checkUser(@RequestParam String login, @RequestParam String password) {
		List<User> users = userRepository.findByLogin(login);
		if (users.isEmpty()) {
			return "user inexiste";
		}
		for (User user : users) {
			if (password.equals(user.getPassword()))
				return "success";
		}
		return "password incorrect";

	}
	


}
