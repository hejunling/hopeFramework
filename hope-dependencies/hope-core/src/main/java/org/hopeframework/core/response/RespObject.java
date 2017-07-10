package org.hopeframework.core.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 完整返回信息
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@XmlRootElement(name = "response")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RespObject<T> {
	@XmlElement
	private RespInfo info;
	@XmlAnyElement(lax = true)
	private T content;

	public RespObject() {
	}

	public RespObject(T content) {
		if (null == this.info) {
			this.info = new RespInfo();
		}
		this.content = content;
	}

	public RespObject(RespInfo info, T content) {
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
