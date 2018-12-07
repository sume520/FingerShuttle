package com.example.sun.fingershuttle.JsonHandle;
import com.google.gson.Gson;

public class JsonManager {
	public static String createJson(String operate,String table,String values,String condition) {
		Order order=new Order(operate,table,values,condition);
		Gson gson=new Gson();
		String json=gson.toJson(order);
		System.out.println(json);
		return json;
	}
}
