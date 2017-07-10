package org.hopeframework.dubbo.health;

import org.hopeframework.dubbo.listener.ConsumerSubscribeListener;
import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.service.EchoService;

/**
 * dubbo健康信息
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@Component
public class DubboHealthIndicator extends AbstractHealthIndicator implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	protected void doHealthCheck(Health.Builder builder) throws Exception {
		if (!ConsumerSubscribeListener.subscribedInterfaces.isEmpty()) {
			for (Class<?> clazz : ConsumerSubscribeListener.subscribedInterfaces) {
				EchoService echoService = (EchoService) applicationContext.getBean(clazz);
				echoService.$echo("Hello");
				builder.withDetail(clazz.getCanonicalName(), true);
			}
		}
		builder.up();
	}

}