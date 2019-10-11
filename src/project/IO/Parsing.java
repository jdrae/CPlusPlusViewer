package project.IO;
import java.util.*;

import project.GUI.ReadFileClass;

public class Parsing {
	public static KeyWord kw = new KeyWord();
	public String str; //가장 첫 텍스트. 
	String raw;
	String[][] keyword = {
			{"class"}, //클래스
			{"public","private","default","protected"}, //접근제어자 
			{"char", "double", "float", "int", "long", "short", "void", "bool"} //자료형: 메소드 및 변수 구분
	};

	ArrayList<String> temp1 = new ArrayList<String>();
	ArrayList<String> temp2 = new ArrayList<String>();
	
	public void algorithm() { //init() 한 이후에 사용 가능
		//1단계: 클래스 안 내용 가져와서 temp1 에 저장 & 클래스 이름 저장. 클래스 하나일때만!
		System.out.println(str);
		temp1 = doParsing(str, 0, "{","};");
		kw.clist.add(new Classes(doParsing(str, 0, " ","{").get(0)));
		
		//2단계: 접근제어자별 분류. KeyWord의 alist 에 저장
		ArrayList<String> tmp1 = new ArrayList<String>();
		ArrayList<String> tmp2 = new ArrayList<String>();
		ArrayList<Integer> intTmp = new ArrayList<Integer>();
		for(int i = 0; i<temp1.size(); i++) {
			String[] deli = {":",";"};
			temp2 = Token.finalResult(temp1.get(i), deli, false);
		}

		for(int i =0; i<temp2.size();i++) {
			for(int j= 0; j<keyword[1].length; j++) {
				if(temp2.get(i).contains(keyword[1][j])) {
					intTmp.add(i); //접근제어자 키워드가 저장된 인덱스
				}
			}
		}

		for(int i =0; i<intTmp.size();i++) {
			kw.alist.add(new Accesses(temp2.get(intTmp.get(i)).trim()));
			if(i == intTmp.size()-1) { //마지막접근제어자일 경우
				for(int j=intTmp.get(i)+1; j<temp2.size();j++) {
					kw.alist.get(i).allAccess.add(temp2.get(j));
				}
			}
			else {
				for(int j=intTmp.get(i)+1; j<intTmp.get(i+1);j++) {
					kw.alist.get(i).allAccess.add(temp2.get(j));
				}
			}
		}
		
		//3단계  메소드와 변수 구분
		tmp1.clear();
		for(int i = 0; i< kw.alist.size(); i++) {
			tmp1 = kw.alist.get(i).allAccess;
			for(int j = 0; j<tmp1.size(); j++) {
				String stmp = tmp1.get(j);
				if(tmp1.get(j).contains("(")) {		//메소드
					kw.alist.get(i).mAccess.add(stmp.substring(0,stmp.length()));
				}
				else {		//변수
					kw.alist.get(i).vAccess.add(stmp.substring(0,stmp.length()));
				}
			}
		}
		
		//4단계 변수 info - 완료!
		tmp1.clear();	tmp2.clear();
		for(int i = 0; i< kw.alist.size(); i++) {
			tmp2 = kw.alist.get(i).vAccess;
			for(int j = 0; j<tmp2.size(); j++) { //tmp2 의 사이즈가 0 이면 실행 안됨
				//이름 추출
				if(tmp2.get(j).contains("*")) { //포인터의 경우
					kw.vlist.add(new Variables(doParsing(tmp2.get(j), 2, "*","").get(0)));
				}
				else {
					kw.vlist.add(new Variables(doParsing(tmp2.get(j), 2, " ","").get(0)));
				}
				//자료형 추출
				kw.vlist.get(j).type = doParsing(tmp2.get(j),2,""," ").get(0);
				//접근제어자 추출
				kw.vlist.get(j).access = kw.alist.get(i).name;
			}
		}
		
		//5단계 메소드 info -완료!
		tmp1.clear();	tmp2.clear();	ArrayList<String> tmp3 = new ArrayList<String>();
		for(int i = 0; i< kw.alist.size(); i++) {
			tmp2 = kw.alist.get(i).mAccess; 
			for(int j = 0; j<tmp2.size(); j++) { //tmp2 의 사이즈가 0 이면 실행 안됨
				int from = tmp2.get(j).indexOf("("); //첫 (
				int to = tmp2.get(j).indexOf(")"); //첫 )
				tmp1.add(tmp2.get(j).substring(from+1, to)); //tmp1에 괄호 안의 내용 저장. 없으면 "" 저장
				tmp3.add(tmp2.get(j).substring(0,from)); //반환형과 이름
				//접근제어자 추출
				kw.mlist.add(new Methods(kw.alist.get(i).name));
			}
		}
		for(int j = 0; j<tmp3.size();j++) {
			//이름 &반환형 추출
			if(tmp3.get(j).contains("Stack")) { //생성자, 소멸자
				kw.mlist.get(j).name = tmp3.get(j).trim();
				kw.mlist.get(j).type = null;
			}
			else {
				kw.mlist.get(j).name = doParsing(tmp3.get(j),2," ","").get(0);
				kw.mlist.get(j).type = doParsing(tmp3.get(j), 2, ""," ").get(0);
			}
			//매개변수 추출 
			if(tmp1.get(j).equals("")) //매개변수 없을때
				kw.mlist.get(j).parameter = null;
			else
				kw.mlist.get(j).parameter = doParsing(tmp1.get(j), 2, ""," ").get(0);
		}
		
		//메소드 내용 추출
		tmp1.clear(); tmp2.clear();	
		String delete = doParsing(raw, 0, "","};").get(0);
		String dlt = raw.replace(delete, "");
		tmp1 = doParsing(dlt, "Stack::", "", "Stack::");
		for(int i =0; i<tmp1.size(); i++) {
			tmp2.add(tmp1.get(i)); //.replace("Stack::", "")
			int from = tmp2.get(i).indexOf("{");
			int to = tmp2.get(i).lastIndexOf("}");
			for(int j = 0; j< kw.mlist.size(); j++) {
				String n = kw.mlist.get(j).name;
				if(tmp2.get(i).contains(":"+n)) { //소멸자가 아닐경우
					kw.mlist.get(j).allContents = tmp2.get(i).substring(from+1, to);
				}
			}
		}
		//소멸자의 메소드 내용
		for(int j = 0; j<kw.mlist.size(); j++) {
			if(kw.mlist.get(j).name.contains("~")) {
				for(int i =0; i<temp2.size(); i++) {
					if(temp2.get(i).contains(kw.mlist.get(j).name)) {
						int from = temp2.get(i).indexOf("{");
						kw.mlist.get(j).allContents = temp2.get(i).substring(from+1);;
					}
				}
			}
		}
		
		
		//6단계: 메소드 안의 변수 vinm
		for(int i =0;i<kw.mlist.size();i++) {
			for(int j = 0; j<kw.vlist.size(); j++) {
				if(kw.mlist.get(i).allContents == null) { //내용이 없는 경우
					kw.mlist.get(i).vinm = null;
				}
				else if(kw.mlist.get(i).allContents.contains(kw.vlist.get(j).name)) {
					kw.mlist.get(i).vinm.add(kw.vlist.get(j));
				}
			}
		}
		
		//7단계 변수를 사용하는 메소드 musingv
		for(int i =0;i<kw.mlist.size();i++) {
			ArrayList<Variables> vtmp = kw.mlist.get(i).vinm;
				for(int k = 0; k<kw.vlist.size(); k++) {
					int w = vtmp.indexOf(kw.vlist.get(k));
					if(w != -1) {
						kw.vlist.get(k).musingv.add(kw.mlist.get(i));
					}
				}
		}
		
		
		//8단계 클래스 안의 메소드와 변수 minc, vinc
		for(Methods m : kw.mlist)
			kw.clist.get(0).minc.add(m);
		for(Variables v : kw.vlist)
			kw.clist.get(0).vinc.add(v);
		
		System.out.println("algorithm()성공...!");
	} //algorithm
	
