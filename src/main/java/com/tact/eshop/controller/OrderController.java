package com.tact.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tact.eshop.entity.Customer;
import com.tact.eshop.entity.Order;
import com.tact.eshop.repository.CustomerRepository;
import com.tact.eshop.repository.OrderRepository;


@Controller
@RequestMapping("/order/")
public class OrderController {
	
	@Autowired
	private OrderRepository oRepo;
	
	@RequestMapping("list")
	public String list(HttpSession session, Model model) {
		String returnString;
		if(session.getAttribute("account") != null) {
			Customer customer = (Customer) session.getAttribute("account");
			
			List<Order> orders = (List<Order>) oRepo.findAll();
			ArrayList<Order> ordersOfCustomer = new ArrayList<Order>();
			
			for(Order orderInList : orders) {
				if(orderInList.getCustomer().getId() == customer.getId()) {
					ordersOfCustomer.add(orderInList);
				}
			}
			
			model.addAttribute("orders", ordersOfCustomer);
			returnString = "/order/list";
		}
		else {
			returnString = "redirect:/";
		}
		return returnString;
	}
	
	@RequestMapping("{id}")
	public String description(@PathVariable String id, Model model) {
		
		Order order = oRepo.findOne(Long.valueOf(id));
		model.addAttribute("order", order);
		
		return "order/description";
	}

}
