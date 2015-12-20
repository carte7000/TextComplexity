package org.ift7022.tp3.texts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.swing.text.html.parser.DocumentParser;

import org.ift7022.tp3.FileFetcher;
import org.ift7022.tp3.SentenceTokenizer;
import org.ift7022.tp3.ngrams.NgramRepository;
import org.ift7022.tp3.ngrams.PosTagger;
import org.ift7022.tp3.ngrams.TopNgramContainer;

import opennlp.tools.formats.ad.ADSentenceStream.Sentence;

public class Text {
	private static final int MAX_RANK = 5000;
	private NgramCounter ngramCounter = null;
	private NgramCounter posCounter = null;
	private String text = null;
	private int maxRank = 0;
	private TopNgramContainer topNgramContainer;
	private NgramRepository ngramRepository;
	private NgramRepository posRepository;
	private PosTagger posTagger;

	public Text(TopNgramContainer topNgramContainer, NgramRepository ngramRepository, NgramRepository posRepository, PosTagger tagger) {
		this.ngramCounter = createNgramRepository();
		this.posCounter = createNgramRepository();
		this.topNgramContainer = topNgramContainer;
		this.ngramRepository = ngramRepository;
		this.posRepository = posRepository;
		this.posTagger = tagger;
	}

	private NgramCounter createNgramRepository() {
		return new InMemoryNgramCounter();
	}

	public void updateText(String text) {
		initText(text);
		parseText(text);
	}

	private void parseText(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text);
		while (tokenizer.hasMoreTokens()) {
			indexWord(tokenizer.nextToken());
		}
		
		String[] sentences = (new SentenceTokenizer(FileFetcher.SENTENCE_MODEL)).sentTokenize(text);
		for(String sentence : sentences){
			System.out.println("POS: "+ sentence);
			indexPos(sentence);
		}
	}

	private void indexPos(String sentence) {
		ArrayList<String> words = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(text);
		while (tokenizer.hasMoreTokens()) {
			words.add(tokenizer.nextToken());
		}
		int i = 0;
		
		String[] pos = posTagger.tag(words.toArray(new String[words.size()]));
		
		while(i < pos.length-5){
			posCounter.persist(Arrays.copyOfRange(pos, i, i+5));
			i++;
		}
		pos = null;
		//System.gc();
	}

	private void indexWord(String word) {
		ngramCounter.persist(new String[] { word });
		updateMaxRank(word);
	}

	private void initText(String text) {
		this.maxRank = 0;
		this.text=text;
		this.ngramCounter = createNgramRepository();
	}

	private void updateMaxRank(String word) {
		if(maxRank < MAX_RANK){
			if(!topNgramContainer.isTopWord(word)){
				maxRank = MAX_RANK;
			}
			else{
				int wordRank = topNgramContainer.getRank(word);
				if (maxRank < wordRank) {
					maxRank = wordRank;
				}
			}
		}
	}

	public int getMaxRank() {
		return maxRank;
	}

	public Double getPerplexity(){
		return ngramCounter.computePerplexity(ngramRepository);
	}
	
	public Double getPosPerplexity(){
		return posCounter.computePerplexity(posRepository);
	}
	
	public Long getTotalWord() {
		return this.ngramCounter.getTotalCount();
	}
}
