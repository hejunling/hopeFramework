package org.hopeframework.db;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageHelper;

/**
 * 注入mybatis用分页插件
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
public class EnablePageAutoConfiguration{

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(EnablePageAutoConfiguration.class);
	
	public static String SERVICE_NAME = "cloud-biz-service";

	@Bean
	public PageHelper pageHelper() {
		logger.info("注册MyBatis分页插件PageHelper------begin");
		
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		
		logger.info("注册MyBatis分页插件PageHelper------successfully");
		return pageHelper;
	}
	
}
