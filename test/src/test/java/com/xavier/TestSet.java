package com.xavier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class TestSet {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
	}

	/**
	 * Sorted Set
	 */
	@Test
	public void setTest() {
		jedis.sadd("setTest", "m1", "m2");
	}

	@Test
	public void muliSetTest() {
		//添加元素，返回添加成功的元素个数
		long fruitNum = jedis.sadd("fruits", "apple", "banana", "lemen");
		System.out.println(fruitNum);   //输出3
		fruitNum = jedis.sadd("fruits", "apple", "pear", "blueberry");
		System.out.println(fruitNum);  //输出2

		//查询集合中的元素个数
		fruitNum = jedis.scard("fruits");
		System.out.println(fruitNum);

		//取多个集合的差集
		jedis.sadd("school1", "zhangsan", "lisi", "wangwu");
		jedis.sadd("school2", "zhangsan", "maliu");
		Set<String> s1 = jedis.sdiff("school1", "school2");  //取属于school1，但不属于school2的内容
		System.out.println(s1.size());  //s1中包含"lisi","wangwu"
		//取出school1和school2集合的差集，并存储在school3中
		jedis.sdiffstore("school3", "school1", "school2");
		System.out.println(jedis.scard("school3"));

		//取多个集合的交集
		jedis.sinter("school1", "school2");
		//取school1和school2的交集，存储在school4中
		jedis.sinterstore("school4", "school1", "school2");

		//取多个集合的并集
		jedis.sunion("school1", "school2");
		//取school1和school2的并集，并存储在school5中
		jedis.sunionstore("school5", "school1", "school2");

		//判断某个元素是否在某个集合中
		boolean b = jedis.sismember("school1", "zhangsan");

		//获取集合中的所有元素
		Set<String> school1 = jedis.smembers("school1");
		for (String s : school1) {
			System.out.println(s);
		}

		//从一个集合中移动指定的元素到另外一个集合中(把school1中的"lisi"移动到"school2"中)
		jedis.smove("school1", "school2", "lisi");
		for (String s : jedis.smembers("school2")) {
			System.out.println("school2:" + s);
		}

		//从集合中随机获取2个元素
		List<String> list = jedis.srandmember("school2", 2);


		//从集合中随机移除一个或多个元素，返回被移除的元素
//        Set<String> popObj=jedis.spop("fruits", 1);
//        System.out.println(popObj);

		//从集合中删除一个或多个指定的元素，返回删除的个数
		long remNum = jedis.srem("fruits", "apple", "blueberry", "123");
		System.out.println(remNum);
	}

	@After
	public void flushDB() {
		jedis.flushDB();
		jedis.quit();
	}
}
