package project.GUI;
import java.awt.BorderLayout;
import javax.swing.*;

public class MethodMemberClass extends JFrame{
	
	private JTextArea textArea;
	private JLabel uselabel;
	public JPanel Panel;
	private String variableList = null;

	public MethodMemberClass(Element element) {
		
		Panel = new JPanel();
		Panel.setLayout(new BorderLayout());
		
		uselabel = new JLabel("Use");
		textArea = new JTextArea(10, 20);
		
		Panel.add(uselabel, BorderLayout.NORTH);
		Panel.add(textArea, BorderLayout.CENTER);
		
		textArea.setEditable(false);

	}
	
	public void setTextArea(Element element) {
		variableList = "";
		for(int i=0;i<element.variableList.size();i++) {
			variableList += element.variableList.get(i)+'\n';
		}
		
		textArea.setText(variableList);
	}
	
}
