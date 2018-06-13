package com.xavier;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, String> map = new HashMap<>();
		map.put("name", "fujianchao");
		map.put("password", "123");
		map.put("age", "12");
		// 存入一个map
		jedis.hmset("user", map);

		// map key的个数
		System.out.println("map的key的个数" + jedis.hlen("user"));

		// map key
		System.out.println("map的key" + jedis.hkeys("user"));

		// map value
		System.out.println("map的value" + jedis.hvals("user"));

		// (String key, String... fields)返回值是一个list
		List<String> list = jedis.hmget("user", "age", "name");
		System.out.println("redis中key的各个 fields值："
				+ jedis.hmget("user", "age", "name") + list.size());

		// 删除map中的某一个键 的值 password
		// 当然 (key, fields) 也可以是多个fields
		jedis.hdel("user", "age");

		System.out.println("删除后map的key" + jedis.hkeys("user"));

	}
}
