package org.ift7022.tp3.classifier;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.datumbox.applications.nlp.TextClassifier;
import com.datumbox.common.dataobjects.Record;
import com.datumbox.common.persistentstorage.ConfigurationFactory;
import com.datumbox.common.persistentstorage.interfaces.DatabaseConfiguration;
import com.datumbox.common.utilities.PHPfunctions;
import com.datumbox.common.utilities.RandomGenerator;
import com.datumbox.framework.machinelearning.classification.MultinomialNaiveBayes;
import com.datumbox.framework.machinelearning.common.bases.mlmodels.BaseMLmodel;
import com.datumbox.framework.machinelearning.featureselection.categorical.ChisquareSelect;
import com.datumbox.framework.utilities.text.extractors.NgramsExtractor;

/**
 * Text Classification example.
 * 
 * @author Vasilis Vryniotis <bbriniotis@datumbox.com>
 */
public class TextClassification {

	private String text;

	public TextClassification(String text) {
		this.text = text;
	}

	/**
	 * Example of how to use the TextClassifier class.
	 * 
	 * @param args
	 *            the command line arguments
	 * @throws java.net.URISyntaxException
	 */
	public String classify() throws URISyntaxException {
		/**
		 * There are two configuration files in the resources folder: tr -
		 * datumbox.config.properties: It contains the configuration for the
		 * storage engines (required) - logback.xml: It contains the
		 * configuration file for the logger (optional)
		 */

		// Initialization
		// --------------
		RandomGenerator.setGlobalSeed(42L); // optionally set a specific seed
											// for all Random objects
		DatabaseConfiguration dbConf = ConfigurationFactory.INMEMORY.getConfiguration(); // in-memory
																							// maps
		// DatabaseConfiguration dbConf =
		// ConfigurationFactory.MAPDB.getConfiguration(); //mapdb maps

		// Reading Data
		// ------------
		Map<Object, URI> dataset = new HashMap<Object, URI>(); // The examples
																// of each
		// category are stored on
		// the same file, one
		// example per row.
		dataset.put("complicated", TextClassification.class.getClassLoader()
				.getResource("datasets/text-complexity/scientific-articles.train").toURI());
		dataset.put("not complicated", TextClassification.class.getClassLoader()
				.getResource("datasets/text-complexity/simple-articles.train").toURI());

		// Setup Training Parameters
		// -------------------------
		TextClassifier.TrainingParameters trainingParameters = new TextClassifier.TrainingParameters();

		// Classifier configuration
		trainingParameters.setMLmodelClass(MultinomialNaiveBayes.class);
		trainingParameters.setMLmodelTrainingParameters(new MultinomialNaiveBayes.TrainingParameters());

		// Set data transfomation configuration
		trainingParameters.setDataTransformerClass(null);
		trainingParameters.setDataTransformerTrainingParameters(null);

		// Set feature selection configuration
		trainingParameters.setFeatureSelectionClass(ChisquareSelect.class);
		trainingParameters.setFeatureSelectionTrainingParameters(new ChisquareSelect.TrainingParameters());

		// Set text extraction configuration
		trainingParameters.setTextExtractorClass(NgramsExtractor.class);
		trainingParameters.setTextExtractorParameters(new NgramsExtractor.Parameters());

		// Fit the classifier
		// ------------------
		TextClassifier classifier = new TextClassifier("SentimentAnalysis", dbConf);
		classifier.fit(dataset, trainingParameters);

		// Use the classifier
		// ------------------

		// Get validation metrics on the training set
		BaseMLmodel.ValidationMetrics vm = classifier.validate(dataset);
		classifier.setValidationMetrics(vm); // store them in the model for
												// future reference

		// Classify a single sentence
		Record r = classifier.predict(text);
		String predicted = (String) (r.getYPredicted());
		System.out.println("Classifing sentence: \"" + text + "\"");
		System.out.println("Predicted class: " + r.getYPredicted());
		System.out.println("Probability: " + r.getYPredictedProbabilities().get(r.getYPredicted()));

		System.out.println("Classifier Statistics: " + PHPfunctions.var_export(vm));

		// Clean up
		// --------

		// Erase the classifier. This removes all files.
		classifier.erase();
		return predicted;
	}

}