package com.xavier;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSerializableObject {

	private Jedis jedis;


	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
	}

	/**
	 * <p>自定义对象 User为例 id name</p>
	 *
	 * RedisTemplate 中有 序列化和反序列化
	 * 如：template.getStringSerializer().serialize("name")
	 */
	@Test
	public void serializableObjectTest() {

		User user = new User();
		user.setId(123);
		user.setName("fighter");

		// 存入一个 user对象
		jedis.set("user".getBytes(), SerializationUtil.serialize(user));

		// 获取
		byte[] bs = jedis.get("user".getBytes());
		User desUser = (User) SerializationUtil.deserialize(bs);
		System.out.println(desUser);

	}


}
