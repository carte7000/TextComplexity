package org.ift7022.tp3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceTokenizer {

	private SentenceDetectorME sentenceDetector;
	
	public SentenceTokenizer(String file){
		InputStream modelIn = null;
		try {
			modelIn = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
		  SentenceModel model = new SentenceModel(modelIn);
		  sentenceDetector = new SentenceDetectorME(model);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}
	
	public String[] sentTokenize(String text){
		return sentenceDetector.sentDetect(text);
	}
	
}
