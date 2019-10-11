package project.GUI;


public class DotH {
	protected String name;
	protected Component methods, datas;
	
	public DotH(String name) {
		this.name = name;
		methods = new Component("Methods");
		datas = new Component("Datas");
	}
	
	public Component getComponent(String name) {
		if(name.equals("Methods"))
			return methods;
		else
			return datas;
	}
	
	public Component getComponent(int index) {
		if(index == 0) {
			return methods;
		}
		else 
			return datas;
	}
	
	public int getIndexOf(Component d) {
		if(d==methods)
			return 0;
		else 
			return 1;
	}
	
	public String toString() {
		return name;
	}
	
}
