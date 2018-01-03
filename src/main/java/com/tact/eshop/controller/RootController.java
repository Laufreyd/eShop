package com.tact.eshop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tact.eshop.entity.OrderProduct;
import com.tact.eshop.entity.Product;
import com.tact.eshop.repository.OrderProductRepository;
import com.tact.eshop.repository.ProductRepository;


@Controller
@RequestMapping("/")
public class RootController {
	
	@Autowired
	private ProductRepository pRepo;
	
	@Autowired
	private OrderProductRepository opRepo;
	
	@RequestMapping({"", "index", "home"})
	public String index(Model model) {
		
		List<Product> products = (List<Product>) pRepo.findAll();
		ArrayList<Product> lastProducts = new ArrayList<Product>();
		
		for(int i = products.size(); i > 0; i--) {
			lastProducts.add(products.get(i - 1));
			if(lastProducts.size() == 3) {
				break;
			}
		}
		
		model.addAttribute("lastProducts", lastProducts);
		
		return "root/home";
	}

}
