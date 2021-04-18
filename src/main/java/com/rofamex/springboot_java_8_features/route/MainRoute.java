package com.rofamex.springboot_java_8_features.route;

import java.awt.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rofamex.springboot_java_8_features.entity.Customer;
import com.rofamex.springboot_java_8_features.repository.CustomerRepository;

@Component
public class MainRoute {
	private static final Logger LOG = LoggerFactory.getLogger(MainRoute.class);
	
	@Autowired
	CustomerRepository customerRepository;

	public MainRoute() {
	}


}