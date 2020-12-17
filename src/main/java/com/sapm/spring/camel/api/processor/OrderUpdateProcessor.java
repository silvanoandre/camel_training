package com.sapm.spring.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapm.spring.camel.api.dto.OrderDto;
import com.sapm.spring.camel.api.service.OrderService;

@Component
public class OrderUpdateProcessor implements Processor{
	
	@Autowired
	private OrderService service;

	@Override
	public void process(Exchange exchange) throws Exception {
		
		service.updateOrder(exchange.getIn().getBody(OrderDto.class));
	}

}
