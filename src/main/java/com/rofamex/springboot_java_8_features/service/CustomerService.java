package com.rofamex.springboot_java_8_features.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rofamex.springboot_java_8_features.entity.Customer;
import com.rofamex.springboot_java_8_features.repository.CustomerRepository;

@Service
public class CustomerService {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	CustomerRepository customerRepository;

	private Stream<Customer> createStream() {
		Stream.Builder<Customer> customerStreamBuilder = Stream.builder();
		customerStreamBuilder.accept(new Customer(1L, "Alice", 28));
		customerStreamBuilder.accept(new Customer(2L, "Paul", 50));
		customerStreamBuilder.accept(new Customer(3L, "John", 57));

		return customerStreamBuilder.build();
	}

	public void streamForEachTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Stream<Customer> customerStream = createStream();

		customerStream.forEach(c -> {
			LOG.info("customer name={}, age={}", c.getName(), c.getAge());
		});

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}

	public void streamMapTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Long[] customerIds = { 6L, 7L, 8L };

		List<Customer> listOfCustomers = Stream.of(customerIds).map(customerRepository::findById).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		listOfCustomers.forEach(c -> {
			LOG.info("customer name={}, age={}", c.getName(), c.getAge());
		});

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}

}
