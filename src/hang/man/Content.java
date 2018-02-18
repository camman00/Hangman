package hang.man;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Content extends JPanel {
	private JLabel label1,label2,label3,label4;
	public Content() {
		label1 = label2 = label3 = label4 = new JLabel();
		label1.setText("Guess a letter");
		label2.setText("");
		label3.setText("You have 9 lives left");
		label4.setText("You have 0 solved words");
		this.add(label1);
		label3.setBounds(label3.getX(), label3.getY() + 100, label3.getWidth(), label3.getHeight());
		this.add(label3);
		System.out.println(label1 + " " + label3);
		
	}

}
