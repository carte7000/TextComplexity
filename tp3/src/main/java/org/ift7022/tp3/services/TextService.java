package org.ift7022.tp3.services;

import org.ift7022.tp3.TextFactory;
import org.ift7022.tp3.texts.Text;

public class TextService {
	
	private TextFactory textFactory;
	
	public TextService(TextFactory textFactory){
		this.textFactory = textFactory;
	}
	
	public Statistic getStatistic(String text){
		Text textObj = textFactory.create();
		textObj.updateText(text);
		Statistic statistic = new Statistic();
		statistic.wordPerplexity = textObj.getPerplexity();
		statistic.posPerplexity = textObj.getPosPerplexity();
		return statistic;
	}
	
}
