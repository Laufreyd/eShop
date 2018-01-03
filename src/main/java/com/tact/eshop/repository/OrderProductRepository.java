package com.tact.eshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tact.eshop.entity.OrderProduct;
import com.tact.eshop.entity.Product;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Long>{

	List<OrderProduct> findByProduct(Product p);
}