	public void print() {
		//클래스
		System.out.println("===============CLASS===============");
		System.out.println("kw.clist.get(0).name: "+kw.clist.get(0).name);
		System.out.print("kw.clist.get(0).minc: ");
		for(Methods m : kw.clist.get(0).minc)
			System.out.print(m.name+"   ");
		System.out.println();
		System.out.print("kw.clist.get(0).vinc: ");
		for(Variables v : kw.clist.get(0).vinc)
			System.out.print(v.name+"   ");
		System.out.println();
		
		//변수
		System.out.println("===============VARIABLE===============");
		for(int j = 0; j<kw.vlist.size(); j++) {
			System.out.println("kw.vlist.get("+j+").name: "+kw.vlist.get(j).name);
			System.out.println("kw.vlist.get("+j+").access: "+kw.vlist.get(j).access);
			System.out.println("kw.vlist.get("+j+").type: "+kw.vlist.get(j).type);
			System.out.print("kw.vlist.get("+j+").musingv: ");
			for(Methods m : kw.vlist.get(j).musingv)
				System.out.print(m.name+"   ");
			System.out.println("\n");
			
		}
		//메소드
		System.out.println("===============METHOD===============");
		for(int i = 0; i<kw.mlist.size(); i++) {
			System.out.println("kw.mlist.get("+i+").name: "+kw.mlist.get(i).name);
			System.out.println("kw.mlist.get("+i+").access: "+kw.mlist.get(i).access);
			System.out.println("kw.mlist.get("+i+").type: "+kw.mlist.get(i).type);
			System.out.println("kw.mlist.get("+i+").parameter: "+kw.mlist.get(i).parameter);
			System.out.print("kw.mlist.get("+i+").vinm.get(j).name: ");
			for(int j = 0; j<kw.mlist.get(i).vinm.size(); j++) {
				System.out.print(kw.mlist.get(i).vinm.get(j).name+"   ");
			}
			System.out.println("\nkw.mlist.get("+i+").allContents: "+kw.mlist.get(i).allContents);
			System.out.println();
		}
		
		
	}
	
