package com.rofamex.springboot_java_8_features.service;

import java.util.Arrays;
import java.util.Collection;
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

		// @formatter:off
		List<Customer> listOfCustomers = Stream.of(customerIds)
				.map(customerRepository::findById)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
		// @formatter:on

		listOfCustomers.forEach(c -> {
			LOG.info("customer name={}, age={}", c.getName(), c.getAge());
		});

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}
	
	public void streamFindFirstTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Long[] customerIds = { 6L, 7L, 8L };

		// @formatter:off
		Customer customer = Stream.of(customerIds)
				.map(customerRepository::findById)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.findFirst()
				.orElse(null);
		// @formatter:on

		LOG.info("customer name={}, age={}", customer.getName(), customer.getAge());

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}
	
	public void streamToArrayTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Stream<Customer> customerStream = createStream();
		
		// @formatter:off
		Customer[] arrayOfCustomer = customerStream
				.toArray(Customer[]::new);
		// @formatter:on
		
		for (int i = 0; i < arrayOfCustomer.length; i++) {
			LOG.info("customer name={}, age={}", arrayOfCustomer[i].getName(), arrayOfCustomer[i].getAge());
		}

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}
	
	public void streamFlatMapTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		// @formatter:off
		List<List<String>> namesNested = Arrays.asList( 
			      Arrays.asList("Jeff", "Bezos"), 
			      Arrays.asList("Bill", "Gates"), 
			      Arrays.asList("Mark", "Zuckerberg"));
		
		List<String> namesFlatStream = namesNested.stream()
			      .flatMap(Collection::stream)
			      .collect(Collectors.toList());
		// @formatter:on
		
		LOG.info("list of names={}", namesFlatStream);

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}
	
	public void streamPeekTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");
		
		Stream<Customer> customerStream = createStream();

		// @formatter:off
		List<Customer> listOfCustomers= customerStream
			.peek(c -> {
				LOG.info("customer name={}, age={}", c.getName(), c.getAge());
			})
			.collect(Collectors.toList());
		
		listOfCustomers.forEach(c -> {
			LOG.info("customer name={}, age={}", c.getName(), c.getAge());
		});

		// @formatter:on
		
		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}
	
}
