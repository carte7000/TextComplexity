package org.ift7022.tp3.context;

import java.io.IOException;

import org.ift7022.tp3.FileFetcher;
import org.ift7022.tp3.GoogleStylePosTagger;
import org.ift7022.tp3.TextFactory;
import org.ift7022.tp3.UniversalPosTagger;
import org.ift7022.tp3.ngrams.NgramRepository;
import org.ift7022.tp3.ngrams.OpenNlpPosTagger;
import org.ift7022.tp3.ngrams.Parser;
import org.ift7022.tp3.ngrams.PosTagger;
import org.ift7022.tp3.ngrams.RedisNgramRepository;
import org.ift7022.tp3.ngrams.TopNgramContainer;
import org.ift7022.tp3.parsers.NgramParser;
import org.ift7022.tp3.services.TextService;

import redis.embedded.RedisServer;

public class InstantContext extends ContextBase {

	@Override
	protected void registerServices() {
		
		startEmbeddedRedisServer();
		
		(new FileFetcher()).Download();
    	OpenNlpPosTagger openNlpTagger = new OpenNlpPosTagger();
    	PosTagger tagger = new GoogleStylePosTagger(new UniversalPosTagger(openNlpTagger, FileFetcher.MAP));
    	TopNgramContainer topNgramContainer = new TopNgramContainer();
    	NgramRepository ngramRepository = new RedisNgramRepository();
    	NgramRepository posRepository = new RedisNgramRepository("POS");
    	TextFactory factory = new TextFactory(topNgramContainer, ngramRepository, posRepository, tagger);
    	ServiceLocator.getInstance().register(TextFactory.class, factory);
    	TextService service = new TextService(factory);
    	ServiceLocator.getInstance().register(TextService.class, service);
	}

	private void startEmbeddedRedisServer() {
		RedisServer redisServer = null;
		try {
			redisServer = new RedisServer(6379);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redisServer.start();
	}

	@Override
	protected void createData() {
	}

}
