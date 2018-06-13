package com.xavier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

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
		//从左边（头部）添加元素，返回列表的长度
		long len = jedis.lpush("chars", "a", "b", "c", "d");  //此时 chars为：d c b a
		System.out.println(len);

		//从右边（尾部）添加元素，返回列表的长度
		len = jedis.rpush("chars", "1", "2", "3", "4", "5");  //此时 chars为： d c b a 1 2 3 4 5
		System.out.println(len);

		//获取列表的长度
		len = jedis.llen("chars");
		System.out.println(len);

		//获取列表元素，0:索引开始位置
		List<String> list = jedis.lrange("chars", 0, -1);
		for (String s : list) {
			System.out.print(s + " ");   //输出  d c b a 1 2 3 4 5
		}


		//删除指定的相同的元素，返回删除成功的元素个数
		jedis.rpush("fruit", "apple", "apple", "banana", "apple");
		long r = jedis.lrem("fruit", 2, "apple");   //从左往右删除，删除最先出现的2个"apple"元素
		System.out.println("\n" + r);
		list = jedis.lrange("fruit", 0, -1);
		for (String s : list) {
			System.out.print(s + " ");   //banana apple
		}

		//弹出列表中的左边第一个元素
		String s = jedis.lpop("chars");
		System.out.println(s);

		//弹出列表右边的最后一个元素
		s = jedis.rpop("chars");

		//往列表左边插入元素，列表必须存在，如果不存在，不做任何操作
		jedis.lpushx("char888", "P");

		//往列表右边插入元素，列表必须存在，如果不存在，不做任何操作
		jedis.rpushx("chars999", "X");

		//修改列表中指定索引的元素
		jedis.lset("chars", 2, "Z");

		//返回指定索引的元素
		String str = jedis.lindex("chars", 1);

		//截取并保存列表中指定范围内的元素
		jedis.ltrim("chars", 2, 5);  //保留索引为2,3,4,5的四个元素

		//弹出chars1的最右边一个元素到chars2列表的最左边
		jedis.rpush("chars1", "a", "b", "c");      //chars1: a b c
		jedis.rpush("chars2", "1", "2", "3", "4");  //chars2: 1 2 3 4
		jedis.rpoplpush("chars1", "chars2");   //chars1:a b    chars2: c 1 2 3 4
	}

	@After
	public void flushDB() {
		jedis.flushDB();
		jedis.quit();
	}
}
