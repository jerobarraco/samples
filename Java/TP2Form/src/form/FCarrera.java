package form;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import model.*;
public class FCarrera extends JDialog implements ActionListener {
	private boolean result = false;
	private Carrera carrera;
	private int c;
	public void setCarrera(Integer c){
		result = false; //porque me da fiaca sobreescribir el show
		this.c = c;
		if (c<0)
			carrera = new Carrera();
		else
			carrera = m.uni.getCarrera(c);
		
		tNombre.setText(carrera.getNombre());
		tTitulo.setText(carrera.getTitulo());
		tMaterias.setText(carrera.getMaterias().toString());
		tAños.setText(carrera.getAños().toString());
	}
	public boolean result(){
		return result;
	}
	/**
	 *Formstuff 
	 */
	private static final long serialVersionUID = -7768260263349502588L;
	private JTextField tNombre = new JTextField();
	private JTextField tTitulo = new JTextField();
	private JTextField tMaterias = new JTextField();
	private JTextField tAños = new JTextField();
	
	private String acOk = "Ok", acCancel="Cancel";
	private JButton bOk = new JButton(acOk);
	private JButton bCancel = new JButton(acCancel);
	
	public FCarrera(JFrame parent){
		super(parent, "Agregar Carrera", true);
		setForm();
	}
	private void setForm(){
		bOk.addActionListener(this);
		bCancel.addActionListener(this);
		JPanel s = new JPanel();
		s.setLayout(new FlowLayout(FlowLayout.RIGHT));
		s.add(bOk); 
		s.add(bCancel);
		
		JPanel c= new JPanel();
		c.setLayout(new GridLayout(4, 2));
		c.add(new JLabel("Nombre"));
		c.add(tNombre);
		c.add(new JLabel("Titulo"));
		c.add(tTitulo);
		c.add(new JLabel("Materias"));
		c.add(tMaterias);
		c.add(new JLabel("Años"));
		c.add(tAños);
		
		this.setLayout(new BorderLayout());
		this.add(c, BorderLayout.CENTER);
		this.add(s, BorderLayout.SOUTH);
		this.pack();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals(acOk)){
			carrera.setNombre(tNombre.getText());
			carrera.setTitulo(tTitulo.getText());
			carrera.setMaterias(Integer.parseInt(tMaterias.getText()));
			carrera.setAños(Integer.parseInt(tAños.getText()));
			if (this.c<0){
				m.uni.addCarrera(carrera);
			}
			result = true;
		}
		setVisible(false);
	}

}
