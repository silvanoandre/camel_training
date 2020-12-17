package com.sapm.spring.camel.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapm.spring.camel.api.dto.OrderDto;
import com.sapm.spring.camel.api.model.Order;
import com.sapm.spring.camel.api.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	private List<Order> list = new ArrayList<>();

	@PostConstruct
	public void initDB() {
		list.add(new Order("Mobile", 5000));
		list.add(new Order("Book", 400));
		list.add(new Order("AC", 15000));
		list.add(new Order("Shoes", 4000));
		orderRepository.saveAll(list);
	}
	
    public Order getOrder(Long id) {
        Optional < Order > order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new IllegalStateException("Order could not found for given id:" + id);
        }
        return order.get();
    }
    
    public String  removeOrder(Long id) {
        Optional < Order > order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new IllegalStateException("Order could not found for given id:" + id);
        }
        orderRepository.deleteById(id);
        
        return "Order deleted successfully";
    }

	public Order addOrder(Order order) {		
		
		return orderRepository.save(order);
		
	}

	public List<Order> getOrders() {
		return (List<Order>) orderRepository.findAll();
	}
	
	public Order updateOrder(OrderDto orderDto) {		
		Order orderUpdated = null;
		 Optional < Order > orderFound = orderRepository.findById(orderDto.getId());
	        if (!orderFound.isPresent()) {
	            throw new IllegalStateException("Order could not found for given id:" + orderDto.getId());
	        }
	        orderUpdated= orderFound.get();
	        orderUpdated.setName(orderDto.getName());
	        orderUpdated.setPrice(orderDto.getPrice());
	        
	        orderRepository.save(orderUpdated);
	        	        
	        return orderUpdated;
		
	}
	
    public Order Order(Long id) {
        Optional < Order > order = orderRepository.findById(id);
        if (!order.isPresent()) {
            throw new IllegalStateException("Order could not found for given id:" + id);
        }
        return order.get();
    }

}
