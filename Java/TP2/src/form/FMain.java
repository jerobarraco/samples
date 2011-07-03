package form;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import model.*;

public class FMain 
	extends JFrame
	implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500006548956921511L;
	private Map<Long, Alumno> alumnos = new TreeMap<Long, Alumno>();
	private Universidad uni ;
	
	//interface objects
	private FAlumno fa ;
	private JButton btnAdd;
	
	private String acAddAl ="Agregar alumno";
	/*
	 * private JList alumnos
	 * private JList carreras
	 */
	
	public FMain(){
		super();
		setForm();
		uni = new Universidad();
	}
	
	public void AddAlumno(){
		Integer lib =0;
		try{
			lib = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de libreta"));
		}catch(Exception e){
			return;
		}
		Alumno a = uni.addOrGet(lib);
		fa.setAlumno(a);
		fa.setVisible(true);
		if (fa.result()){ refresh();}
	}
	
	
	public void refresh(){
		
	}
	
	
	
	
	
	
	//cosas molestas de los forms
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String ac =arg0.getActionCommand();
		if (ac.equals(acAddAl)){
			AddAlumno();
		}
	}
	private void setForm(){
		//form stuff
		fa = new FAlumno(this);
		btnAdd = new JButton(acAddAl);
		btnAdd.addActionListener(this);
		
		
		
		this.setLayout(new BorderLayout());
		
		JPanel north = new JPanel();
		north.setLayout(new FlowLayout());
		north.add(btnAdd);
		
		JPanel center = new JPanel();
		center.setLayout (new FlowLayout());
		center.add(new JButton("asldk"));
	
		this.add(north, BorderLayout.PAGE_START);
		this.add(center, BorderLayout.CENTER);
		pack();
	}
}
