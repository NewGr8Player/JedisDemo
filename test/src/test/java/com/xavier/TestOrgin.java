package com.xavier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestOrgin {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
	}

	/**
	 * 简单添加
	 */
	@Test
	public void singleTest() {
		String name = "name";
		String value = "qq";
		jedis.set(name, value);
		System.out.println("追加前：" + jedis.get(name)); // 追加前：qq

		// 在原有值得基础上添加,如若之前没有该key，则导入该key
		jedis.append(name, "ww");
		System.out.println("追加后：" + jedis.get(name)); // 追加后：qqww

		jedis.append("id", "ee");
		System.out.println("没此key：" + jedis.get(name));
		System.out.println("get此key：" + jedis.get("id"));

	}

	/**
	 * mset 是设置多个key-value值 参数（key1,value1,key2,value2,...,keyn,valuen） mget
	 * 是获取多个key所对应的value值 参数（key1,key2,key3,...,keyn） 返回的是个list
	 */
	@Test
	public void muliTest() {
		jedis.mset("name1", "aa", "name2", "bb", "name3", "cc");
		System.out.println(jedis.mget("name1", "name2", "name3"));
	}

	@After
	public void flushDB(){
		jedis.flushDB();
	}
}
