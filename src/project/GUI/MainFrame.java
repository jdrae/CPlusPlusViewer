package project.GUI;
import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame{ 

	public JPanel panel1 = new JPanel();		//트리
	public JPanel panel2 = new JPanel();		//멤버
	public JPanel panel3 = new JPanel();		//cardLayout
	private JPanel leftpanel = new JPanel(); 	//트리+멤버 패널
	
	private CardLayout cards1 = new CardLayout();
	private CardLayout cards2 = new CardLayout();
	private CardLayout cards3 = new CardLayout();

//	private MethodMemberClass m;
//	private TableClass t;
//	private EditablePageClass e;
//	private DataField d;
//	private TreeClass tree;

	
	public MainFrame(){
		
		panel1.setLayout(cards1);
		panel2.setLayout(cards2);
		panel3.setLayout(cards3);
		
		leftpanel.setLayout(new GridLayout(2, 1));
		setLayout(new BorderLayout());
		
//		m = new MethodMemberClass();
//		t = new TableClass();
//		e = new EditablePageClass();
//		d = new DataField();
//		tree = new TreeClass(this);
				
		panel1.add("empty", new JPanel());					//빈 화면
//		panel1.add("tree", tree.panel);						//open file시
		
		panel2.add("empty", new JPanel());					//빈화면
//		panel2.add("use", m.Panel);							//사용중인 field		
		
		panel3.add("empty", new JPanel());					//처음 화면
//		panel3.add("table", new JScrollPane(t.table));		//클래스 선택시
//		panel3.add("method", e.panel);						//메서드 선택시
//		panel3.add("data", d.mainPanel);					//자료 선택시
		
		leftpanel.add(panel1);
		leftpanel.add(panel2);
		
		add(leftpanel, BorderLayout.WEST);
		add(panel3, BorderLayout.CENTER);
		
	}
	
	public void changePanel(int i) {
		if(i==1) cards1.next(this.getContentPane());
		if(i==2) cards2.next(this.getContentPane());
		if(i==3) cards3.next(this.getContentPane());
	}
	
	public CardLayout getCardLayout(int i) {
		if(i==1) return cards1;
		if(i==2) return cards2;
		if(i==3) return cards3;
		else return null;
	}
  
	
}



