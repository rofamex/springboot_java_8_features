package com.rofamex.springboot_java_8_features.service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

	private Stream<Customer> createStreamOfCustomer() {
		Stream.Builder<Customer> customerStreamBuilder = Stream.builder();
		customerStreamBuilder.accept(new Customer(3L, "John", 50));
		customerStreamBuilder.accept(new Customer(1L, "Alice", 72));
		customerStreamBuilder.accept(new Customer(2L, "Paul", 57));

		return customerStreamBuilder.build();
	}

	public void streamForEachTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Stream<Customer> customerStream = createStreamOfCustomer();

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

		Stream<Customer> customerStream = createStreamOfCustomer();

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

		Stream<Customer> customerStream = createStreamOfCustomer();

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

	public void streamSkipLimitTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

		// @formatter:off
	    List<Integer> collect = infiniteStream
	      .skip(2)
	      .limit(5)
	      .peek(num -> LOG.info("Your number is {}.", num))
	      .collect(Collectors.toList());
		// @formatter:on

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}

	public void streamSortTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Stream<Customer> customerStream = createStreamOfCustomer();

		// @formatter:off
		List<Customer> listOfCustomers= customerStream
			.peek(c -> {
				LOG.info("customer id={}, name={}, age={}", c.getId(), c.getName(), c.getAge());
			})
			.sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
			.peek(c -> {
				LOG.info("customer id={}, name={}, age={}", c.getId(), c.getName(), c.getAge());
			})
			.collect(Collectors.toList());
		// @formatter:on

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}

	public void streamMinMaxTest() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		Stream<Customer> customerStream = createStreamOfCustomer();

		// @formatter:off
		Optional<Customer> customer = customerStream
			.peek(c -> {
				LOG.info("customer id={}, name={}, age={}", c.getId(), c.getName(), c.getAge());
			})
			.min((e1, e2) -> (int) (e1.getId() - e2.getId()));
			
		LOG.info("customer id={}, name={}, age={}", customer.get().getId(), customer.get().getName(), customer.get().getAge());

		// @formatter:on

		LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}

	public void streamStringInverted() {
		LOG.info("-------------------------- LOG START LOG ---------------------------");

		String invertThisString = "invertThisString";
		String reversedString = new StringBuilder(invertThisString).reverse().toString();
		LOG.info("reversed string = {}", reversedString);
		
		LOG.info("-------------------------- OTHER WAY --------------------------------");
		
		// @formatter:off
		Iterator<Character> revIter = invertThisString.chars() 
	            .mapToObj(item -> new Character((char)item)) 
	            .collect(Collectors.toCollection(ArrayDeque::new)) 
	            .descendingIterator(); 
	             
	    reversedString = StreamSupport 
	            .stream(Spliterators.spliteratorUnknownSize(revIter, Spliterator.ORDERED), false) 
	            .map(Object::toString) 
	            .collect(Collectors.joining()); 
	    // @formatter:on
	   LOG.info("reversed string = {}", reversedString);
	   
	   LOG.info("-------------------------- OTHER WAY --------------------------------");
	   
		// @formatter:off
	   Stream.Builder<Character> charStreamBuilder = Stream.builder();
	   
	   ArrayDeque<Character> arrayDeque = new ArrayDeque<Character>();
	   
	   invertThisString.chars()
	   .forEach(y -> arrayDeque.offerFirst(Character.valueOf((char) y)));
	   
	   reversedString = ""; 
	   
	   for (Iterator iterator = arrayDeque.iterator(); iterator.hasNext();) {
		   reversedString = reversedString.concat(String.valueOf(iterator.next()));
	   }

		// @formatter:on
	   LOG.info("reversed string = {}", reversedString);
	 
	   LOG.info("-------------------------- LOG FINISH LOG --------------------------");
	}

}
