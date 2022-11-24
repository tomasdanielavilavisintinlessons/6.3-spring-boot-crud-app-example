package com.z9devs.controllersimpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("")
	public String movieListJPA(Model model, HttpServletRequest req) {
		req.getSession().setAttribute("user", "prova");
		logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
		return "index";
	}
}
