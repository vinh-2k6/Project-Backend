package vn.hoidanit.springsieutoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.springsieutoc.service.UserService;

@RestController
public class HomeController {
	private final UserService userService;

	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String index() {
		return "Hello World from Spring Boot - @hoidanit 123";
	}

}