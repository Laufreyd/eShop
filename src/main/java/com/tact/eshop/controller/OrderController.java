package com.tact.eshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tact.eshop.Application;
import com.tact.eshop.entity.Customer;
import com.tact.eshop.entity.Order;
import com.tact.eshop.entity.OrderProduct;
import com.tact.eshop.entity.Product;
import com.tact.eshop.repository.CustomerRepository;
import com.tact.eshop.repository.OrderProductRepository;
import com.tact.eshop.repository.OrderRepository;
import com.tact.eshop.repository.ProductRepository;


@Controller
@RequestMapping("/order/")
public class OrderController {
	 private static final Logger log =
	            LoggerFactory.getLogger(Application.class);
	@Autowired
	private OrderRepository oRepo;
	
	@Autowired
	private CustomerRepository cRepo;
	
	@Autowired
	private ProductRepository pRepo;
	
	@Autowired
	private OrderProductRepository opRepo;
	
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
	
	@PostMapping("add/{id}")
	public String addProductToChart(OrderProduct qt, @PathVariable String id, HttpSession session, Model model) {
		
		String returnString = "redirect:/product/{id}";
		
		if(session.getAttribute("account") != null) {
			
			Customer currentCustomer = (Customer) session.getAttribute("account");
			currentCustomer = cRepo.findOne(currentCustomer.getId());
			if(session.getAttribute("currentOrder") == null) {
				
				List<Order> ordersCustomer = currentCustomer.getOrders();
				
				ArrayList<Order> allOrders = new ArrayList<Order>();

				for(Order orderInList : ordersCustomer) {
					if(orderInList.getFinished() == false) {
						allOrders.add(orderInList);
					}
				}
				
				switch (allOrders.size()) {
				case 0:
					Order order = new Order();
					currentCustomer.addOrder(order);
					oRepo.save(order);
					session.setAttribute("currentOrder", order);
					break;
				case 1:
					session.setAttribute("currentOrder", ordersCustomer.get(0));
					break;
				default:
					session.setAttribute("account", null);
					model.addAttribute("error", "An error was occur, please connect again<br>If the problem, contact support system");
					returnString = "/user/connexion";
					break;
				}
				
			}
			
			if(session.getAttribute("currentOrder") != null) {
				Product productToAdd = pRepo.findOne(Long.valueOf(id));
				Integer quantity = qt.getQuantity();
				
				if(productToAdd.getEndOfLife() == false && productToAdd.getQuantity() > 0) {					
					Order newOrder = (Order) session.getAttribute("currentOrder");
					
					Boolean check = false;
					OrderProduct newOrderProduct = new OrderProduct(newOrder, productToAdd, 1);

					for(OrderProduct orderProduct : newOrder.getOrderedProduct()) {
						if(orderProduct.getProduct().getId() == productToAdd.getId()) {
							orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
							productToAdd.setQuantity(productToAdd.getQuantity() - quantity);
							newOrder.setTotal(newOrder.getTotal() + (productToAdd.getPrice() * quantity));
							check = true;
							opRepo.save(orderProduct);
							oRepo.save(newOrder);
							break;
						}
					}
					
					if(!check) {
						newOrder.addProduct(productToAdd, quantity);
						newOrderProduct = newOrder.getOrderedProduct().get(newOrder.getOrderedProduct().size() - 1);
						opRepo.save(newOrderProduct);
					}
					
				}
			}
		}
		
		return returnString;
	}
	
	@PostMapping("remove/{id}")
	public String removeProductToChart(OrderProduct qt, @PathVariable String id, HttpSession session, Model model) {
		String returnString = "redirect:/order/list";
		if(session.getAttribute("account") != null) {
			
			Customer currentCustomer = (Customer) session.getAttribute("account");
			currentCustomer = cRepo.findOne(currentCustomer.getId());
			if(session.getAttribute("currentOrder") == null) {
				
				List<Order> ordersCustomer = currentCustomer.getOrders();
				
				ArrayList<Order> allOrders = new ArrayList<Order>();

				for(Order orderInList : ordersCustomer) {
					if(orderInList.getFinished() == false) {
						allOrders.add(orderInList);
					}
				}
				
				switch (allOrders.size()) {
				case 0:
					Order order = new Order();
					currentCustomer.addOrder(order);
					oRepo.save(order);
					session.setAttribute("currentOrder", order);
					break;
				case 1:
					session.setAttribute("currentOrder", ordersCustomer.get(0));
					break;
				default:
					session.setAttribute("account", null);
					model.addAttribute("error", "An error was occur, please connect again<br>If the problem, contact support system");
					returnString = "/user/connexion";
					break;
				}
				
			}
			
			if(session.getAttribute("currentOrder") != null) {
				Order newOrder = (Order) session.getAttribute("currentOrder");

				Product productToErase = pRepo.findOne(Long.valueOf(opRepo.findOne(Long.valueOf(id)).getProduct().getId()));
				
				for(OrderProduct orderProduct : newOrder.getOrderedProduct()) {
					if(orderProduct.getProduct().getId() == productToErase.getId()) {
						newOrder.removeProduct(productToErase, qt.getQuantity());
						oRepo.save(newOrder);

						if(orderProduct.getQuantity() == 0) {
							opRepo.delete(orderProduct.getId());
							if(newOrder.getOrderedProduct().isEmpty()) {
								oRepo.delete(newOrder.getId());
							}
								
							session.setAttribute("currentOrder", null);
						}
						else {
							opRepo.save(orderProduct);
						}

						break;
					}
				}
			}
		}
		return returnString;
	}

}
