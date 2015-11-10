package org.ift7022.tp3.texts;

import org.ift7022.tp3.ngrams.Ngram;
import org.ift7022.tp3.ngrams.NgramRepository;

public interface NgramCounter {
	public void persist(String[] word);

	public int getTotalCount();

	public Ngram getNgram(String[] value);

	public Double computePerplexity(NgramRepository ngram);

}
