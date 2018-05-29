package com.xavier;

import redis.clients.jedis.Jedis;

public class StringTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost",6379);
		jedis.select(0);
		System.out.println(jedis.get("test"));
		jedis.setex("key",10,"value10s");
		System.out.println(jedis.get("key"));
	}
}
