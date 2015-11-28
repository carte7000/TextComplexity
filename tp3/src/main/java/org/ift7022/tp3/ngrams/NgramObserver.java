package org.ift7022.tp3.ngrams;

public interface NgramObserver {

	void notify(String[] key, long count);

}
