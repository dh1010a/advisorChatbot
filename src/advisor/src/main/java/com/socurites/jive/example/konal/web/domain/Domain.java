package com.socurites.jive.example.konal.web.domain;

public class Domain {

    private String message;
    private String name;
    
    public Domain() {
        
    }
    
    public Domain(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
    
}
