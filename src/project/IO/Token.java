package project.IO;
//토큰 저장
//ReadFileData 클래스 필요
import java.util.*;

import project.GUI.ReadFileClass;

public class Token {
	ReadFileClass rd;
	String contents; //나뉘기전 상태 (주석은 생성자에서 제거됨)
	//int finalCount; //토큰의 개수
	static ArrayList<String> finalArr; //나뉘어진 총 토큰
	String[] allComments; //주석을 저장함
	
	//file 을 받는 생성자 및 기본 생성자(test.txt)
	public Token(String sourcefile) {
		this.contents = sourcefile;
		String tempArr[] = split(contents, "\n", false);
		int tempCount = len(contents, "\n");
		allComments = comment(tempArr, tempCount); //주석추출
		for(int i = 0; i<allComments.length; i++) {
			contents = contents.replaceAll(allComments[i], "");
		}
		contents = contents.replaceAll("(\r\n|\r|\n|\n\r|\t)", ""); // 공백 없애기 
		
		/* 결과:
			class testClass {int i = 0; String v = null; public void testMethod(String s){System.out.println(s+"testText");}}
		 */
	}
	
	//데이터 길이 반환
	public static int len(String file, String deli) {
		StringTokenizer st = new StringTokenizer(file,deli);
		return st.countTokens();
	}
	public static int len(String[] arr, String deli) {
		String str="";
		int count =0;
		for(int z=0; z<arr.length; z++) {
			StringTokenizer st = new StringTokenizer(arr[z],deli);
			while(st.hasMoreTokens()) {
				str=str+st.nextToken();
				count++;
			}
		}
		return count;
	}
	
	//나뉜 값 저장
	public static String[] split(String file, String deli, boolean tf) {
		StringTokenizer st = new StringTokenizer(file, deli, tf);
		String arr[] = new String[st.countTokens()];
		int i = 0;
		while(st.hasMoreTokens()) {
			arr[i] = st.nextToken();
			i++;
		}
		return arr;
	}
	
	//두번째 deli 이후로
	public static String plus(String[] arr, String deli) {
		String str = "";
		for(int z = 0; z<arr.length; z++) {
			StringTokenizer st = new StringTokenizer(arr[z], deli);
			while(st.hasMoreTokens()) {
				str = str+deli+st.nextToken();
			}
		}
		return str;
	}
	
	
	//한번에
	static public ArrayList<String> finalResult(String contents, String[] deli, boolean tf) {
		String[] arr1 = null, arr2 = null;
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i<deli.length; i++) {
			if(i==0) {
				arr1 = new String[len(contents,deli[i])];
				arr1 = split(contents,deli[i], tf);
			}
			else {
				arr2=new String[len(arr1,deli[i])];
				String sum = plus(arr1,deli[i]);
				arr2 = split(sum,deli[i], tf);
				arr1 = arr2;
			}
		}
		for(int i = 0; i<arr1.length; i++) {
			list.add(arr1[i]);
			list.get(i);
		}
		return list;
		//finalCount = len(finalArr,deli[deli.length-1]);
	}
	
	//마지막 결과 출력. finalResult() 호출 후에 사용가능
	static public void printFinalResult(String[] str, int finalCount) {
		//System.out.println(contents+"\n");
		for(int i = 0; i<finalCount; i++) {
			System.out.print("###"+i+"###");
			System.out.println(str[i]);
		}
	}
	static public void printFinalResult(ArrayList<String> str, int finalCount) {
		//System.out.println(contents+"\n");
		for(int i = 0; i<finalCount; i++) {
			System.out.print("###"+i+"###");
			System.out.println(str.get(i));
		}
	}
	 
	
	public String[] comment(String[] arr, int len){
		ArrayList<String> cmt = new ArrayList<String>();
		String[] cmts;
		for(int i = 0; i<len; i++) {
			int s = arr[i].indexOf("//");
			//System.out.println("s: "+s+", e: "+e);
			if(s != -1) {
				//System.out.println("found \"//\" in "+s);
				//System.out.println(arr[i].substring(s));
				cmt.add(arr[i].substring(s));
			}
		}
		cmts = new String[cmt.size()];
		for(int j = 0; j<cmt.size();j++) {
			cmts[j] = cmt.get(j);
			
		}
		return cmts;
	}
	
//	public void doAll() {
//		String[] deli = {""}; //나눌 delimeter 순서대로 배열 생성
//		finalArr = finalResult(contents, deli, false);
//	}
	
	
}
