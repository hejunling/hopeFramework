package org.hopeframework.biz.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 业务服务应用
 * 
 * @author hechuan
 *
 * @created 2017年4月11日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@MapperScan(value= {"org.hopeframework.db.mapper"})
@SpringBootApplication
public class BizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BizServiceApplication.class, args);
	}
}
