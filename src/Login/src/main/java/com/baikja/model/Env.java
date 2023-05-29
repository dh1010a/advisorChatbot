package com.baikja.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Env {
	@Value("${targetUrl}")
	private String targetUtl ;

	public String getTargetUtl() {
		return targetUtl;
	}

	public void setTargetUtl(String targetUtl) {
		this.targetUtl = targetUtl;
	}
	
}
