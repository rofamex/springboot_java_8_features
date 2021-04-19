package com.rofamex.springboot_java_8_features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rofamex.springboot_java_8_features.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@GetMapping(value = "/execute")
	public void execute() {
//		customerService.streamForEachTest();
//		customerService.streamMapTest();
//		customerService.streamFindFirstTest();
//		customerService.streamToArrayTest();
//		customerService.streamFlatMapTest();
//		customerService.streamPeekTest();
//		customerService.streamSkipLimitTest();
//		customerService.streamSortTest();
		customerService.streamMinMaxTest();
		
	}

}
