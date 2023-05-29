package com.ktds.CounselingAdvisor.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ktds.CounselingAdvisor.Util.Util;
import com.ktds.CounselingAdvisor.vo.Env;
import com.ktds.CounselingAdvisor.vo.Message;
import com.ktds.CounselingAdvisor.vo.Param;

@CrossOrigin("*")
@Controller
@RestController
@RequestMapping(value = "/api", method = { RequestMethod.GET, RequestMethod.POST })
public class apiController {

	@Inject
	private Env evn;

	@RequestMapping(value = "/connector/", method = { RequestMethod.GET, RequestMethod.POST })
	@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
	@ResponseBody
	public Message apiCaller(@RequestBody Param param) {

		boolean flag = Boolean.valueOf(evn.getLogdb()).booleanValue();

		// boolean flag = false;

		Message message = new Message();
		try {
			message = Util.restCaller(param);
			if (flag) {
				Util.restLogCaller(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

}
