package org.hopeframework.dubbo.listener;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 消费者调用统计过滤器
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@Activate(group = Constants.CONSUMER, order = -110000)
public class ConsumerInvokeStaticsFilter extends StaticsFilter {

	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		// 调用开始前
		beforeInvoke();

		// 生成统计信息缓存key的包装类
		StatisticsKey statisticsKey = new StatisticsKey(invocation.getClass().getCanonicalName(),
				invocation.getMethodName());

		// 统计调用次数
		increase(statisticsKey);

		try {
			Result invoke = invoker.invoke(invocation);
			return invoke;
		} finally {
			// 调用结束
			afterInvoke(statisticsKey);
		}
	}

}
