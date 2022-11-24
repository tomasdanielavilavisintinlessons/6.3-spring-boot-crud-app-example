package com.z9devs.controllersimpl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.z9devs.entities.User;
import com.z9devs.services.UserService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService uService;

	@GetMapping("")
	public ModelAndView login(@RequestParam(value="error", required=false) String error) {
		ModelAndView mav = new ModelAndView();
		if(error != null) {
			switch(error) {
				case "expiredSession":
					mav.addObject("error", "La sessione è scaduta perché hai effettuato il login da un'altra parte.");
					break;
				case "invalidSession":
					mav.addObject("error", "La sessione non è valida o è scaduta, effettuare il login.");
					break;
				case "authenticationFailed":
					mav.addObject("error", "Lo user o la password non sono corretti.");
					break;
			}
		}
		mav.setViewName("sec/login");
		return mav;
	}
	
	@GetMapping("signIn")
	public String signIn() {
		
		return "sec/signIn";
	}
	
	@PostMapping("doSignIn")
	public @ResponseBody Map<String,String> signInAction(@RequestBody User user) {
		Map<String,String> res = new LinkedHashMap<String,String>();
		if(uService.userExists(user.getUsername())) {
			System.out.println("User esiste, da controller");
			res.put("esito", "alreadyExists");
		} else {
			uService.createUser(user);
		}
		return res;
	}
}
