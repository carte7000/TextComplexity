package org.ift7022.tp3.ngrams;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class InMemoryNgramRepository implements NgramRepository{
	
	Map<String, Ngram> dict = new HashMap<String, Ngram>();
	
	public void persist(String word){
	}
	
	public Ngram getNgram(String word) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Collection<Ngram> findAll(){
		return dict.values();
	}
}
