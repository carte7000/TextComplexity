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

public class InMemoryNgramRepository implements NgramRepository {

	Map<String[], Integer> dict = new HashMap<String[], Integer>();
	Integer totalCount = 0;

	public InMemoryNgramRepository(String pathFile) throws IOException {
		Path path = Paths.get(pathFile);
		Iterator<String> iterator = Files.lines(path).iterator();
		while (iterator.hasNext()) {
			String line = iterator.next();
			String[] stringArray = line.split(";");
			dict.put(new String[] { stringArray[0] }, Integer.parseInt(stringArray[1]));
			totalCount += Integer.parseInt(stringArray[1]);
		}

	}

	public Ngram getNgram(String[] word) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Ngram> findAll() {
		Collection<Ngram> result = new ArrayList<Ngram>();
		for (String[] key : dict.keySet()) {
			Ngram ngram = new Ngram(key, dict.get(key), totalCount);
			result.add(ngram);
		}
		return result;
	}
}
