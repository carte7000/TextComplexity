package org.ift7022.tp3;

import java.util.ArrayList;

import org.ift7022.tp3.ngrams.OpenNlpPosTagger;
import org.ift7022.tp3.ngrams.PosTagger;

public class GoogleStylePosTagger implements PosTagger {

	private PosTagger internalPosTagger = null;
	
	public GoogleStylePosTagger(PosTagger posTagger) {
		this.internalPosTagger = posTagger;
	}
	
	public String[] tag(String[] sentence) {
		String[] result = this.internalPosTagger.tag(sentence);
		ArrayList<String> styledResult = new ArrayList<String>();
		for(String pos : result){
			styledResult.add("_" + pos + "_");
		}
		return styledResult.toArray(new String[styledResult.size()]);
	}

}
