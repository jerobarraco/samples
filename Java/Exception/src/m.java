import java.awt.BorderLayout;
import javax.swing.*;
import excepciones.excep;


public class m extends JFrame {
	public m(){
		super("mititulo");
        this.setSize(400,500);
        this.setTitle("Primer Aplicacion Swing");
        JLabel aLabel = new JLabel("Something to look at",
        		new ImageIcon("images/beach.gif"), JLabel.CENTER);
        aLabel.setVerticalTextPosition(JLabel.TOP);
        aLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.getContentPane().add(aLabel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
	}
	public static void main(String args[]) throws excep{
		new m();
		try{
			//asdfghjkl
			
			if (1==2){
				throw new excep();
			};
		}catch (excep e){
			e.printStackTrace();
		}
	}
}
