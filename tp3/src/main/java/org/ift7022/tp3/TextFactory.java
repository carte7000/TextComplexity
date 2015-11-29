package org.ift7022.tp3;

import org.ift7022.tp3.ngrams.NgramRepository;
import org.ift7022.tp3.ngrams.PosTagger;
import org.ift7022.tp3.ngrams.TopNgramContainer;
import org.ift7022.tp3.texts.Text;

public class TextFactory {
	
	private TopNgramContainer topNgramContainer;
	private NgramRepository ngramRepository;
	private NgramRepository posRepository;
	private PosTagger posTagger;
	
	public TextFactory(TopNgramContainer topNgramContainer, NgramRepository ngramRepository,
			NgramRepository posRepository, PosTagger tagger) {
		this.topNgramContainer = topNgramContainer;
		this.ngramRepository = ngramRepository;
		this.posRepository = posRepository;
		this.posTagger = tagger;
	}

	public Text create(){
		return new Text(topNgramContainer, ngramRepository, posRepository, posTagger);
	}
	
}
