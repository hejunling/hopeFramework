package org.hopeframework.dubbo.listener;

import com.google.common.base.Throwables;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.UncheckedExecutionException;

import com.alibaba.dubbo.rpc.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 调用统计过滤器
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
public abstract class StaticsFilter implements Filter {

	/** 默认日期格式 */
	public static final String DEFAULT_TIME_FORMAT = "yyyyMMddHHmmssSSS";

	/** 缓存key名中字段之间的分割符 */
	public static final String KEY_SPILT_CHAR = ".";

	/** 调用次数统计 */
	public static Map<String, AtomicLong> countStatics = Maps.newConcurrentMap();

	/** 每次调用花费时间缓存 */
	public static Cache<String, Long> timeStatics = CacheBuilder.newBuilder()
			// 回收的参数设置
			// 最大保存10000条
			.maximumSize(10000)
			// key为弱引用
			.weakKeys()
			// 值为软引用
			.softValues()
			// 再没有写操作过了1天后，自动销毁
			.expireAfterWrite(1, TimeUnit.DAYS).build();

	/** 日志操作相关 */
	protected static Logger logger;

	/** 调用开始时间 */
	protected static ThreadLocal<Long> startTime = new ThreadLocal<Long>();

	/** 调用次数统计的Key缓存 */
	private static final LoadingCache<StatisticsKey, String> flattenHierarchyCache = CacheBuilder.newBuilder()
			.weakKeys().build(new CacheLoader<StatisticsKey, String>() {
				@SuppressWarnings("RedundantTypeArguments") // <Class<?>> is
															// actually needed
															// to compile
				@Override
				public String load(StatisticsKey invocation) {
					return newKey(invocation.getCanonicalName(), KEY_SPILT_CHAR, invocation.getMethodName());
				}
			});

	public StaticsFilter() {
		logger = LoggerFactory.getLogger(this.getClass());
	}

	/**
	 * 取得当前调用次数
	 *
	 * @param clazz
	 *            方法所在类
	 * @param methodName
	 *            方法名
	 * @return 当前调用次数
	 */
	public static long getValue(Class clazz, String methodName) {
		String key = flattenHierarchy(new StatisticsKey(clazz.getCanonicalName(), methodName));
		AtomicLong value = countStatics.get(key);
		return value != null ? value.get() : 0;
	}

	/**
	 * 取得当前调用次数
	 *
	 * @param invocation
	 *            统计缓存中的key
	 * @return 当前调用次数
	 */
	public static long getValue(StatisticsKey invocation) {
		String key = flattenHierarchy(invocation);
		AtomicLong value = countStatics.get(key);
		return value != null ? value.get() : 0;
	}

	/**
	 * 调用开始前
	 *
	 * @return 记录调用时间
	 */
	protected long beforeInvoke() {
		long startLong = System.currentTimeMillis();
		startTime.set(startLong);
		return startLong;
	}

	/**
	 * 调用次数统计
	 *
	 * @param invocation
	 *            dubbo的rpc调用唤醒类
	 */
	protected static void increase(StatisticsKey invocation) {
		String key = flattenHierarchy(invocation);
		if (!countStatics.containsKey(key)) {
			countStatics.put(key, new AtomicLong(0));
		}
		countStatics.get(key).incrementAndGet();
	}

	/**
	 * 调用结束
	 *
	 * @param statisticsKey
	 *            缓存key的包装类
	 * @return 调用花费时间
	 */
	protected long afterInvoke(StatisticsKey statisticsKey) {

		// 取得调用开始时间
		long startTime = this.startTime.get();
		// 结束时间
		long endTime = System.currentTimeMillis();
		// 调用消耗时间
		long cost = endTime - startTime;
		// 当前调用次数
		long invokeNum = getValue(statisticsKey);
		// 记录消耗时间的缓存的key
		String key = newKey(flattenHierarchy(statisticsKey), KEY_SPILT_CHAR, String.valueOf(invokeNum));
		// 将本次调用耗费时间记录到缓存中
		timeStatics.put(key, cost);

		// 调用日志
		logger.info("service execute startTime = {},endTime = {},endTime - startTime = {} ms",
				getSysTimeSLong(startTime), getSysTimeSLong(endTime), cost);

		// 清空当前的缓存开始时间
		this.startTime.remove();

		return cost;
	}

	/**
	 * 取得当前时间
	 *
	 * @return 返回时间 格式为{@code DEFAULT_TIME_FORMAT}(yyyyMMddHHmmssSSS)
	 */
	protected String getSysTimeSLong(Long unit) {

		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIME_FORMAT, Locale.ENGLISH);

		return format.format(new Date(unit));
	}

	/**
	 * 创建key
	 *
	 * @param keys
	 *            用来创建key的字段列表
	 * @return 创建好的key
	 */
	private static String newKey(String... keys) {

		StringBuffer sb = new StringBuffer();

		// 取得key
		for (String str : keys) {
			sb.append(str);
		}
		String key = sb.toString();
		// 清空
		sb.delete(0, sb.length());

		return key;
	}

	/**
	 * 取得统计用户缓存key
	 *
	 * @param invocation
	 *            统计缓存中的key
	 * @return 缓存key
	 */
	private static String flattenHierarchy(StatisticsKey invocation) {
		try {
			return flattenHierarchyCache.getUnchecked(invocation);
		} catch (UncheckedExecutionException e) {
			throw Throwables.propagate(e.getCause());
		}
	}

	/** 统计缓存中的key */
	protected static final class StatisticsKey {

		private final String canonicalName;

		private final String methodName;

		public StatisticsKey(String canonicalName, String methodName) {
			this.canonicalName = canonicalName;
			this.methodName = methodName;
		}

		public String getCanonicalName() {
			return canonicalName;
		}

		public String getMethodName() {
			return methodName;
		}
	}

}
