package com.baikja.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.baikja.model.Env;
import com.baikja.model.User;
import com.baikja.service.UserService;
import com.baikja.util.LoginUtil;

@Controller
public class LoginController {

	// private String targetUrl = "http://demo.aiadvisor.ml:8080/CounselingAdvisor";
	@Inject
	private Env evn;

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "이미 가입된 Email주소입니다. ");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {

			System.out.println("---------->  : " + user.getRole());
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "정상적으로 사용자 등록이 완료되었습니다.");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"안녕하세요!    " + user.getName() + "" + user.getLastName()+ "님" + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage", user.getLastName() + "" + user.getName() + "님의 권한은 Admin 입니다.");
		modelAndView.setViewName("admin/home");

		System.out.println(user.getLastName());
		System.out.println(user.getName());

		System.out.println("#####################  권한 확인  시작 ################################");
		System.out.println(LoginUtil.currentUserName());
		System.out.println(LoginUtil.hasAdminRole());
		System.out.println("#####################  권한 확인  완료 ################################");
		LoginUtil.UserInfoPrint();
		return modelAndView;
	}

	// logingSuccess

	@RequestMapping(value = "/logingSuccess", method = RequestMethod.GET)
	public ModelAndView loginSuccess() {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		System.out.println(user.getLastName());
		System.out.println(user.getName());

		System.out.println("#####################  권한 확인  시작 ################################");
		System.out.println(LoginUtil.currentUserName());
		System.out.println(LoginUtil.hasAdminRole());
		System.out.println("#####################  권한 확인  완료 ################################");

		String role = LoginUtil.getUserRole();

		modelAndView.addObject("userName",
				"안녕하세요!    " + user.getName() + "" + user.getLastName() + " 사원님" + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage",
				user.getName() + "" + user.getLastName() + "님의 권한은 " + role + " 입니다.");
		modelAndView.addObject("userid", user.getEmail());
		modelAndView.addObject("name", user.getLastName() + "" + user.getName());

		// targetUrl
		// modelAndView.addObject("targetUrl", targetUrl);
		modelAndView.addObject("targetUrl", evn.getTargetUtl());

		if (role.equals("ADMIN")) {
			modelAndView.setViewName("admin/home");

		} else if (role.equals("USER")) {
			modelAndView.setViewName("user/home");
		} else if (role.equals("GUEST")) {
			modelAndView.setViewName("guest/home");
		}

		return modelAndView;
	}

}
