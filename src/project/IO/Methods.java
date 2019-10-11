package project.IO;

import java.util.ArrayList;

public class Methods{
	public String name;
	public String type;
	public String parameter;
	public String access;
	public String allContents; //textarea에 표시할 메소드 내용
	public ArrayList<Variables> vinm = new ArrayList<Variables>();
	
	public Methods(String access) {
		this.access = access;
	}
	
}
