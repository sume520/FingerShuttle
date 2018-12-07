package com.example.sun.fingershuttle.JsonHandle;

public class Order {
	
	private String operate=null;
	private String table=null;
	private String values=null;
	private String condition=null;

	Order(){}

	
	public Order(String operate, String table, String values, String condition) {
		super();
		this.operate = operate;
		this.table = table;
		this.values = values;
		this.condition = condition;
	}


	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
}
