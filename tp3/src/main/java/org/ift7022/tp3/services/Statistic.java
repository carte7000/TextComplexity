package org.ift7022.tp3.services;

public class Statistic {

	public Double wordPerplexity;
	public Double posPerplexity;
	public String typeClass;

	public String toString() {
		return "Word Perplexity: " + String.valueOf(wordPerplexity) + ", POS Perplexity: "
				+ String.valueOf(posPerplexity) + "this text is " + typeClass;
	}
}
