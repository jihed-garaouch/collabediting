package com.collab.collabediting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class CollabeditingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollabeditingApplication.class, args);
	}
	@Controller
	public class RouterController {
		@RequestMapping({"/", "/login", "/signup", ""})
		public String index() {
			return "forward:/index.html";
		}
	}
}
c