package com.ktds.CounselingAdvisor.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktds.CounselingAdvisor.vo.Env;

@Controller
@RequestMapping("/CounselingAdvisor")
public class CounselingAdvisorController {
	@Inject private Env evn;
	
	@RequestMapping(method = RequestMethod.GET)
	public String hello(@RequestParam String name, @RequestParam String userid, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("userid", userid);
		
		String targetUrl = evn.getTargetUtl();
		
//		model.addAttribute("targetUrl", "http://192.168.56.1:9191/api/domain/");
//		model.addAttribute("targetUrl", "http://127.0.0.1:9191/api/domain/");
//		model.addAttribute("targetUrl", "http://127.0.0.1:8080/api/connector/");
//		model.addAttribute("targetUrl", "http://demo.aicentro.ml:8080/api/connector/");
//		model.addAttribute("targetUrl", "http://demo.aiadvisor.ml:8080/api/connector/");
//		model.addAttribute("targetUrl", "http://192.168.199.110:8080/api/connector/");
		model.addAttribute("targetUrl", targetUrl);
		String message = name + "(" + userid + ")님 방문을 환영합니다.";
		model.addAttribute("welcomeMessage", message);
		return "CounselingAdvisor"; 
	}
}
