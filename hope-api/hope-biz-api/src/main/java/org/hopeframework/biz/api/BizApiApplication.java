package org.hopeframework.biz.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * BIZ API APPLICATION
 * 
 * @author hechuan
 *
 * @created 2017年4月11日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@SpringBootApplication
public class BizApiApplication {

	private static final Logger logger = LoggerFactory.getLogger(BizApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BizApiApplication.class, args);

		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				logger.info("shutdown now and application is exit!");
			}
		});
	}

}
