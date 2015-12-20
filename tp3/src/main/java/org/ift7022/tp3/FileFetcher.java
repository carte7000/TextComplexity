package org.ift7022.tp3;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class FileFetcher {
	
	public static final String COMPLICATED_1 = "Complicated1.txt";
	public static final String COMPLICATED_2 = "Complicated2.txt";
	public static final String COMPLICATED_3 = "Complicated3.txt";
	public static final String COMPLICATED_4 = "Complicated4.txt";
	public static final String COMPLICATED_5 = "Complicated5.txt";
	public static final String NOT_COMPLICATED_6 = "NotComplicated6.txt";
	public static final String NOT_COMPLICATED_2 = "NotComplicated2.txt";
	public static final String NOT_COMPLICATED_3 = "NotComplicated3.txt";
	public static final String NOT_COMPLICATED_4 = "NotComplicated4.txt";
	public static final String NOT_COMPLICATED_5 = "NotComplicated5.txt";
	public static final String SIMPLE_TRAIN = "simple.train";
	public static final String SCIENTIFIC_TRAIN = "scientific.train";
	public static final String POS_MODEL = "en-pos-maxent.bin";
	public static final String SENTENCE_MODEL = "da-sent.bin";
	public static final String COMPRESSED_1GRAM = "compressed_1gram.csv";
	public static final String MAP = "map.map";
	public static final String COMPRESSED = "compressed.csv";  
		
	public void Download() {
		UrlToFile("https://s3.amazonaws.com/text-complexity/compressed.csv", COMPRESSED);
		UrlToFile("https://s3.amazonaws.com/text-complexity/Complicated1.txt", COMPLICATED_1);
		UrlToFile("https://s3.amazonaws.com/text-complexity/Complicated2.txt", COMPLICATED_2);
		UrlToFile("https://s3.amazonaws.com/text-complexity/Complicated3.txt", COMPLICATED_3);
		UrlToFile("https://s3.amazonaws.com/text-complexity/Complicated4.txt", COMPLICATED_4);
		UrlToFile("https://s3.amazonaws.com/text-complexity/Complicated5.txt", COMPLICATED_5);
		UrlToFile("https://s3.amazonaws.com/text-complexity/NotComplicated6.txt", NOT_COMPLICATED_6);
		UrlToFile("https://s3.amazonaws.com/text-complexity/NotComplicated2.txt", NOT_COMPLICATED_2);
		UrlToFile("https://s3.amazonaws.com/text-complexity/NotComplicated3.txt", NOT_COMPLICATED_3);
		UrlToFile("https://s3.amazonaws.com/text-complexity/NotComplicated4.txt", NOT_COMPLICATED_4);
		UrlToFile("https://s3.amazonaws.com/text-complexity/NotComplicated5.txt", NOT_COMPLICATED_5);
		UrlToFile("https://s3.amazonaws.com/text-complexity/map.map", MAP);
		UrlToFile("https://s3.amazonaws.com/text-complexity/compressed_1gram.csv", COMPRESSED_1GRAM);
		UrlToFile("https://s3.amazonaws.com/text-complexity/da-sent.bin", SENTENCE_MODEL);
		UrlToFile("https://s3.amazonaws.com/text-complexity/en-pos-maxent.bin", POS_MODEL);
		UrlToFile("https://s3.amazonaws.com/text-complexity/scientific-articles.train", SCIENTIFIC_TRAIN);
		UrlToFile("https://s3.amazonaws.com/text-complexity/simple-articles.train", SIMPLE_TRAIN);
		System.out.println("All files are downloaded.");
	}
	
	private void UrlToFile(String url, String file){
		File f = new File(file);
		if(!f.exists()){
			try {
				FileUtils.copyURLToFile(new URL(url), f);
				System.out.println("Fetching: "+ f + "...");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
