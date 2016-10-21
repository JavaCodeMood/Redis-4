package com.silence.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestRedis {

	private Jedis jedis;

	@Before
	public void setup() {
		
		jedis = new Jedis("localhost");

	}
	/**
	 * 字符串操作
	 */
	@Test
	public void testString(){
		jedis.set("name","xinxin");//向key-->name中放入了value-->xinxin
		System.out.println(jedis.get("name"));//执行结果：xinxin  
		
		jedis.append("name", " is my lover"); //拼接
		System.out.println(jedis.get("name"));
		
		jedis.del("name");  //删除某个键
		System.out.println(jedis.get("name"));
		
		//设置多个键值对
		jedis.mset("name","liuling","age","23","qq","476777XXX");
		jedis.incr("age"); //进行加1操作
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
	}
	
	/**
	 * Map操作
	 */
	@Test
	public void testMap(){
		//添加数据
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xinxin");
		map.put("age", "22");
		map.put("qq", "123456");
		jedis.hmset("user",map);
		//取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
		//第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
		List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
		System.out.println(rsmap);
		
		//删除map中的某个键值
		jedis.hdel("user","age");
		System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
		System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
		
		
	}
}
