package com.xavier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

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
		//添加
		jedis.sadd("testname", "liuling");
		jedis.sadd("testname", "xinxin");
		jedis.sadd("testname", "ling");
		jedis.sadd("testname", "zhangxinxin");
		jedis.sadd("testname", "who");
		//移除
		jedis.srem("testname", "who");
		System.out.println(jedis.smembers("testname"));//获取所有加入的value
		System.out.println(jedis.sismember("testname", "who"));//判断 who 是否是user集合的元素
		System.out.println(jedis.srandmember("testname"));
		System.out.println(jedis.scard("testname"));//返回集合的元素个数
	}

	@After
	public void flushDB() {
		jedis.flushDB();
	}
}
