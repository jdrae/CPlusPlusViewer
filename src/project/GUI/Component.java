package project.GUI;

import java.util.*;

public class Component {
	protected String name;
	protected ArrayList<Element> member;
	
	public Component(String name) {
		this.name = name;
		member = new ArrayList<Element>();
	}
	
	public void add(Element e) {
		member.add(e);
	}
	
	public Element get(int index) {
		return member.get(index);
	}
	
	public int size() {
		return member.size();
	}
	
	public int getIndexOf(Element e) {
		for(int i=0;i<member.size();i++) {
			Element se = member.get(i);
			if(se == e) {
				return i;
			}
		}
		return -1;
	}
	
	public String toString() {
		return name;
	}
	
	public String display() {
		String info = name;
		for(int i=0;i<member.size();i++) {
			info += "\n\t"+member.get(i).toString();
		}
		return info;
	}
}
