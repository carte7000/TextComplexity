package org.ift7022.tp3.ngrams;

import java.util.Collection;

public interface NgramRepository {
	public Ngram getNgram(String[] word);

	public Collection<Ngram> findAll();

}
