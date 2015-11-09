package org.ift7022.tp3.ngrams;

public class Ngram {

	private String value;
	private int count;
	private int totalCount;
	
	public Ngram(String value, int count, int totalCount){
		this.value = value;
		this.count = count;
		this.totalCount = totalCount;
	}
	
	public String getValue(){
		return value;
	}
	
	public double getLogProbability(){
		return -1*Math.log(this.getProbability());
	}
	
	public double getProbability(){
		return this.count/totalCount;
	}
	
}
