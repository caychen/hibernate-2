package org.com.cay.both.OneToMany;

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

	/**
	 * 1、声明集合类型时，需使用接口类型，因为Hibernate在获取集合类型时，返回的是Hibernate内置的集合类型,
	 * 	而不是javase标准的集合类型。
	 * 2、需要把集合属性进行初始化，避免出现空指针异常
	 */
	private Set<Orders> orderss = new HashSet<>();

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

	public Set<Orders> getOrderss() {
		return orderss;
	}

	public void setOrderss(Set<Orders> orderss) {
		this.orderss = orderss;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + "]";
	}

}
