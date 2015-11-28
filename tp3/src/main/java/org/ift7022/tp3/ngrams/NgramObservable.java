package org.ift7022.tp3.ngrams;

import java.util.ArrayList;
import java.util.Collection;

public class NgramObservable {
	public Collection<NgramObserver> observers = new ArrayList<NgramObserver>();
	
	public void notify(String[] key, long count){
		for(NgramObserver observer : observers){
			observer.notify(key, count);
		}
	}
	
	public void register(NgramObserver observer){
		observers.add(observer);
	}
	
}
