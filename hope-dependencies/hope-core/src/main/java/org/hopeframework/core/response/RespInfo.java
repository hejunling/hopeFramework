package org.hopeframework.core.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hopeframework.core.constant.ResponseConst;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 返回信息
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@JsonRootName("info")
@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "info")
public class RespInfo {
	private int code;
	private String msg;

	public RespInfo() {
		super();
		this.code = ResponseConst.SUCCESS;
		this.msg = "成功";
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
