package org.ift7022.tp3.ngrams;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TopNgramContainer {
	
	private static TopNgramContainer instance = null;
	
	public static TopNgramContainer getInstance(){
		if(instance == null){
			instance = new TopNgramContainer(new InMemoryNgramRepository().findAll());
		}
		
		return instance;
	}
	
	Map<String, Integer> container = new HashMap<String, Integer>();
	
	public TopNgramContainer(Collection<Ngram> ngrams){
		Heap<Ngram> heap = new Heap<Ngram>(new Comparator<Ngram>() {
			public int compare(Ngram o1, Ngram o2) {
				return new Double(o1.getLogProbability()).compareTo(new Double(o2.getLogProbability()));
			}
		});
		
		for(Ngram value : ngrams){
			heap.insert(value);
		}
		
		Iterator<Ngram> iterator = heap.iterator();
		Integer rank = 1;
		
		while(iterator.hasNext()){
			container.put(iterator.next().getValue(), rank);
			rank+=1;
		}
	}
	
	public boolean isTopWord(String word){
		return container.containsKey(word);
	}
	
	public Integer getRank(String word){
		return container.get(word);
	}
	
}
