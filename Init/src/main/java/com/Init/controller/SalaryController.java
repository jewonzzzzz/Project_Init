package com.Init.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "salary/*")
public class SalaryController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(SalaryController.class);

}
