package com.tact.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class RootController {
	
	@RequestMapping("")
	public String index() {

		return "root/home";
	}

}
