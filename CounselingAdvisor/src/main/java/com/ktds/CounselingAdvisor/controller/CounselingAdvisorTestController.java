package com.ktds.CounselingAdvisor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/CounselingAdvisorTest")
public class CounselingAdvisorTestController {
	@RequestMapping(method = RequestMethod.GET)
	public String hello(@RequestParam String name, @RequestParam String userid, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("userid", userid);
		String message = name+"("+userid+")님 방문을 환영합니다.";
		model.addAttribute("welcomeMessage", message);
		return "CounselingAdvisorTest";
	}
}
