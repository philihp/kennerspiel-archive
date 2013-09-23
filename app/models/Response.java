package models;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;


public class Response {
	public String message = "";
	public Response(String message) {
		this.message = message;
	}
	
	public static JsonNode json(String message) {
		return Json.toJson(new Response(message));
	}
}
