package project.GUI;
import java.awt.Color;

import javax.swing.*;
import javax.swing.event.*;

import project.IO.Parsing;
import project.IO.KeyWord;

public class TreeClass extends JSplitPane{
	protected JTree tree;
	protected JTextArea display;
	
	private String fileName;
	
	public JPanel panel = new JPanel();
	
	static public Component methods, datas;
	static public DotH com;

	private MainFrame M;
	
	private Parsing p;
	
	private MethodMemberClass methodMemberClass;
	private TableClass t;
	private EditablePageClass edit;
	private DataField d;
	
	public TreeClass(MainFrame m) {
		super(VERTICAL_SPLIT);
		
		M = m;
		
		fileName = p.kw.clist.get(0).name; //클래스 이름
		
		com = new DotH(fileName);
		
		
		
		methods = com.getComponent("Methods");
		
		//파싱한 element 추가하기
		for(int i=0;i<p.kw.clist.get(0).minc.size();i++) {
			methods.add(new Element(p.kw.clist.get(0).minc.get(i).name));
		}
		
		//methods의 Element의 bool값을 T로 초기화
		for(int i=0;i<methods.size();i++) {
			methods.get(i).setBool("method");
		}
		
		//method의 type, access, parameter, 사용중인 variables, sourceCode 초기화
		for(int i=0;i<methods.size();i++) {
			methods.get(i).type = p.kw.mlist.get(i).type;
			methods.get(i).access = p.kw.mlist.get(i).access;
			methods.get(i).parameter = p.kw.mlist.get(i).parameter;
			for(int j=0;j<p.kw.mlist.get(i).vinm.size();j++) {
				methods.get(i).variableList.add(p.kw.mlist.get(i).vinm.get(j).name);
			}
			methods.get(i).sourceCode = p.kw.mlist.get(i).allContents;
		}
		
		datas = com.getComponent("Datas");
		
		//파싱한 element 추가하기
		for(int i=0;i<p.kw.vlist.size();i++) {
			datas.add(new Element(p.kw.vlist.get(i).name));
		}
		
		//Dates의 Element의 boolean값을 F로 초기화
		for(int i=0;i<datas.size();i++) {
			datas.get(i).setBool("data");
		}
		
		//data의 type, methods, musingv 초기화
		for(int i=0;i<datas.size();i++) {
			datas.get(i).type = p.kw.vlist.get(i).type;
			datas.get(i).access = p.kw.vlist.get(i).access;
			for(int j=0;j<p.kw.vlist.get(0).musingv.size();j++) {
				datas.get(i).methodsList.add(p.kw.vlist.get(i).musingv.get(j).name);
			}

		}
		
		DotHTreeModel model = new DotHTreeModel(com);
		
		tree = new JTree(model);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				Object o = e.getPath().getLastPathComponent();
				
				if(o instanceof DotH) {
					DotH d = (DotH)o;
					
					if(t==null){
						t = new TableClass(d);
						M.panel3.add("table", new JScrollPane(t.table));	
					}
			
					//card 교체
					M.getCardLayout(3).show(M.panel3, "table");
					M.getCardLayout(2).show(M.panel2, "empty");
				}
				else if (o instanceof Component) {
					return;
				}
				
				else {
					Element element = (Element)o;
					
					//method 클릭시
					if(element.bool) {

						if(edit==null && methodMemberClass==null) {
							edit = new EditablePageClass(element);
							methodMemberClass = new MethodMemberClass(element);
							
							M.panel2.add("use", methodMemberClass.Panel);			//사용중인 field		
							M.panel3.add("method", edit.panel);						//메서드 선택시
							
							edit.setTextArea(element);
							methodMemberClass.setTextArea(element);
						}
						else {
							edit.setTextArea(element);
							methodMemberClass.setTextArea(element);
						}
						
						//card 교체
						M.getCardLayout(3).show(M.panel3, "method");
						M.getCardLayout(2).show(M.panel2, "use");
					}
					
					//data 클릭시
					else {
						if(d==null) {
							d = new DataField(element);
							M.panel3.add("data", d.mainPanel);						//자료 선택시
							d.setData(element);
						}
						else {
							d.setData(element);
						}
						
						//card 교체
						M.getCardLayout(3).show(M.panel3, "data");
						M.getCardLayout(2).show(M.panel2, "empty");
					}
				}
				
			}
		});
		
		panel.add(tree);
	}

}
