package com.silence.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisJava {
	public static void main(String[] args) {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());
		jedis.set("tutorial-name", "Redis tutorial");
		System.out.println("Stored string in redis:: " + jedis.get("tutorial-name"));

		jedis.lpush("tutorial-list", "Redis");
		jedis.lpush("tutorial-list", "Mongodb");
		jedis.lpush("tutorial-list", "Mysql");
		// Get the stored data and print it
		List<String> list = jedis.lrange("tutorial-list", 0, 5);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Stored string in redis:: " + list.get(i));
		}

	}
}