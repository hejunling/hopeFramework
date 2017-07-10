package org.hopeframework.dubbo.config;

import org.hopeframework.dubbo.endpoint.DubboEndpoint;
import org.hopeframework.dubbo.endpoint.DubboOperationEndpoint;
import org.hopeframework.dubbo.health.DubboHealthIndicator;
import org.hopeframework.dubbo.metrics.DubboMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * dubbo自动加载配置
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
@EnableConfigurationProperties(DubboProperties.class)
public class DubboAutoConfiguration {

	@Autowired
	private DubboProperties dubboProperties;

	@Bean
	public ApplicationConfig requestApplicationConfig() {
		return dubboProperties.getApplication();
	}

	@Bean
	public RegistryConfig requestRegistryConfig() {
		return dubboProperties.getRegistry();
	}

	@Bean
	public ProtocolConfig requestProtocolConfig() {
		return dubboProperties.getProtocol();
	}

	@Bean
	public ProviderConfig requestProviderConfig() {
		return dubboProperties.getProvider();
	}

	@Bean
	@ConfigurationProperties(prefix = "endpoints.dubbo", ignoreUnknownFields = false)
	public DubboEndpoint dubboEndpoint() {
		return new DubboEndpoint(dubboProperties);
	}

	@Bean
	@ConfigurationProperties(prefix = "endpoints.dubbo", ignoreUnknownFields = false)
	public DubboOperationEndpoint dubboOperationEndpoint() {
		return new DubboOperationEndpoint();
	}

	@Bean
	public DubboHealthIndicator dubboHealthIndicator() {
		return new DubboHealthIndicator();
	}

	@Bean
	public DubboMetrics dubboConsumerMetrics() {
		return new DubboMetrics();
	}

}
