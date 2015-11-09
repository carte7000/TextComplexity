package org.ift7022.tp3.texts;

import java.util.StringTokenizer;

import org.ift7022.tp3.ngrams.NgramCounter;
import org.ift7022.tp3.ngrams.TopNgramContainer;

public class Text {
	private NgramCounter ngramRepository = null;
	private String text = null;
	private int maxRank = 0;
	
	public Text(){
		ngramRepository = createNgramRepository();
	}
	
	private NgramCounter createNgramRepository() {
		return new InMemoryNgramCounter();
	}

	public void updateText(String text){
		initText(text);
		parseText(text);
	}

	private void parseText(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text);
		while(tokenizer.hasMoreTokens()){
			indexWord(tokenizer.nextToken());
		}
	}

	private void indexWord(String word) {
		ngramRepository.persist(word);
		updateMaxRank(word);
	}

	private void initText(String text) {
		this.text = text;
		this.maxRank = 0;
		this.ngramRepository = createNgramRepository();
	}

	private void updateMaxRank(String word) {
		if(maxRank < 5000){
			if(!TopNgramContainer.getInstance().isTopWord(word)){
				maxRank = 5000;
			}
			else{
				int wordRank = TopNgramContainer.getInstance().getRank(word);
				if(maxRank < wordRank){
					maxRank = wordRank;
				}
			}
		}
	}
	
	public int getMaxRank(){
		return maxRank;
	}
	
	public int getTotalWord(){
		return this.ngramRepository.getTotalCount();
	}
}
