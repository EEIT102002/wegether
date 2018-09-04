package controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class indexController {

	
	@RequestMapping(value="/indexHello")
	public void indexHello() {
		System.out.println("AAA");
	}
}
