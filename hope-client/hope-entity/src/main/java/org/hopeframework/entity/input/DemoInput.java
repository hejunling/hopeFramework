package org.hopeframework.entity.input;

import java.io.Serializable;

import org.hopeframework.core.request.PagePojo;

/**
 * DEMO INPUT
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
public class DemoInput extends PagePojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
