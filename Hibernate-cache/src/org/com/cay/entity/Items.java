package org.com.cay.entity;

public class Items {
	
	private Integer id;
	private String name;
	private String city;
	private Integer price;
	private Integer number;
	private String picture;
	
	public Items(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", city=" + city
				+ ", price=" + price + ", number=" + number + ", picture="
				+ picture + "]";
	}
	
}
