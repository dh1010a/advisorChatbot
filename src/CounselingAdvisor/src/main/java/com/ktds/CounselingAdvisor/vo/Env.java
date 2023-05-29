package com.ktds.CounselingAdvisor.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {
	@Value("${targetUrl}")
	private String targetUtl ;

	@Value("${logUrl}")
	private String logUrl ;
	
	@Value("${logdb}")
	private String logdb ;

	public String getTargetUtl() {
		return targetUtl;
	}

	public void setTargetUtl(String targetUtl) {
		this.targetUtl = targetUtl;
	}

	public String getLogUrl() {
		return logUrl;
	}

	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

	public String getLogdb() {
		return logdb;
	}

	public void setLogdb(String logdb) {
		this.logdb = logdb;
	}
	
	
	
}
