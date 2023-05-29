package com.ktds.CounselingAdvisor.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.inject.Inject;

import org.json.JSONException;
import org.json.JSONObject;

import com.ktds.CounselingAdvisor.vo.Env;
import com.ktds.CounselingAdvisor.vo.Message;
import com.ktds.CounselingAdvisor.vo.Param;

import com.ktds.CounselingAdvisor.vo.*;

public class Util {
	@Inject private static Env evn;
	private static String logUrl = "http://localhost:9192/CounselingLog/questions";
	
	public static Message restCaller(Param param) throws IOException, JSONException {

		String jsonStr = "{\"userid\":\"" + param.getUserid() + "\",\"name\":\"" + param.getName() + "\""
				+ ", \"message\":\"" + param.getMessage().replaceAll("\"","\\\\\"") + "\"}";

		JSONObject jsonObject = new JSONObject(jsonStr);
		System.out.println(jsonObject);
		String str = "";

		try {
			URL url = new URL("http://127.0.0.1:9191/api/domain/");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(jsonObject.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line;
			while ((line = in.readLine()) != null) { // response를 차례대로 출력
				str = str +line;
			}
			//System.out.println("\nCrunchify REST Service Invoked Successfully..");
			in.close();
		} catch (Exception e) {
			//System.out.println("\nError while calling Crunchify REST Service");
			//System.out.println(e);
			e.printStackTrace();
		}
		

		JSONObject resultObject = new JSONObject(str);
		String message = (String) resultObject.get("message");
		String name = (String) resultObject.get("name");
		String userid = param.getUserid();
		
		System.out.println("message  ==> : "+message);
		
		message = message.replaceAll("\\\"", "");
		Message mess = new Message();
		mess.setMessage(message);
		mess.setQuestion(param.getMessage());
		mess.setUserid(userid);
		mess.setName(name);
		return mess;
	}
	
	public static LogDTO restLogCaller(Message mess) throws Exception {
		//String jsonStr = "{ \"title\" : \""+mess.getUserid()+"\",\"description\" : \""+mess.getMessage()+"\", \"answer\" : \""+mess.getQuestion()+"\"}";
		String jsonStr = "{ \"title\" : \""+mess.getUserid()+"\",\"description\" : \""+mess.getMessage().replaceAll("(\r\n|\r|\n|\n\r)", " ").replaceAll("\"","\\\\\"")+"\", \"answer\" : \""+mess.getQuestion().replaceAll("(\r\n|\r|\n|\n\r)", " ").replaceAll("\"","\\\\\"")+"\"}";
		System.out.println("LOG STR"+jsonStr);
		JSONObject jsonObject = new JSONObject(jsonStr);
		System.out.println("LOG OBJECT"+jsonObject);
		
		String str = "";
		
		try {
			URL url = new URL(logUrl);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(jsonObject.toString());
			out.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line;
			while ((line = in.readLine()) != null) { // response를 차례대로 출력
				str = str +line;
			}
			
		} catch(Exception e) {e.printStackTrace();}
		
		/**
		createdAt
		updatedAt
		id
		title
		description
		answer
		**/
		JSONObject resultObject = new JSONObject(str);
		String createdAt = (String) resultObject.get("createdAt");
		String updatedAt = (String) resultObject.get("updatedAt");
		String id = Integer.toString((int) resultObject.get("id"));
		String title = (String) resultObject.get("title");
		String description = (String) resultObject.get("description");
		String answer = (String) resultObject.get("answer");
		
		LogDTO dto = new LogDTO();
		dto.setCreatedAt(createdAt);
		dto.setUpdatedAt(updatedAt);
		dto.setId(id);
		dto.setTitle(title);
		dto.setDescription(description);
		dto.setAnswer(answer);
		return dto;
		
	}
}
