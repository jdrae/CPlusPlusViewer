import project.GUI.*;
import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		
		JFrame f = new JFrame("C++Viewer");
		MainFrame m = new MainFrame();
		FileClass file = new FileClass(m);
		
		f.setJMenuBar(file.getJMenuBar());
		f.getContentPane().add(m.getContentPane());

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1280, 800);
		f.setVisible(true);
		f.setResizable(false);	
	}
}
