package org.ift7022.tp3.services;

import java.net.URISyntaxException;

import org.ift7022.tp3.TextFactory;
import org.ift7022.tp3.classifier.TextClassification;
import org.ift7022.tp3.texts.Text;

public class TextService {

	private TextFactory textFactory;

	public TextService(TextFactory textFactory) {
		this.textFactory = textFactory;
	}

	public Statistic getStatistic(String text) throws URISyntaxException {
		Text textObj = textFactory.create();
		// textObj.updateText(text);
		Statistic statistic = new Statistic();
		TextClassification textClassification = new TextClassification(text);
		// statistic.wordPerplexity = textObj.getPerplexity();
		// statistic.posPerplexity = textObj.getPosPerplexity();
		statistic.typeClass = textClassification.classify();
		return statistic;
	}

}
