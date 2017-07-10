package org.hopeframework.biz.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import org.hopeframework.client.biz.service.IDemoService;
import org.hopeframework.core.annotation.AccessToken;
import org.hopeframework.core.annotation.HopeReqBody;
import org.hopeframework.core.enums.AccessType;
import org.hopeframework.core.response.RespBody;
import org.hopeframework.db.model.demo.Demo;
import org.hopeframework.entity.input.DemoInput;
import org.hopeframework.entity.output.DemoOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * demo controller
 * 
 * @author hechuan
 *
 * @created 2017年4月11日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@RestController
@HopeReqBody
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@Reference(version = IDemoService.VERSION)
	private IDemoService demoService;

	/**
	 * demo detail
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@AccessToken(access = AccessType.WEB)
	@RequestMapping(value = "demoDetail", method = RequestMethod.POST)
	public RespBody<DemoOutput> demoDetail(DemoInput input) {

		// demo entity
		DemoOutput demoEntity = demoService.getDemoEntity(input);

		return new RespBody<DemoOutput>(demoEntity);
	}

	/**
	 * demo list
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@AccessToken(access = AccessType.WEB)
	@RequestMapping(value = "demoList", method = RequestMethod.POST)
	public RespBody<List<DemoOutput>> getDemoList(DemoInput input) {

		// demo list
		List<DemoOutput> demoList = demoService.getDemoList(input);

		return new RespBody<List<DemoOutput>>(demoList);
	}

	/**
	 * demo page list
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@AccessToken(access = AccessType.WEB)
	@RequestMapping(value = "demoPageList", method = RequestMethod.POST)
	public RespBody<PageInfo<Demo>> getDemoPageList(DemoInput input) {

		// demo page list
		PageInfo<Demo> demoPageList = demoService.getDemoPageList(input);

		return new RespBody<PageInfo<Demo>>(demoPageList);
	}

	/**
	 * save demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@AccessToken(access = AccessType.WEB)
	@RequestMapping(value = "saveDemo", method = RequestMethod.POST)
	public RespBody<String> saveDemo(DemoInput input) {

		demoService.saveDemo(input);

		return new RespBody<>();
	}

	/**
	 * update demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@AccessToken(access = AccessType.WEB)
	@RequestMapping(value = "updateDemo", method = RequestMethod.POST)
	public RespBody<String> updateDemo(DemoInput input) {

		demoService.updateDemo(input);

		return new RespBody<>();
	}

	/**
	 * test transactional
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@AccessToken(access = AccessType.WEB)
	@RequestMapping(value = "transDemo", method = RequestMethod.POST)
	public RespBody<String> transDemo(DemoInput input) {

		demoService.testTransactional(input);

		return new RespBody<>();
	}

	@AccessToken(access = AccessType.WEB)
	@RequestMapping(value = "requestDemo", method = RequestMethod.POST)
	public RespBody<String> requestDemo(DemoInput input,HttpServletRequest request){
		logger.debug(input.toString());
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			logger.debug(names.nextElement());
		}
		HttpSession session = request.getSession();
		logger.debug("session = {}",session);

		session.setAttribute("phone","13921546873");

		Object phone = session.getAttribute("phone");
		logger.info("phone = {}",phone);

		return new RespBody<>();
	}
}
