package org.ift7022.tp3;

import java.io.IOException;
import java.util.Scanner;

import org.ift7022.tp3.context.ContextBase;
import org.ift7022.tp3.context.InstantContext;
import org.ift7022.tp3.context.ServiceLocator;
import org.ift7022.tp3.ngrams.InMemoryNgramRepository;
import org.ift7022.tp3.ngrams.NgramRepository;
import org.ift7022.tp3.ngrams.OpenNlpPosTagger;
import org.ift7022.tp3.ngrams.Parser;
import org.ift7022.tp3.ngrams.PosTagger;
import org.ift7022.tp3.ngrams.RedisNgramRepository;
import org.ift7022.tp3.ngrams.TopNgramContainer;
import org.ift7022.tp3.parsers.NgramParser;
import org.ift7022.tp3.services.TextService;
import org.ift7022.tp3.texts.Text;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ContextBase context = new InstantContext();
    	context.apply();
    	TextService service = ServiceLocator.getInstance().resolve(TextService.class);
    	System.out.println(service.getStatistic("I'm a big big dog in his big big van.").toString());
    	
    	/*Scanner reader = new Scanner(System.in);  // Reading from System.in
    	String command = "";
    	while(command != "exit"){
    		System.out.println("Enter a text: ");
    		command = reader.next();
    		System.out.println(service.getStatistic(command).toString());
    	}*/
    }
}
