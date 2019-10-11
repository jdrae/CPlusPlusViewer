package project.GUI;

import java.util.ArrayList;

public class Element {
	protected String name, type, parameter, sourceCode, access;
	protected ArrayList<String> methodsList, variableList;
	protected boolean bool;
	
	public Element(String name) {
		this.name = name;
		methodsList = new ArrayList<String>();
		variableList = new ArrayList<String>();
	}
	
	public String toString() {
		return name;
	}
	
	//If method, return T, else return F
	public void setBool(String s) {
		if(s=="method") this.bool = true;
		else if(s=="data") this.bool = false;
	}
	
	public boolean getBool() {
		return this.bool;
	}
	
	public String display() {
		String info = this.name;
		
		if(this.bool) {
			info += "("+this.parameter+")";
		}
		else if(!this.bool) {
			info += ": "+this.type;
		}
		
		return info;
	}
}
