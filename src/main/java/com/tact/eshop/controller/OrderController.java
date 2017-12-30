package com.tact.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/order/")
public class OrderController {
	
	@RequestMapping("list")
	public String list() {
		return "order/list";
	}
	
	@RequestMapping("{id}")
	public String description() {
		
		return "order/description";
	}

}
