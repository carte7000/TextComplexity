package org.ift7022.tp3.ngrams;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class StandfordNlpPosTagger implements PosTagger{

	MaxentTagger tagger;
	Pattern pattern;
	
	public StandfordNlpPosTagger(){
		pattern = Pattern.compile("a*b");
		tagger = new MaxentTagger("english-left3words-distsim.tagger");
	}
	
	public String[] tag(String[] sentence) {
		// TODO Auto-generated method stub
		ArrayList<String> test = new ArrayList<String>();
		Matcher m = pattern.matcher(tagger.tagString(String.join(" ", sentence)));
		while(m.find()){
			test.add(m.group(0));
		}
		return test.toArray(new String[] {});
	}

}
