package project.GUI;

import project.IO.*;
import java.awt.event.*;
import javax.swing.*;

public class FileClass extends JFrame implements ActionListener{
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem open, save, exit;
	
	private MainFrame M;
	
	//불러온 소스파일
	private String dot_h_source_file;
	
	//불러온 소스파일명
	private static String sourceFileName;
	
	private TreeClass tree;
	
	private Parsing p;
	
	public FileClass(MainFrame m) {
		
		M = m;
		
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		//Creating menuBar
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		
		menu.add(open);
		menu.add(save);
		menu.add(exit);
		
		menuBar.add(menu);
		
		open.addActionListener(this);
		save.addActionListener(this);
		exit.addActionListener(this);
		
		setJMenuBar(menuBar);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == open) {
			JFileChooser chooser = new JFileChooser();
			
			int r = chooser.showOpenDialog(this);
			
			if(r == JFileChooser.APPROVE_OPTION) {
				String sourcefile = chooser.getSelectedFile().getAbsolutePath();
				ReadFileClass fileReader = new ReadFileClass(sourcefile); 
				
				int pos = sourcefile.lastIndexOf(".");
				String ext = sourcefile.substring(pos + 1);
				
				//읽어온 파일이 .h파일이면
				if(ext.equals("h")) {
					dot_h_source_file = fileReader.getBuffer().toString();
					
					pos = sourcefile.lastIndexOf("\\");
					sourceFileName = sourcefile.substring(pos+1);
					
					//파싱진행하기
					p = new Parsing(fileReader, dot_h_source_file);
					p.algorithm();
	
					//tree 생성
					tree = new TreeClass(M);
					M.panel1.add("tree", tree.panel);						
					M.getCardLayout(1).show(M.panel1, "tree");
			
				}
				
				else {
					System.out.println(".h파일이 아닙니다...!");
				}
				
			}
		}
		
		if(source == save) {
			System.out.println("Save");
		}
		
		else if(source == exit) {
			System.exit(0);
		}
	}
	
	public static String getSourceFileName() {
		return sourceFileName;
	}
}
