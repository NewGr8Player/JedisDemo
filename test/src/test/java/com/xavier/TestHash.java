package com.xavier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestHash {

	private Jedis jedis;


	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
	}

	/**
	 * map
	 */
	@Test
	public void hashTest() {
		//添加hash元素,hset每次只能添加一个成员变量
		long r=jedis.hset("user:1001", "name", "higgin");
		r=jedis.hset("user:1001", "age","18");
		System.out.println(r);

		//如果key不存在则添加，负责操失败。(key存在返回0，反之返回1)
		r=jedis.hsetnx("user:1001", "name", "6666");
		System.out.println(r);
		r=jedis.hsetnx("user:1001", "email", "123@qq.com");
		System.out.println(r);

		//获取某一个key的值
		String name=jedis.hget("user:1001", "name");
		System.out.println(name);

		//获取所有字段的map类型数据
		Map<String,String> map=jedis.hgetAll("user:1001");
		for(Entry<String, String> entry:map.entrySet()){
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}

		//获取所有字段的值
		List<String> values=jedis.hvals("user:1001");
		for(String v:values){
			System.out.println("value:"+v);
		}

		//删除某个字段,返回删除成功的个数
		long dr=jedis.hdel("user:1001", "name","email","haha");
		System.out.println(dr);

		//判断某个key的field字段是否存在
		boolean b=jedis.hexists("user:1001", "name");

		//对某个field累加指定的数值，返回累加后的值(如果希望减，可以给个 负数的参数)
		r=jedis.hincrBy("user:1001", "age", 5);
		System.out.println(r);

		//对某个浮点类型的field累加指定的数值
		jedis.hset("user:1001", "height", "170.00");
		double h=jedis.hincrByFloat("user:1001", "height", 2);
		System.out.println(h);

		//获取hash的field字段数量
		long num=jedis.hlen("user:1001");
		System.out.println(num);

		//设置多个字段的值,如果成功返回"OK"字符串
		Map<String,String> userMap=new HashMap<String,String>();
		userMap.put("name", "Jack");
		userMap.put("age", "19");
		String isOK=jedis.hmset("user:1002",userMap);
		System.out.println(isOK);

		//获取多个字段的值
		List<String> list=jedis.hmget("user:1001", "age","name");

	}

	@After
	public void flushDB(){
		jedis.flushDB();
		jedis.quit();
	}
}