	public Parsing(ReadFileClass rd, String sourcefile){ 
		if(rd.getBuffer() != null) { //에러가 안나면
			raw = sourcefile;
			Token tkn = new Token(sourcefile);
			str = tkn.contents;
		}
	}
	
/*	void init(){ //테스트용 초기화
		ReadFileData rd = new ReadFileData("Stack.h_text.txt");
		if(rd.buffer != null) { //에러가 안나면
			raw = rd.buffer.toString();
			Token tkn = new Token(rd);
			tkn.doAll();
			str = tkn.contents;
		}
		else System.out.println(rd.errmessage);
	}
*/	
	public ArrayList<String> doParsing(String str,int arrNum, String f, String t) {
		if(str == null || str.equals("")) {
			return null;
		}
		else {
			ArrayList<String> list = new ArrayList<String>();
			String adder;
				if(arrNum == 1) adder=":"; //접근제어자 뒤에는 ":"
				else adder = " "; //나머지는 " "
			int s = 0;
			int e = str.length();
			
			for(int i = 0; i<keyword[arrNum].length ; i++){
				int r = str.indexOf(keyword[arrNum][i]+adder);
				while(s<e) {
					if(r!=-1) {
						s = r+((keyword[arrNum][i]).length());
						int from;
							if(f.equals("")) from = r-1; //키워드의 시작부터
							else from = str.indexOf(f, s);
						int to;
							if(t.equals("")) to = str.length(); //str의 끝까지
							else to = str.indexOf(t, s);
						list.add(str.substring(from+1, to));
					}
					else {
						s++;
					}
					r = str.indexOf(keyword[arrNum][i]+adder, s);
				}
				s=0;
			}
			return list;
		}
	}
	public ArrayList<String> doParsing(String str, String deli, String f, String t) {
		if(str == null || str.equals("")) {	return null; }
		else {
			ArrayList<String> list = new ArrayList<String>();
			int s = 0;
			int e = str.length();
			int p = 0;
			while(p<str.length()){
				int r = str.indexOf(deli);
				while(s<e) {
					if(r!=-1) {
						s = r+(deli.length());
						int from;
							if(f.equals("")) from = r-1; //키워드의 시작부터
							else from = str.indexOf(f, s);
						int to;
							if(t.equals("")) to = str.length(); //str의 끝까지
							else to = str.indexOf(t, s);
							if(to == -1) to = str.length(); 
						list.add(str.substring(from+1, to));
					}
					else {
						s++;
					}
					r = str.indexOf(deli, s);
				}
				p=s;
				s=0;
			}
			return list;
		}
	}

}
