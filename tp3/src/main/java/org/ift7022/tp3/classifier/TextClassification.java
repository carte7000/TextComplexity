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

	/**
	 * Example of how to use the TextClassifier class.
	 * 
	 * @param args
	 *            the command line arguments
	 * @throws java.net.URISyntaxException
	 */
	public static void main(String[] args) throws URISyntaxException {
		/**
		 * There are two configuration files in the resources folder:
		 * 
		 * - datumbox.config.properties: It contains the configuration for the
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
				.getResource("datasets/sentiment-analysis/scientific-articles.comp").toURI());
		dataset.put("not complicated", TextClassification.class.getClassLoader()
				.getResource("datasets/sentiment-analysis/simple-articles.comp").toURI());

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
		String sentence = "Adventures come to the adventurous, and mysterious things fall in the way of those who, with wonder and imagination, are on the watch for them; but the majority of people go past the doors that are half ajar, thinking them closed, and fail to notice the faint stirrings of the great curtain that hangs ever in the form of appearances between them and the world of causes behind.For only to the few whose inner senses have been quickened, perchance by some strange suffering in the depths, or by a natural temperament bequeathed from a remote past, comes the knowledge, not too welcome, that this greater world lies ever at their elbow, and that any moment a chance combination of moods and forces may invite them to cross the shifting frontier.Some, however, are born with this awful certainty in their hearts, and are called to no apprenticeship, and to this select company Jones undoubtedly belonge All his life he had realised that his senses brought to him merely a more or less interesting set of sham appearances; that space, as men measure it, was utterly misleading; that time, as the clock ticked it in a succession of minutes, was arbitrary nonsense; and, in fact, that all his sensory perceptions were but a clumsy representation of real things behind the curtain—things he was for ever trying to get at, and that sometimes he actually did get at.He had always been tremblingly aware that he stood on the borderland of another region, a region where time and space were merely forms of thought, where ancient memories lay open to the sight, and where the forces behind each human life stood plainly revealed and he could see the hidden springs at the very heart of the world. Moreover, the fact that he was a clerk in a fire insurance office, and did his work with strict attention, never allowed him to forget for one moment that, just beyond the dingy brick walls where the hundred men scribbled with pointed pens beneath the electric lamps, there existed this glorious region where the important part of himself dwelt and moved and had its being. For in this region he pictured himself playing the part of a spectator to his ordinary workaday life, watching, like a king, the stream of events, but untouched in his own soul by the dirt, the noise, and the vulgar commotion of the outer world.And this was no poetic dream merely. Jones was not playing prettily with idealism to amuse himself. It was a living, working belief. So convinced was he that the external world was the result of a vast deception practised upon him by the gross senses, that when he stared at a great building like St. Paul's he felt it would not very much surprise him to see it suddenly quiver like a shape of jelly and then melt utterly away, while in its place stood all at once revealed the mass of colour, or the great intricate vibrations, or the splendid sound—the spiritual idea—which it represented in stone.For something in this way it was that his mind worked.Yet, to all appearances, and in the satisfaction of all business claims, Jones was normal and unenterprising. He felt nothing but contempt for the wave of modern psychism. He hardly knew the meaning of such words as clairvoyance and clairaudience He had never felt the least desire to join the Theosophical Society and to speculate in theories of astral-plane life, or elementals. He attended no meetings of the Psychical Research Society, and knew no anxiety as to whether his aura was black or blue; nor was he conscious of the slightest wish to mix in with the revival of cheap occultism which proves so attractive to weak minds of mystical tendencies and unleashed imaginations.There were certain things he knew, but none he cared to argue about; and he shrank instinctively from attempting to put names to the contents of this other region, knowing well that such names could only limit and define things that, according to any standards in use in the ordinary world, were simply undefinable and illusive.So that, although this was the way his mind worked, there was clearly a very strong leaven of common sense in Jones. In a word, the man the world and the office knew as Jones was Jones. The name summed him up and labelled him correctly—John Enderby Jone Among the things that he knew, and therefore never cared to speak or speculate about, one was that he plainly saw himself as the inheritor of a long series of past lives, the net result of painful evolution, always as himself, of course, but in numerous different bodies each determined by the behaviour of the preceding one. The present John Jones was the last result to date of all the previous thinking, feeling, and doing of John Jones in earlier bodies and in other centuries. He pretended to no details, nor claimed distinguished ancestry, for he realised his past must have been utterly commonplace and insignificant to have produced his present; but he was just as sure he had been at this weary game for ages as that he breathed, and it never occurred to him to argue, to doubt, or to ask questions. And one result of this belief was that his thoughts dwelt upon the past rather than upon the future; that he read much history, and felt specially drawn to certain periods whose spirit he understood instinctively as though he had lived in them; and that he found all religions uninteresting because, almost without exception, they start from the present and speculate ahead as to what men shall become, instead of looking back and speculating why men have got here as they are.In the insurance office he did his work exceedingly well, but without much personal ambition. Men and women he regarded as the impersonal instruments for inflicting upon him the pain or pleasure he had earned by his past workings, for chance had no place in his scheme of things at all; and while he recognised that the practical world could not get along unless every man did his work thoroughly and conscientiously, he took no interest in the accumulation of fame or money for himself, and simply, therefore, did his plain duty, with indifference as to results.In common with others who lead a strictly impersonal life, he possessed the quality of utter bravery, and was always ready to face any combination of circumstances, no matter how terrible, because he saw in them the just working-out of past causes he had himself set in motion which could not be dodged or modified. And whereas the majority of people had little meaning for him, either by way of attraction or repulsion, the moment he met some one with whom he felt his past had been vitally interwoven his whole inner being leapt up instantly and shouted the fact in his face, and he regulated his life with the utmost skill and caution, like a sentry on watch for an enemy whose feet could already be heard approaching.";
		String sentence2 = "maths";
		Record r = classifier.predict(sentence2);

		System.out.println("Classifing sentence: \"" + sentence + "\"");
		System.out.println("Predicted class: " + r.getYPredicted());
		System.out.println("Probability: " + r.getYPredictedProbabilities().get(r.getYPredicted()));

		System.out.println("Classifier Statistics: " + PHPfunctions.var_export(vm));

		// Clean up
		// --------

		// Erase the classifier. This removes all files.
		classifier.erase();
	}

}