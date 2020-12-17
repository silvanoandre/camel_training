package com.sapm.spring.camel.api.resource;

import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.sapm.spring.camel.api.dto.OrderDto;
import com.sapm.spring.camel.api.model.Order;
import com.sapm.spring.camel.api.processor.OrderAddProcessor;
import com.sapm.spring.camel.api.processor.OrderUpdateProcessor;
import com.sapm.spring.camel.api.service.OrderService;

@Component
public class ApplicationResource extends RouteBuilder {

	@Autowired
	private OrderService service;

	@BeanInject
	private OrderAddProcessor addProcessor;
	
	@BeanInject
	private OrderUpdateProcessor updateProcessor;
	

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet")
		.port(9090)
		.host("localhost")
		.bindingMode(RestBindingMode.json);


		rest().get("/getOrders")
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.route()
		.setBody(() -> service.getOrders())
		.endRest();
		
		
        rest().get("/getOrder/{id}")
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .route()
        .bean(OrderService.class, "getOrder(${header.id})")
        .end();
        
        
        rest().delete("/removeOrder/{id}")
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .route()
        .bean(OrderService.class, "removeOrder(${header.id})")
        .end();
        
        
		rest().post("/addOrder")
		.consumes(MediaType.APPLICATION_JSON_VALUE)
		.type(Order.class)
		.outType(Order.class)
		.route()
		.process(addProcessor)
		.endRest();
		
		rest().put("/updateOrder")
		.consumes(MediaType.APPLICATION_JSON_VALUE)
		.type(OrderDto.class)
		.outType(Order.class)
		.route()
		.process(updateProcessor)
		.log("Entity updated")
		.endRest();
		
	}

}
