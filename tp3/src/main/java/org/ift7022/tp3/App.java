package org.ift7022.tp3;

import java.io.IOException;

import org.ift7022.tp3.ngrams.InMemoryNgramRepository;
import org.ift7022.tp3.ngrams.NgramRepository;
import org.ift7022.tp3.ngrams.OpenNlpPosTagger;
import org.ift7022.tp3.ngrams.Parser;
import org.ift7022.tp3.ngrams.PosTagger;
import org.ift7022.tp3.ngrams.RedisNgramRepository;
import org.ift7022.tp3.ngrams.TopNgramContainer;
import org.ift7022.tp3.parsers.NgramParser;
import org.ift7022.tp3.texts.Text;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	(new FileFetcher()).Download();
    	OpenNlpPosTagger openNlpTagger = new OpenNlpPosTagger();
    	Parser parser = new NgramParser();
    	PosTagger tagger = new GoogleStylePosTagger(new UniversalPosTagger(openNlpTagger, FileFetcher.MAP));
    	TopNgramContainer topNgramContainer = new TopNgramContainer();
    	NgramRepository ngramRepository = new RedisNgramRepository();
    	NgramRepository posRepository = new RedisNgramRepository("POS");
    	Text text = new Text(topNgramContainer, ngramRepository, posRepository, tagger);
    	text.updateText("I am a blue and grey dog and i like it. House House House House screamed Bob. 203 want Bob and 202. Want want want a do.");    	
        System.out.println(text.getPosPerplexity());
    }
}
