package com.xavier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

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
		jedis.zadd("a", 15, "a:15");
	}

	@After
	public void flushDB() {
		jedis.flushDB();
	}
}
