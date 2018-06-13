package com.xavier;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestList {
	private Jedis jedis;


	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
	}


	/**
	 * list
	 */
	@Test
	public void listTest() {
		jedis.lpush("list", "aa");
		jedis.lpush("list", "bb");
		jedis.lpush("list", "cc");
		System.out.println(jedis.lrange("list", 0, -1));
		System.out.println(jedis.lrange("list", 0, 1));
		System.out.println(jedis.lpop("list")); // 栈顶
		jedis.del("list");
	}
}
