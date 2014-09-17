package br.com.altamira.data.manufacturing.bom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	Long number = 0l;
	String customer = "";
	String representative = "";
	Date created = new Date();
	Date delivery = new Date();
	String quotation = "";
	String comment = "";
	List<Item> item = new ArrayList<Item>();
	
	public Order() {
		
	}
	
	public Order(Long number, String customer, String quotation,
			Date created) {
		super();
		this.number = number;
		this.customer = customer;
		this.quotation = quotation;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getDelivery() {
		return delivery;
	}

	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
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

}
