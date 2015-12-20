package org.ift7022.tp3;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ThresholdingOutputStream;
import org.apache.commons.math3.distribution.GeometricDistribution;
import org.ift7022.tp3.classifier.TextClassification;
import org.ift7022.tp3.context.BuildContext;
import org.ift7022.tp3.context.ContextBase;
import org.ift7022.tp3.context.InstantContext;
import org.ift7022.tp3.context.ServiceLocator;
import org.ift7022.tp3.services.Statistic;
import org.ift7022.tp3.services.TextService;

/**
 * Hello world!
 *
 */
public class App {
	
	static Statistic stat1;
	static Statistic stat2;
	static TextService service;
	
	public static void main(String[] args) {
		ContextBase context = new BuildContext();
		context.apply();
		service = ServiceLocator.getInstance().resolve(TextService.class);
		
		String test = "";
		String test2 = "";
		
		try {
			test = FileUtils.readFileToString(new File(FileFetcher.SCIENTIFIC_TRAIN));
			test2 = FileUtils.readFileToString(new File(FileFetcher.SIMPLE_TRAIN));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			stat1 = service.getStatistic(test);
			System.out.println("Training 1 Done");
			
			stat2 = service.getStatistic(test2);
			System.out.println("Training 2 Done");
			
			//System.out.println(text);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String resultClassifier = "";
		resultClassifier += getResultClassifier(FileFetcher.COMPLICATED_1);
		resultClassifier += getResultClassifier(FileFetcher.COMPLICATED_2);
		resultClassifier += getResultClassifier(FileFetcher.COMPLICATED_3);
		resultClassifier += getResultClassifier(FileFetcher.COMPLICATED_4);
		resultClassifier += getResultClassifier(FileFetcher.COMPLICATED_5);
		resultClassifier += getResultClassifier(FileFetcher.NOT_COMPLICATED_6);
		resultClassifier += getResultClassifier(FileFetcher.NOT_COMPLICATED_2);
		resultClassifier += getResultClassifier(FileFetcher.NOT_COMPLICATED_3);
		resultClassifier += getResultClassifier(FileFetcher.NOT_COMPLICATED_4);
		resultClassifier += getResultClassifier(FileFetcher.NOT_COMPLICATED_5);
		
		System.out.println(resultClassifier);
		
		String resultPerplexity = "";
		resultPerplexity += getResultPerplexity(FileFetcher.COMPLICATED_1);
		resultPerplexity += getResultPerplexity(FileFetcher.COMPLICATED_2);
		resultPerplexity += getResultPerplexity(FileFetcher.COMPLICATED_3);
		resultPerplexity += getResultPerplexity(FileFetcher.COMPLICATED_4);
		resultPerplexity += getResultPerplexity(FileFetcher.COMPLICATED_5);
		resultPerplexity += getResultPerplexity(FileFetcher.NOT_COMPLICATED_6);
		resultPerplexity += getResultPerplexity(FileFetcher.NOT_COMPLICATED_2);
		resultPerplexity += getResultPerplexity(FileFetcher.NOT_COMPLICATED_3);
		resultPerplexity += getResultPerplexity(FileFetcher.NOT_COMPLICATED_4);
		resultPerplexity += getResultPerplexity(FileFetcher.NOT_COMPLICATED_5);
		
		System.out.println(resultPerplexity);
		
		
		
		/*
		 * Scanner reader = new Scanner(System.in); // Reading from System.in
		 * String command = ""; while(command != "exit"){ System.out.println(
		 * "Enter a text: "); command = reader.next();
		 * System.out.println(service.getStatistic(command).toString()); }
		 */
	}
	
	private static String getResultPerplexity(String url){
		return url+": "+classifyWithPerplexity(url)+"\n";
	}
	
	private static String getResultClassifier(String url){
		return url+": "+classifyWithClassifier(url)+"\n";
	}
	
	private static String classifyWithPerplexity(String url){
		Statistic text = null;
		try {
			text = service.getStatistic(FileUtils.readFileToString(new File(url)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double diff1 = getDistance(stat1, text);
		Double diff2 = getDistance(stat2, text);
		
		if(diff1 > diff2){
			return "Compliquer";
		}
		else{
			return "Pas Compliquer";
		}
	}
	
	private static String classifyWithClassifier(String url){
		try {
			String test = FileUtils.readFileToString(new File(url));
			TextClassification text = new TextClassification(test);
			return text.classify();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public static Double getDistance(Statistic test, Statistic compared){
		return Math.sqrt(Math.pow((test.posPerplexity-compared.posPerplexity),2)+Math.pow((test.wordPerplexity-compared.wordPerplexity),2));
	}
}
