package org.ift7022.tp3.ngrams;

import java.util.Collection;

public interface NgramRepository {
	public void persist(String[] word, long count);
	public Ngram getNgram(String[] word);
}
