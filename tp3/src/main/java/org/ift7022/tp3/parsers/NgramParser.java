package org.ift7022.tp3.parsers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ift7022.tp3.ngrams.NgramRepository;
import org.ift7022.tp3.ngrams.ParseResult;
import org.ift7022.tp3.ngrams.Parser;

public class NgramParser implements Parser {
	public void parse(String file, NgramRepository ngramRepository){
		Map<String[], Long> dict = new HashMap<String[], Long>();
		Path path = Paths.get(file);
		Iterator<String> iterator;
		iterator = createIterator(path);
		while (iterator.hasNext()) {
			String line = iterator.next();
			try{
				String[] stringArray = line.split(";");
				Long count = getCount(stringArray[stringArray.length-1]);
				try{
					ngramRepository.persist(stringArray[0].split(" "), count);
				}
				catch(Exception ex){
					dict.put(new String[] { stringArray[0] }, count);
				}
			}
			catch(Exception ex){
				try
				{
				    String filename= "log.csv";
				    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
				    fw.write(line + "\n");//appends the string to the file
				    fw.close();
				}
				catch(IOException ioe)
				{
				    System.err.println("IOException: " + ioe.getMessage());
				}
			}
		}
	}
	
	private Long getCount(String count){
		try{
			return Long.parseLong(count);
		}
		catch(Exception ex){
			ex.printStackTrace();
			return Long.valueOf(0);
		}
	}

	private Iterator<String> createIterator(Path path) {
		Iterator<String> iterator = null;
		try {
			iterator = Files.lines(path).iterator();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iterator;
	}
}
