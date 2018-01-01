package com.tact.eshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** Class for Order POJO of eShop project. */
@Entity
@Table(name="shop_order")
public class Order extends EntityBase {

    @Column
    private Float total = 0f;
    
    @Column
    private Boolean finished = false;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private List<OrderProduct> products;

    public Order() {
        this.products = new ArrayList<OrderProduct>();
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * @param customer the customer to set
     */
    public Order setCustomer(Customer customer) {
        this.customer = customer;

        // Bidirectional check
        if (customer != null && !customer.getOrders().contains(this)) {
            customer.addOrder(this);
        }

        return this;
    }

    /**
     * @return the total
     */
    public Float getTotal() {
        return this.total;
    }

    /**
     * @return the boolean who says if the order is close or not
     */
    public Boolean getFinished() {
		return this.finished;
	}

    /**
     * @param finished The boolean
     * @return Order
     */
	public Order setFinished(Boolean finished) {
		this.finished = finished;
		
		return this;
	}
	
	public Order addProduct(Product product, int qt) {
		
		OrderProduct item = null;
		
		for(OrderProduct orderproduct : this.getOrderedProduct()) {
			if(orderproduct.getProduct().getId() == product.getId()) {
				item = orderproduct; 
				break;
			}
		}
		
		if(item == null) {
			item = new OrderProduct(this, product, qt);
			this.products.add(item);
		}
		else {
			item.setQuantity(item.getQuantity() + qt);
		}
		
        this.total += product.getPrice() * qt;
        product.setQuantity(product.getQuantity() - 1);

        return this;
    }

    public List<OrderProduct> getOrderedProduct() {
        return this.products;
    }

    public void removeProduct(Product product, int qt) {
        OrderProduct finded = null;
        for (OrderProduct orderProduct : this.products) {
            if (orderProduct.getProduct().equals(product)) {
                finded = orderProduct;
            }
        }

        finded.setQuantity(finded.getQuantity() - qt);
        if (finded.getQuantity() <= 0) {
            this.products.remove(finded);
        }
        
        this.total -= product.getPrice() * qt;
    }

}
