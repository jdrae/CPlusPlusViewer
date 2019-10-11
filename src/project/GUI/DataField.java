package project.GUI;
import java.awt.BorderLayout;
import javax.swing.*;

public class DataField extends JFrame{
	public JPanel mainPanel;
	private JPanel namePanel, methodsPanel;
	private JTextField nameText, methodsText;
	
	private String methodList = null;
	public DataField(Element element) {
		
		mainPanel = new JPanel();
		
		namePanel = new JPanel();
		methodsPanel = new JPanel();
		
		namePanel.setLayout(new BorderLayout());
		methodsPanel.setLayout(new BorderLayout());
		
		nameText = new JTextField(10);
		nameText.setEditable(false);
		
		namePanel.add(new JLabel("Name"), BorderLayout.NORTH);
		namePanel.add(nameText, BorderLayout.SOUTH);
		
		methodsText = new JTextField(20);
		methodsText.setEditable(false);
		
		methodsPanel.add(new JLabel("Methods"), BorderLayout.NORTH);
		methodsPanel.add(methodsText, BorderLayout.SOUTH);
		
		mainPanel.add(namePanel);
		mainPanel.add(methodsPanel);
		
	}
	
	public void setData(Element element) {
		nameText.setText(element.name);;
		
		methodList = "";
		for(int i=0;i<element.methodsList.size();i++) {
			methodList += element.methodsList.get(i);
			if(i!=element.methodsList.size()-1) methodList += ", ";
		}
		methodsText.setText(methodList);
	}
}
