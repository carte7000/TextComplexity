package org.ift7022.tp3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ift7022.tp3.ngrams.OpenNlpPosTagger;
import org.ift7022.tp3.ngrams.PosTagger;

public class UniversalPosTagger implements PosTagger {

	private Map<String, String> dict = new HashMap<String, String>();
	private PosTagger internalPosTagger = null;
	
	public UniversalPosTagger(OpenNlpPosTagger openNlpTagger, String mapFile) {
		this.internalPosTagger = openNlpTagger;
		buildDict(mapFile);
	}

	private void buildDict(String mapFile) {
		Path path = Paths.get(mapFile);
		Iterator<String> iterator;
		iterator = createIterator(path);
		while (iterator.hasNext()) {
			String line = iterator.next();
			String[] stringArray = line.split("\t");
			dict.put(stringArray[0], stringArray[1]);
		}
	}
	
	private Iterator<String> createIterator(Path path) {
		Iterator<String> iterator = null;
		try {
			iterator = Files.lines(path).iterator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iterator;
	}

	public String[] tag(String[] sentence) {
		String[] taggedSentence = this.internalPosTagger.tag(sentence);
		return universialize(taggedSentence);
	}

	private String[] universialize(String[] taggedSentence) {
		ArrayList<String> result = new ArrayList<String>();
		for (String value : taggedSentence){
			String convertedValue = dict.get(value);
			result.add(convertedValue);
		}
		return result.toArray(new String[result.size()]);
	}

}
