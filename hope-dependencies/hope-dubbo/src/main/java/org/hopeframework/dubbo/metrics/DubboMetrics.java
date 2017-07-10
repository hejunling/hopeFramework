package org.hopeframework.dubbo.metrics;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.hopeframework.dubbo.listener.ConsumerInvokeStaticsFilter;
import org.hopeframework.dubbo.listener.ProviderInvokeStaticsFilter;
import org.hopeframework.dubbo.listener.StaticsFilter;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

/**
 * Dubbo 性能度量数据
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
public class DubboMetrics implements PublicMetrics {

	/** dubbo消费者性能数据前缀 */
	private static final String CONSUMER_PREFIX = "dubbo.consumer.";

	/** dubbo生产者性能数据前缀 */
	private static final String PROVIDER_PREFIX = "dubbo.provider.";

	/**
	 * 性能数据收集
	 * 
	 * @return 性能数据集合
	 */
	public Collection<Metric<?>> metrics() {

		List<Metric<?>> metrics = new LinkedList<Metric<?>>();

		// 消费者调用次数统计
		if (!ConsumerInvokeStaticsFilter.countStatics.isEmpty()) {
			for (Map.Entry<String, AtomicLong> entry : ConsumerInvokeStaticsFilter.countStatics.entrySet()) {
				metrics.add(new Metric<Number>(CONSUMER_PREFIX + entry.getKey(), entry.getValue().get()));
			}
		}
		// 消费者调用花费时间统计
		if (StaticsFilter.timeStatics.size() > 0) {
			for (Map.Entry<String, Long> entry : ConsumerInvokeStaticsFilter.timeStatics.asMap().entrySet()) {
				metrics.add(new Metric<Number>(CONSUMER_PREFIX + entry.getKey(), entry.getValue()));
			}
		}
		// 生成者调用次数统计
		if (!ProviderInvokeStaticsFilter.countStatics.isEmpty()) {
			for (Map.Entry<String, AtomicLong> entry : ProviderInvokeStaticsFilter.countStatics.entrySet()) {
				metrics.add(new Metric<Number>(PROVIDER_PREFIX + entry.getKey(), entry.getValue().get()));
			}
		}
		// 生成者调用花费时间统计
		if (ProviderInvokeStaticsFilter.timeStatics.size() > 0) {
			for (Map.Entry<String, Long> entry : ProviderInvokeStaticsFilter.timeStatics.asMap().entrySet()) {
				metrics.add(new Metric<Number>(PROVIDER_PREFIX + entry.getKey(), entry.getValue()));
			}
		}
		return metrics;
	}

}
