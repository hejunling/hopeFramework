package org.hopeframework.dubbo.endpoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hopeframework.dubbo.config.DubboProperties;
import org.hopeframework.dubbo.listener.ConsumerInvokeStaticsFilter;
import org.hopeframework.dubbo.listener.ConsumerSubscribeListener;
import org.hopeframework.dubbo.listener.ProviderExportListener;
import org.hopeframework.dubbo.listener.ProviderInvokeStaticsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

/**
 * dubbo查询端口
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
public class DubboEndpoint extends AbstractEndpoint {

	private final DubboProperties dubboProperties;

	@Autowired
	public DubboEndpoint(DubboProperties dubboProperties) {
		super("dubbo", false, true);
		this.dubboProperties = dubboProperties;
	}

	public Object invoke() {
		Map<String, Object> info = new HashMap<String, Object>();

		info.put("combo", dubboProperties.getApplication());
		info.put("registry", dubboProperties.getRegistry());
		info.put("protocol", dubboProperties.getProtocol());
		// published services
		Map<String, Map<String, Long>> publishedInterfaceList = new HashMap<String, Map<String, Long>>();
		Set<Class> publishedInterfaces = ProviderExportListener.exportedInterfaces;
		for (Class clazz : publishedInterfaces) {
			String interfaceClassCanonicalName = clazz.getCanonicalName();
			if (!interfaceClassCanonicalName.equals("void")) {
				Map<String, Long> methodNames = new HashMap<String, Long>();
				for (Method method : clazz.getMethods()) {
					methodNames.put(method.getName(), ProviderInvokeStaticsFilter.getValue(clazz, method.getName()));
				}
				publishedInterfaceList.put(interfaceClassCanonicalName, methodNames);
			}
		}
		if (!publishedInterfaceList.isEmpty()) {
			info.put("publishedInterfaces", publishedInterfaceList);
		}
		// subscribed services
		Set<Class> subscribedInterfaces = ConsumerSubscribeListener.subscribedInterfaces;
		if (!subscribedInterfaces.isEmpty()) {
			try {
				Map<String, Map<String, Long>> subscribedInterfaceList = new HashMap<String, Map<String, Long>>();
				for (Class<?> clazz : subscribedInterfaces) {
					Map<String, Long> methodNames = new HashMap<String, Long>();
					for (Method method : clazz.getMethods()) {
						methodNames.put(method.getName(),
								ConsumerInvokeStaticsFilter.getValue(clazz, method.getName()));
					}
					subscribedInterfaceList.put(clazz.getCanonicalName(), methodNames);
				}
				info.put("subscribedInterfaces", subscribedInterfaceList);
			} catch (Exception ignore) {

			}
			info.put("connections", ConsumerSubscribeListener.connections);
		}
		return info;
	}
}
