package org.hopeframework.core.response;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.xml.bind.annotation.XmlRootElement;

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
@JsonRootName("response")
@XmlRootElement(name = "response")
public class RespBody<T> {
	private RespInfo info;
	private T content;

	public RespBody() {
		super();
		this.info = new RespInfo();
	};

	public RespBody(T content) {
		super();
		this.info = new RespInfo();
		this.content = content;
	}

	public RespBody(RespInfo info, T content) {
		super();
		this.info = info;
		this.content = content;
	}

	public RespInfo getInfo() {
		return info;
	}

	public void setInfo(RespInfo info) {
		this.info = info;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

}
