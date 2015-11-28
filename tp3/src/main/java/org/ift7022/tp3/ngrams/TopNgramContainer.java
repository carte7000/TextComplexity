package org.ift7022.tp3.ngrams;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TopNgramContainer implements NgramObserver {
	Map<String[], Integer> container = new HashMap<String[], Integer>();
	Heap<Ngram> heap;
	public TopNgramContainer() {
		heap = new Heap<Ngram>(new Comparator<Ngram>() {
			public int compare(Ngram o1, Ngram o2) {
				return new Double(o1.getLogProbability()).compareTo(new Double(o2.getLogProbability()));
			}
		});
	}

	public void Lock(){
		Iterator<Ngram> iterator = heap.iterator();
		Integer rank = 1;

		while (iterator.hasNext()) {
			container.put(iterator.next().getValue(), rank);
			rank += 1;
		}
	}
	
	public boolean isTopWord(String word) {
		return container.containsKey(word);
	}

	public Integer getRank(String word) {
		return container.get(word);
	}

	public void notify(String[] key, long count) {
		// TODO Auto-generated method stub
		
	}

}
