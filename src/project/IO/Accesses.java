package project.IO;

import java.util.ArrayList;

public class Accesses{
	public String name;
	public ArrayList<String> allAccess = new ArrayList<String>();
	public ArrayList<String> mAccess = new ArrayList<String>();
	public ArrayList<String> vAccess = new ArrayList<String>();

	public Accesses(String name) {
		this.name = name;
	}
	
}
