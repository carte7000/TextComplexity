package org.ift7022.tp3.services;

public class Statistic {

	public Double wordPerplexity;
	public Double posPerplexity;

	public String toString(){
		return "Word Perplexity: " + String.valueOf(wordPerplexity) + ", POS Perplexity: " + String.valueOf(posPerplexity);
	}
}
