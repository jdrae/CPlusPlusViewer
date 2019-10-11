package project.GUI;
import javax.swing.*;

public class EditablePageClass extends JFrame{
	public JPanel panel;
	private JTextArea textArea;
	
	public EditablePageClass(Element element) {
		
		panel = new JPanel();
		textArea = new JTextArea(10, 70);
		textArea.setEditable(true);
		//TODO 메서드 소스 코드 불러오기
		
		panel.add(textArea);
		
	}
	
	public void setTextArea(Element element) {
		textArea.setText(element.sourceCode);
	}
}
