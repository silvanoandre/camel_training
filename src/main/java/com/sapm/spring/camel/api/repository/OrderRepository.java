package com.sapm.spring.camel.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.sapm.spring.camel.api.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
