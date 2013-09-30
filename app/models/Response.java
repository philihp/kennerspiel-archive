package models;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.*;
import com.fasterxml.jackson.annotation.*;

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
