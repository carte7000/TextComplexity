package org.ift7022.tp3.texts;

import java.util.HashMap;
import java.util.Map;

import org.ift7022.tp3.ngrams.Ngram;
import org.ift7022.tp3.ngrams.NgramCounter;
import org.ift7022.tp3.ngrams.NgramRepository;

public class InMemoryNgramCounter implements NgramCounter {

	private int count = 0;
	private Map<String[], Integer> ngramDict = new HashMap<String[], Integer>();

	public void persist(String[] ngram) {
		createIfDoNotExists(ngram);
		incrementValue(ngram);
	}

	private void createIfDoNotExists(String[] word) {
		if (!ngramDict.containsKey(word)) {
			ngramDict.put(word, 0);
		}
	}

	private void incrementValue(String[] ngram) {
		ngramDict.put(ngram, ngramDict.get(ngram) + 1);
		count += 1;
	}

	public int getTotalCount() {
		return count;
	}

	public Ngram getNgram(String[] value) {
		createIfDoNotExists(value);
		return new Ngram(value, ngramDict.get(value), count);
	}

	public Double computePerplexity(NgramRepository repsitory) {
		Double perplixity = 0.0;
		for (String[] key : ngramDict.keySet()) {
			Ngram realNgram = repsitory.getNgram(key);
			perplixity += realNgram.getLogProbability();
		}
		perplixity = perplixity / getTotalCount();
		return Math.exp(perplixity);
	}

}
