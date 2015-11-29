package org.ift7022.tp3;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class FileFetcher {
	
	public static final String POS_MODEL = "en-pos-maxent.bin";
	public static final String SENTENCE_MODEL = "da-sent.bin";
	public static final String COMPRESSED_1GRAM = "compressed_1gram.csv";
	public static final String MAP = "map.map";
	public static final String COMPRESSED = "compressed.csv";  
		
	public void Download() {
		UrlToFile("https://s3.amazonaws.com/text-complexity/compressed.csv", COMPRESSED);
		UrlToFile("https://s3.amazonaws.com/text-complexity/map.map", MAP);
		UrlToFile("https://s3.amazonaws.com/text-complexity/compressed_1gram.csv", COMPRESSED_1GRAM);
		UrlToFile("https://s3.amazonaws.com/text-complexity/da-sent.bin", SENTENCE_MODEL);
		UrlToFile("https://s3.amazonaws.com/text-complexity/en-pos-maxent.bin", POS_MODEL);
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
