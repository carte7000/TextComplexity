package org.ift7022.tp3.ngrams;

public class Ngram {

	private String[] value;
	private Long count;
	private Long totalCount;

	public Ngram(String[] value, Long count, Long totalCount) {
		this.value = value;
		this.count = count;
		this.totalCount = totalCount;

	}

	public String[] getValue() {
		return value;
	}

	public double getLogProbability() {
		return -1 * (Math.log(this.count) - Math.log(totalCount));
	}

	public double getProbability() {
		return this.count / totalCount;
	}

}
