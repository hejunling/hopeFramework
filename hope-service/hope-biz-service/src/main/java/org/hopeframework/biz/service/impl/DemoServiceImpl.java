package org.hopeframework.biz.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hopeframework.client.biz.service.IDemoService;
import org.hopeframework.core.exception.HopeException;
import org.hopeframework.db.mapper.demo.DemoMapper;
import org.hopeframework.db.model.demo.Demo;
import org.hopeframework.db.model.demo.DemoExample;
import org.hopeframework.db.model.demo.DemoExample.Criteria;
import org.hopeframework.entity.input.DemoInput;
import org.hopeframework.entity.output.DemoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

/**
 * Demo service 实现
 * @author hechuan
 *
 * @created 2017年4月11日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
@Transactional
@Service(version = IDemoService.VERSION,interfaceName="org.hopeframework.client.biz.service.IDemoService")
public class DemoServiceImpl implements IDemoService {
	
	@Autowired
	private DemoMapper demoMapper;

	@Override
	public List<DemoOutput> getDemoList(DemoInput input) throws HopeException {
		DemoExample example = new DemoExample();
		Criteria cc = example.createCriteria();
		cc.andPhoneLike("%"+input.getPhone()+"%");
		List<Demo> demoList = demoMapper.selectByExample(example);
	
		List<DemoOutput> outList = Lists.newArrayList();
		for(Demo demo : demoList) {
			DemoOutput out = new DemoOutput();
			BeanUtils.copyProperties(demo, out);
			outList.add(out);
		}
		return outList;
		
	}

	@Override
	public DemoOutput getDemoEntity(DemoInput input) throws HopeException {
	
		DemoExample example = new DemoExample();
		Criteria cc = example.createCriteria();
		cc.andPhoneEqualTo(input.getPhone());
		List<Demo> demoList = demoMapper.selectByExample(example);
		Demo demo = demoList.get(0);
		
		DemoOutput out = new DemoOutput();
		BeanUtils.copyProperties(demo, out);
		return out;
	}

	@Override
	public PageInfo<Demo> getDemoPageList(DemoInput input) throws HopeException {
		
		DemoExample example = new DemoExample();
		Criteria cc = example.createCriteria();
		cc.andPhoneLike("%"+input.getPhone()+"%");
		PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
		List<Demo> demoList = demoMapper.selectByExample(example);
		return new PageInfo<Demo>(demoList);
	}

	@Override
	public int saveDemo(DemoInput input) throws HopeException {
		
		if(null == input || StringUtils.equals(input.getPhone(), "1366666")) {
			throw new HopeException(100001);
		}
		return 0;
	}

	@Override
	public int updateDemo(DemoInput input) throws HopeException {
		
		if(null == input || StringUtils.equals(input.getPhone(), "1366666")) {
			throw new HopeException(100001);
		}
		return 0;
	}

	@Override
	public int testTransactional(DemoInput input) throws HopeException {
		
		Demo demo = new Demo();
		BeanUtils.copyProperties(input, demo);
		demoMapper.insertSelective(demo);
		if(StringUtils.equals(input.getPhone(), "1366666")) {
			throw new HopeException(100001);
		}
		return 1;
	}

}
