package org.hopeframework.client.biz.service;

import java.util.List;

import org.hopeframework.core.exception.HopeException;
import org.hopeframework.db.model.demo.Demo;
import org.hopeframework.entity.input.DemoInput;
import org.hopeframework.entity.output.DemoOutput;

import com.github.pagehelper.PageInfo;

/**
 * DEMO 服务
 * 
 * @author hechuan
 *
 * @created 2017年4月10日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
public interface IDemoService {

	String VERSION = "1.0.0";

	/**
	 * 获取DEMO LIST
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	List<DemoOutput> getDemoList(DemoInput input) throws HopeException;

	/**
	 * 获得DEMO实例
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	DemoOutput getDemoEntity(DemoInput input) throws HopeException;

	/**
	 * 获取DEMO PAGE LIST 分页
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	PageInfo<Demo> getDemoPageList(DemoInput input) throws HopeException;

	/**
	 * 保存demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	int saveDemo(DemoInput input) throws HopeException;

	/**
	 * 更新demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	int updateDemo(DemoInput input) throws HopeException;

	/**
	 * 事物检查
	 * 
	 * @param input
	 *            输入参数
	 * @return 影响行数
	 * @throws HopeException
	 */
	int testTransactional(DemoInput input) throws HopeException;
}
