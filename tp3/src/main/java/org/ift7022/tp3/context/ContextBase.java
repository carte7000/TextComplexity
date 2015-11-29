package org.ift7022.tp3.context;

public abstract class ContextBase {
	public void apply(){
		registerServices();
		createData();
	}
	protected abstract void registerServices();
	protected abstract void createData();
}