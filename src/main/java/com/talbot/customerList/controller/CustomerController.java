package com.talbot.customerList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.talbot.customerList.repositories.CustomersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class CustomerController {
	final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomersRepository repository;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView helloWorld( ModelMap model ) {
		List customers = repository.findAll();
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("customers", customers );
		return modelAndView;
	}
}