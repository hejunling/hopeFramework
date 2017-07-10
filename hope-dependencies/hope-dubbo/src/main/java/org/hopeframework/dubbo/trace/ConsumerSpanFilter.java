package org.hopeframework.dubbo.trace;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanInjector;
import org.springframework.cloud.sleuth.Tracer;

import java.util.Map;

/**
 * 分布式日志，dubbo消费端拦截
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@Activate(group = { Constants.CONSUMER }, order = -9000)
public class ConsumerSpanFilter implements Filter {

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		boolean isTraceDubbo = false;
		Tracer tracer = null;
		SpanInjector spanInjector = null;
		try {
			Map<String, String> attachments = RpcContext.getContext().getAttachments();
			tracer = ApplicationContextAwareBean.CONTEXT.getBean(Tracer.class);
			spanInjector = ApplicationContextAwareBean.CONTEXT.getBean(DubboSpanInjector.class);
			isTraceDubbo = (tracer != null && spanInjector != null);
			if (isTraceDubbo) {
				String spanName = invoker.getUrl().getParameter("interface") + ":" + invocation.getMethodName() + ":"
						+ invoker.getUrl().getParameter("version") + "(" + invoker.getUrl().getHost() + ")";
				Span newSpan = tracer.createSpan(spanName);
				spanInjector.inject(newSpan, RpcContext.getContext());
				newSpan.logEvent(Span.CLIENT_SEND);
			}

			Result result = invoker.invoke(invocation);
			return result;

		} finally {
			if (isTraceDubbo) {
				if (tracer.isTracing()) {
					tracer.getCurrentSpan().logEvent(Span.CLIENT_RECV);
					tracer.close(tracer.getCurrentSpan());
				}
			}
		}
	}

}
