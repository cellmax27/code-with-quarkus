package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {

	public String greeting(String name) {
		// String s = order.id.toString();// = 10L;
		return "hello " + name;
	}

}