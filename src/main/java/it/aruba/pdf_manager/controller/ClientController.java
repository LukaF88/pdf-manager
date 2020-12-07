package it.aruba.pdf_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller

@RequestMapping("/")
public class ClientController {

	@GetMapping({ "/", "/index" })
	public String client(HttpServletRequest request) {
		return "index";
	}

}
