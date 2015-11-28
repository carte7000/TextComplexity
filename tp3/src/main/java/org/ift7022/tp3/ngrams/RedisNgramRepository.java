package org.ift7022.tp3.ngrams;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisNgramRepository implements NgramRepository {

	private static final String SPECIAL_KEY_TOTAL_COUNT = "SpecialKey:TotalCount";
	Jedis jedis;
	Long totalCount = (long) 0;
	String subSection = "";

	public RedisNgramRepository(String pathFile, Parser parser) {
		jedis = new Jedis("localhost");
		parser.parse(pathFile, this);
		save();
		System.out.println(String.valueOf(getTotalCount()));
	}
	
	public RedisNgramRepository(String pathFile, Parser parser, String subSection) {
		this.subSection = subSection + "_";
		jedis = new Jedis("localhost");
		parser.parse(pathFile, this);
		save();
	}

	public RedisNgramRepository() {
		jedis = new Jedis("localhost");
	}

	public RedisNgramRepository(String subSection) {
		jedis = new Jedis("localhost");
		this.subSection = subSection + "_";
	}

	public void persist(String[] key, long value){
		totalCount+=value;
		//jedis.set(SPECIAL_KEY_TOTAL_COUNT, getTotalCount().add(new BigInteger(String.valueOf(value))).toString());
		jedis.set(subSection + formatKey(key), String.valueOf(value));
	}
	
	public void save(){
		jedis.set(subSection + SPECIAL_KEY_TOTAL_COUNT, String.valueOf(totalCount));
	}

	private long getTotalCount() {
		//BigInteger result = new BigInteger(jedis.get(SPECIAL_KEY_TOTAL_COUNT));
		String totalCount = jedis.get(subSection + SPECIAL_KEY_TOTAL_COUNT);
		return Long.parseLong(totalCount);
	}

	private String formatKey(String[] key){
		return String.join(";",key);
	}
	
	public Ngram getNgram(String[] word) {
		return new Ngram(word, getCount(word), getTotalCount());
	}

	private Long getCount(String[] word) {
		if(jedis.exists(subSection + formatKey(word))){
			return Long.parseLong(jedis.get(subSection + formatKey(word)));
		}
		return (long) 1;
	}
}
