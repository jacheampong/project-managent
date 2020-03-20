package com.ja.dev.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ja.dev.pma.entities.UserAccount;
import com.ja.dev.pma.repos.UserAccountRepository;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	UserAccountRepository userRepository;
	
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		
		UserAccount newUser = userRepository.save(user);
		model.addAttribute("newUser", newUser);
		
		return "redirect:/";
	}

}
