package project.IO;

import java.util.ArrayList;

public class Variables{
	public String name;
	public String access;
	public String type;
	public ArrayList<Methods> musingv = new ArrayList<Methods>();
	

	public Variables(String name) {
		this.name = name;
	}
}