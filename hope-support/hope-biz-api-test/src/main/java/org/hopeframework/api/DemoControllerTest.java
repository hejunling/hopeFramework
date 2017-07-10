package org.hopeframework.api;

import org.hopeframework.entity.input.DemoInput;
import org.hopeframework.test.api.BaseAPI;
import org.hopeframework.test.enums.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * demo test
 * 
 * 请求：{"request":{"common":{"action":"demoDetail","reqtime":"20170328104456"},"content":{"accessId":"accessId","requestType":"1","userNo":"hc","ip":null,"pageSize":20,"currentPage":1,"phone":"13666666667"}}}
 * 响应：{"response":{"info":{"code":100011,"msg":""},"content":null}}
 * 
 * @author hechuan
 *
 * @created 2017年4月11日
 *
 * @version hopeframework-1.0.0
 * 
 * @since 1.0.0
 */
public class DemoControllerTest extends BaseAPI {
	
	@Before
    public void setUp(){
        this.url = "http://localhost:10001";
        this.accessId = "accessId";
        this.accessKey = "accessKey";
    }

    /**
     * 用户详细查询
     */
    @Test
    public void demoDetail(){
        try{
            DemoInput input = new DemoInput();
            input.setUserNo("hc");
            input.setRequestType("1");
            input.setAccessId(accessId);
            input.setPhone("13666666667");
            doService("demoDetail", ContentType.JSON, input);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 用户详细查询
     */
    @Test
    public void demoList(){
    	try{
    		DemoInput input = new DemoInput();
    		input.setUserNo("hc");
    		input.setRequestType("1");
    		input.setAccessId(accessId);
    		input.setPhone("13666666667");
    		input.setPageSize(2);
    		input.setCurrentPage(1);
    		doService("demoList", ContentType.JSON, input);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 用户详细查询
     */
    @Test
    public void demoPageList(){
    	try{
    		DemoInput input = new DemoInput();
    		input.setUserNo("hc");
    		input.setRequestType("1");
    		input.setAccessId(accessId);
    		input.setPhone("1366666");
    		input.setPageSize(2);
    		input.setCurrentPage(1);
    		doService("demoPageList", ContentType.JSON, input);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 用户详细查询
     */
    @Test
    public void saveDemo(){
    	try{
    		DemoInput input = new DemoInput();
    		input.setUserNo("hc");
    		input.setRequestType("1");
    		input.setAccessId(accessId);
    		input.setPhone("1366666");
    		input.setPageSize(2);
    		input.setCurrentPage(1);
    		doService("saveDemo", ContentType.JSON, input);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 更新DEMO
     */
    @Test
    public void updateDemo(){
    	try{
    		DemoInput input = new DemoInput();
    		input.setUserNo("hc");
    		input.setRequestType("1");
    		input.setAccessId(accessId);
    		input.setPhone("1366666");
    		input.setPageSize(2);
    		input.setCurrentPage(1);
    		doService("updateDemo", ContentType.JSON, input);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 更新DEMO
     */
    @Test
    public void transDemo(){
    	try{
    		DemoInput input = new DemoInput();
    		input.setUserNo("hc");
    		input.setRequestType("1");
    		input.setAccessId(accessId);
    		input.setPhone("13666667");
    		input.setPageSize(2);
    		input.setCurrentPage(1);
    		doService("transDemo", ContentType.JSON, input);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }


	/**
	 * request DEMO
	 */
	@Test
	public void requestDemo(){
		try{
			DemoInput input = new DemoInput();
			input.setUserNo("hc23");
			input.setRequestType("1");
			input.setAccessId(accessId);
			input.setPhone("136666689");

			doService("requestDemo", ContentType.JSON, input);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    @After
    public void complete() {
    	responseJSON();
    }


}
