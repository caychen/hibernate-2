package org.com.cay.entity.EmbeddedId;

import javax.persistence.Embeddable;

@Embeddable//表示是一个嵌入类，该类的对象在另外一个实体类中充当属性
public class Address {

	private String postcode;
	private String phone;
	private String address;
	
	public Address(){
		
	}

	
	public Address(String postcode, String phone, String address) {
		super();
		this.postcode = postcode;
		this.phone = phone;
		this.address = address;
	}



	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
