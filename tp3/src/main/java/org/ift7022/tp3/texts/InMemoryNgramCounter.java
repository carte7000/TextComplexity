package org.ift7022.tp3.texts;

import org.ift7022.tp3.ngrams.Ngram;
import org.ift7022.tp3.ngrams.NgramCounter;

import java.util.HashMap;
import java.util.Map;

public class InMemoryNgramCounter implements NgramCounter {

	private int count = 0;
	private Map<String, Integer> ngramDict = new HashMap<String, Integer>();
	
	public void persist(String word) {
		createIfDoNotExists(word);
		incrementValue(word);
	}

	private void createIfDoNotExists(String word) {
		if(!ngramDict.containsKey(word)){
			ngramDict.put(word, 0);
		}
	}

	private void incrementValue(String word) {
		ngramDict.put(word, ngramDict.get(word)+1);
		count+=1;
	}

	public int getTotalCount() {
		return count;
	}

	public Ngram getNgram(String value) {
		createIfDoNotExists(value);
		return new Ngram(value, ngramDict.get(value), count);
	}

}
