package com.qf.utils;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
/**
 * 配置文件反射获取对象
 * @author xiu
 *
 */
public class ObjectUtils {
	private static Properties properties;
	static{
		properties = new Properties();
		try {
			properties.load(ObjectUtils.class.getClassLoader().getResourceAsStream("object.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Object getObj(String name) throws Exception {
		String property = properties.getProperty(name);
		Class forName = Class.forName(property); 
		return forName.newInstance();
	}
	
	/*@Test
	public void t1(){
		Properties properties = new Properties();
		try {
			properties.load(ObjectUtils.class.getClassLoader().getResourceAsStream("object.properties"));
			System.out.println(properties.getProperty("userDao"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	

}
