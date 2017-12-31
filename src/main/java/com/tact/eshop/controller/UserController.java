package com.tact.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tact.eshop.entity.Customer;
import com.tact.eshop.repository.CustomerRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private CustomerRepository cRepo;
	
	@RequestMapping("profil")
	public String profil() {
		
		return "/user/profil";
	}
	
	@RequestMapping("connexion")
	public String connexion(HttpSession session) {
		String returnString;
		if(session.getAttribute("account") != null) {
			returnString = "redirect:/";
		}
		else {
			returnString = "/user/connexion";
		}
		return returnString;
		
	}
	
	@RequestMapping("disconnexion")
	public String disconnexion(HttpSession session) {
		if(session.getAttribute("account") != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@PostMapping("authentification")
	public String authentification(Customer customer, HttpSession session, Model model) {
		List<Customer> customerList = cRepo.findByLastName(customer.getLastName());
		ArrayList<Customer> customerFocused = new ArrayList<Customer>();
		
		for(Customer customerInList : customerList) {
			if(customerInList.getFirstName().equals(customer.getFirstName())) {
				customerFocused.add(customerInList);
			}
		}
		
		if(!customerFocused.isEmpty()) {
			session.setAttribute("account", customer);
			return "redirect:/user/profil";
		}
		else{
			model.addAttribute("error", "Aucune personne de ce nom n'est inscrite sur notre site");
			return "/user/connexion";
		}
	}

}
