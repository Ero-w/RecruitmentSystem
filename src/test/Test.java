package test;

import com.alibaba.fastjson.JSONObject;

public class Test {

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JSONObject object=JSONObject.parseObject("{user:'123',password:'123'}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
