package org.hopeframework.dubbo.listener;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 生产者调用统计过滤器
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@Activate(group = Constants.PROVIDER)
public class ProviderInvokeStaticsFilter extends StaticsFilter {

	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		// 调用开始前
		beforeInvoke();

		// 生成统计信息缓存key的包装类
		StatisticsKey statisticsKey = new StatisticsKey(invoker.getInterface().getCanonicalName(),
				invocation.getMethodName());

		// 统计调用次数
		increase(statisticsKey);

		try {
			return invoker.invoke(invocation);
		} finally {
			// 调用结束
			afterInvoke(statisticsKey);
		}

	}

}
