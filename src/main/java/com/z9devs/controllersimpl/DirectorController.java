package com.z9devs.controllersimpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.z9devs.controllers.DirectorControllerAbstract;

// Annotazione Controller vs annotazione RestController
// https://www.baeldung.com/spring-request-response-body
// https://www.baeldung.com/spring-controller-vs-restcontroller
@Controller
@RequestMapping("/director")
public class DirectorController extends DirectorControllerAbstract {
	
}