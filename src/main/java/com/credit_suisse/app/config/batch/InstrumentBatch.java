package com.credit_suisse.app.config.batch;

public class InstrumentBatch {
	
	private String name;
	private String idate;
	private double price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdate() {
		return idate;
	}
	public void setIdate(String idate) {
		this.idate = idate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Instrument [name=" + name + ", price=" + price + ", date=" + idate + "]";
	}
}
