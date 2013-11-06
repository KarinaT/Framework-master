package com.epam.preproduction.entities;

public abstract class Item implements Comparable<Item>{

	private String name;
	private int price;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int compareTo(Item item) {
		return this.name.compareTo(item.name);
	}
	
	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + ", desciption="
				+ description + "]";
	}

}
