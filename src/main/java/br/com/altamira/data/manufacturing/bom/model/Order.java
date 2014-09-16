package br.com.altamira.data.manufacturing.bom.model;

import java.util.Date;
import java.util.List;

public class Order {

	Long id;
	Long number;
	String customer;
	String quotation;
	Date created;
	List<Item> items;
	
	public Order() {
		
	}
	
	public Order(Long id, Long number, String customer, String quotation,
			Date created) {
		super();
		this.id = id;
		this.number = number;
		this.customer = customer;
		this.quotation = quotation;
		this.created = created;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getQuotation() {
		return quotation;
	}
	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}


}
