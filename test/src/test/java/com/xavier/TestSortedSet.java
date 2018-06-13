package com.xavier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class TestSortedSet {

	private Jedis jedis;

	@Before
	public void before() {
		jedis = new Jedis("127.0.0.1");
	}

	/**
	 * Sorted Set
	 */
	@Test
	public void sortedSetTest() {
		//添加元素
		jedis.zadd("score", 100, "Higgin");
		jedis.zadd("score", 90, "zhangsan");
		jedis.zadd("score", 80.5, "lisi");
		jedis.zadd("score", 70, "wangwu");
		jedis.zadd("score", 60.5, "maliu");
		jedis.zadd("score", 50, "songqi");

		//获取所有元素
		Set<String> set = jedis.zrange("score", 0, -1);
		for (String s : set) {
			System.out.println(s);
		}

		//获取指定分数范围内的元素的数量
		long count = jedis.zcount("score", 60, 90);  //包含60和90
		System.out.println(count);

		//为指定的元素增加分值，返回新的分数
		Double newCount = jedis.zincrby("score", 8, "lisi");
		System.out.println(newCount);

		//返回某个元素的分数的排名（从小到大排名,第1名排名为0，第2名排名为1）
		long rank = jedis.zrank("score", "zhangsan");
		System.out.println("rank=" + rank);

		//返回某个元素的分数的排名（从大到小）
		rank = jedis.zrevrank("score", "zhangsan");
		System.out.println("rank=" + rank);

		//获取对应的元素的分数
		Double score = jedis.zscore("score", "Higgin");
		System.out.println("score=" + score);

		//获取指定排名范围内的元素 （按从小到大排序）
		set = jedis.zrange("score", 0, 2);
		for (String s : set) {
			System.out.println("--" + s);
		}
		//获取指定排名范围内的元素 （按从大到小排序）
		set = jedis.zrevrange("score", 0, 2);
		for (String s : set) {
			System.out.println("~~" + s);
		}

		//删除元素，返回删除成功的元素的数量
		count = jedis.zrem("score", "zhangsan", "lisi");
		System.out.println(count);
	}

	@After
	public void flushDB() {
		jedis.flushDB();
		jedis.quit();
	}
}
