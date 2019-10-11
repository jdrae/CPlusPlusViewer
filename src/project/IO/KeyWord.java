package project.IO;
import java.util.ArrayList;

//클래스, 메소드, 변수별로 정보 저장

public class KeyWord {
	public ArrayList<Accesses> alist = new ArrayList<Accesses>(); //접근제어자별 메소드, 변수.
	public ArrayList<Classes> clist = new ArrayList<Classes>();
	public ArrayList<Variables> vlist = new ArrayList<Variables>();
	public ArrayList<Methods> mlist = new ArrayList<Methods>();
}

