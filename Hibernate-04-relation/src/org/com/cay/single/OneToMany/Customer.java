package org.com.cay.single.OneToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer customerId;
	private String customerName;
	private Set<Orders> orderss = new HashSet<>();

	public Set<Orders> getOrderss() {
		return orderss;
	}

	public void setOrderss(Set<Orders> orderss) {
		this.orderss = orderss;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", orderss=" + orderss + "]";
	}

}
