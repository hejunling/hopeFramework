package org.hopeframework.core.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.hopeframework.core.cache.AccessDao;
import org.hopeframework.core.cache.BasicDataDao;
import org.hopeframework.core.constant.Constants;
import org.hopeframework.core.exception.ExceptionHandler;
import org.hopeframework.core.log.DbLogRecordHandler;
import org.hopeframework.core.spring.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.List;

/**
 * web配置
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
@EnableAsync
public class WebConfiguration extends WebMvcConfigurerAdapter {

	/** 日志 */
	private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

	@Bean
	@Primary
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);
		return builder;
	}

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(Constants.ENCODING);
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public SecurityApiHandler securityApiHandler() {
		return new SecurityApiHandler();
	}

	@Bean
	public LogHandlerInterceptor logHandlerInterceptor() {
		return new LogHandlerInterceptor();
	}

	/**
	 * 拦截器添加
	 *
	 * @param registry
	 *            拦截器注册
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logHandlerInterceptor());
		registry.addInterceptor(securityApiHandler());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger**.html");
		super.addResourceHandlers(registry);
	}

	/**
	 * controller 参数解析
	 *
	 * @param argumentResolvers
	 *            参数解析列表
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(hopeMethodArgumentResolver());
	}

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}

	@Bean
	HopeMethodArgumentResolver hopeMethodArgumentResolver() {
		return new HopeMethodArgumentResolver();
	}

	@Bean
	public ValidatorService voiceValidatorService() {
		return new ValidatorService();
	}

	@Bean
	public AccessDao accessDao() {
		return new AccessDao();
	}

	@Bean
	public BasicDataDao basicDataDao() {
		return new BasicDataDao();
	}

	@Bean
	public ExceptionHandler exceptionHandler() {
		return new ExceptionHandler();
	}

	/**
	 * 用户操作日志组件
	 *
	 * @return 用户操作日志组件
	 */
	@Bean
	public UserOperateLogComponent userOperateLogComponent() {
		return new UserOperateLogComponent();
	}

	@Bean
	@ConditionalOnBean({ UserOperateLogComponent.class })
	@Primary
	public UserOperateLogComponent.DefaultLogRecordHandler defaultLogRecordHandler() {
		return new UserOperateLogComponent.DefaultLogRecordHandler();
	}

	@Bean
	public DbLogRecordHandler dbLogRecordHandler() {
		return new DbLogRecordHandler();
	}

	/**
	 * {@link org.springframework.web.bind.annotation.ResponseBody}的请求结果拦截器
	 *
	 * @return 请求结果拦截器
	 */
	@Bean
	public RecordLogAdvice recordLogAdvice() {
		return new RecordLogAdvice();
	}

	@Bean
	public ZipkinSpanReporter spanCollector() {
		return new ZipkinSpanReporter() {

			@Override
			public void report(zipkin.Span span) {
				LOGGER.info(String.format("Reporting span [%s]", span));
			}
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

}
