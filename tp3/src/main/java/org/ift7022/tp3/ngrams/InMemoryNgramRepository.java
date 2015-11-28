package org.ift7022.tp3.ngrams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InMemoryNgramRepository{

	Map<String[], Long> dict = new HashMap<String[], Long>();
	Long totalCount = (long) 0;

	public InMemoryNgramRepository(String pathFile, Parser parser) {
		ParseResult result = null;// = parser.parse(pathFile);
		dict = result.dict;
		totalCount = result.totalCount;
	}

	public Ngram getNgram(String[] word) {
		return new Ngram(word, dict.get(word), totalCount);
	}

	public Collection<Ngram> findAll() {
		Collection<Ngram> result = new ArrayList<Ngram>();
		for (String[] key : dict.keySet()) {
			Ngram ngram = this.getNgram(key);
			result.add(ngram);
		}
		return result;
	}
}
