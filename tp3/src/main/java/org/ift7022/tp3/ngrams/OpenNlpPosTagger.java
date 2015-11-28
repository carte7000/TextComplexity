package org.ift7022.tp3.ngrams;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.postag.WordTagSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

public class OpenNlpPosTagger implements PosTagger{
	private POSTaggerME tagger = null;
	
	public OpenNlpPosTagger(){
		InputStream modelIn = null;
		POSModel model = null;
		
		try {
		  modelIn = new FileInputStream("/home/manshow7000/Downloads/Ngram/en-pos-maxent.bin");
		  model = new POSModel(modelIn);
		}
		catch (IOException e) {
		  // Model loading failed, handle the error
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
		tagger = new POSTaggerME(model);
	}
	
	public String[] tag(String[] sentence) {
		return tagger.tag(sentence);
	}

}
