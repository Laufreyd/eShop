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
import com.tact.eshop.entity.OrderProduct;
import com.tact.eshop.entity.Product;
import com.tact.eshop.repository.CustomerRepository;
import com.tact.eshop.repository.OrderRepository;
import com.tact.eshop.repository.ProductRepository;


@Controller
@RequestMapping("/order/")
public class OrderController {
	
	@Autowired
	private OrderRepository oRepo;
	
	@Autowired
	private CustomerRepository cRepo;
	
	@Autowired
	private ProductRepository pRepo;
	
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
	public String description(@PathVariable String id, HttpSession session, Model model) {
		
		String returnString;
		
		Order order = oRepo.findOne(Long.valueOf(id));
		
		if(session.getAttribute("account") != null) {
			Customer currentCustomer = (Customer) session.getAttribute("account");
			
			if(currentCustomer.getId() == order.getCustomer().getId()) {
				model.addAttribute("order", order);
				
				returnString = "order/description";
			}
			else {
				returnString = "redirect:/order/list";
			}
		}
		else {
			returnString = "redirect:/";
		}
		
		return returnString;
	}
	
	@RequestMapping("add/{id}")
	public String addProductToChart(@PathVariable String id, HttpSession session, Model model) {
		
		String returnString = "redirect:/product/{id}";
		
		if(session.getAttribute("account") != null) {
			
			Customer currentCustomer = (Customer) session.getAttribute("account");
			currentCustomer = cRepo.findOne(currentCustomer.getId());
			if(session.getAttribute("currentOrder") == null) {
				
				List<Order> ordersCustomer = currentCustomer.getOrders();

				if(ordersCustomer.size() == 0) {
					Order order = new Order();
					currentCustomer.addOrder(order);
					oRepo.save(order);
					session.setAttribute("currentOrder", order);
				}
				else {
					
					ArrayList<Order> allOrders = new ArrayList<Order>();
					
					for(Order orderInList : ordersCustomer) {
						if(orderInList.getFinished() == false) {
							allOrders.add(orderInList);
						}
					}
					
					if(allOrders.size() == 1) {
						session.setAttribute("currentOrder", ordersCustomer.get(0));
					}
					else {
						session.setAttribute("account", null);
						model.addAttribute("error", "An error was occur, please connect again<br>If the problem, contact support system");
						returnString = "/user/connexion";
					}
				}
			}
			
			if(session.getAttribute("currentOrder") != null) {
				Product productToAdd = pRepo.findOne(Long.valueOf(id));
				
				if(productToAdd.getEndOfLife() == false) {					
					Order newOrder = (Order) session.getAttribute("currentOrder");
					
					newOrder.addProduct(productToAdd, 1);
					
					oRepo.save(newOrder);
				}
			}
		}
		
		return returnString;
	}
	
	@RequestMapping("remove/{id}")
	public String removeProductToChart(@PathVariable String id, HttpSession session, Model model) {
		String returnString = "redirect:/order/{id}";
		
		if(session.getAttribute("account") != null) {
			
			Customer currentCustomer = (Customer) session.getAttribute("account");
			currentCustomer = cRepo.findOne(currentCustomer.getId());
			if(session.getAttribute("currentOrder") == null) {
				
				List<Order> ordersCustomer = currentCustomer.getOrders();

				if(ordersCustomer.size() == 0) {
					Order order = new Order();
					currentCustomer.addOrder(order);
					oRepo.save(order);
					session.setAttribute("currentOrder", order);
				}
				else {
					
					ArrayList<Order> allOrders = new ArrayList<Order>();
					
					for(Order orderInList : ordersCustomer) {
						if(orderInList.getFinished() == false) {
							allOrders.add(orderInList);
						}
					}
					
					if(allOrders.size() == 1) {
						session.setAttribute("currentOrder", ordersCustomer.get(0));
					}
					else {
						session.setAttribute("account", null);
						model.addAttribute("error", "An error was occur, please connect again<br>If the problem, contact support system");
						returnString = "/user/connexion";
					}
				}
			}
			
			if(session.getAttribute("currentOrder") != null) {
				Product productToErase = pRepo.findOne(Long.valueOf(id));
								
				Order newOrder = (Order) session.getAttribute("currentOrder");
				
				newOrder.removeProduct(productToErase, 1);
								
				oRepo.save(newOrder);
				
			}
		}
		return returnString;
	}

}
