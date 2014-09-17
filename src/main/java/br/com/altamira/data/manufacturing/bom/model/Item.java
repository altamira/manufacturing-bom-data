package br.com.altamira.data.manufacturing.bom.model;

import java.util.List;

public class Item {

	int item;
	String description;
	List<Product> product;
	
	public int getItem() {
		return item;
	}
	public void setItem(int item) {
		this.item = item;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}

}
