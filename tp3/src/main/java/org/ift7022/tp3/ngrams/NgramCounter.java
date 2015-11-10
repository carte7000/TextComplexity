package org.ift7022.tp3.ngrams;

public interface NgramCounter {
	public void persist(String[] word);

	public int getTotalCount();

	public Ngram getNgram(String[] value);

	public Double computePerplexity(NgramRepository ngram);

}
