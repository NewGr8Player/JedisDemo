package com.xavier;

import redis.clients.jedis.Jedis;

public class Test {
	public static void main(String[] args) {
		Jedis jedis = Pool.getJedis();
		jedis.set("TestKey","TestValue");
		System.out.println(jedis.get("TestKey"));
	}
}
