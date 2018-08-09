package com.niit.model;

public class Message
{
	private int id;
	private String message;
	
	public Message()
	{
		
	}
	
	public Message(String message,int id)
	{
		this.message=message;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
