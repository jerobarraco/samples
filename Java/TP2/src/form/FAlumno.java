package form;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Alumno;
public class FAlumno extends JDialog implements ActionListener{
	private static final long serialVersionUID = -6846325409296694670L;

	private boolean result=false;
	private Alumno al;

	public void setAlumno(Alumno pa){
		al=pa;
	}
	
	//iface
	private String acOk = "Ok", acCancel="Cancel";
	private JButton bOk = new JButton(acOk);
	private JButton bCancel = new JButton(acCancel);
	
	private JLabel tLibreta = new JLabel();//cant modify this! D:!
	private JTextField tDni = new JTextField();
	private JTextField tNombre = new JTextField();
	private JTextField tApellido = new JTextField();
	private JTextField tTelefono = new JTextField();
	private JTextField tDireccion = new JTextField();
	
	
	public FAlumno(JFrame parent){
		super(parent, "Agregar Alumno", true);
		setForm();
	}
	public void show(){
		result = false;
		tNombre.setText(al.getNombre());
		tLibreta.setText(al.getLibreta().toString());
		tDni.setText(al.getDni().toString());
		tApellido.setText(al.getApellido());
		tTelefono.setText(al.getTelefono());
		tDireccion.setText(al.getDireccion());
		super.show();
	}
	public boolean result(){
		return result;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals(acOk)){
			al.setNombre(tNombre.getText());
			al.setApellido(tApellido.getText());
			al.setDireccion(tDireccion.getText());
			
			tNombre.setText(al.getNombre());
			tLibreta.setText(al.getLibreta().toString());
			tDni.setText(al.getDni().toString());
			tApellido.setText(al.getApellido());
			tTelefono.setText(al.getTelefono());
			tDireccion.setText(al.getDireccion());
			result = true;
		}
		setVisible(false);
	}
	private void setForm(){
		bOk.addActionListener(this);
		bCancel.addActionListener(this);
		
		this.setLayout(new BorderLayout());
		
		JPanel c= new JPanel();
		c.setLayout(new GridLayout(2, 2));
		c.add(new JLabel("Libreta"));
		c.add(tLibreta);
		
		c.add(new JLabel("Nombre"));
		c.add(tNombre);
		
		apellido 
		direccion
		telefono 
		dni
		
		JPanel s = new JPanel();
		s.setLayout(new FlowLayout(FlowLayout.RIGHT));
		s.add(bOk); s.add(bCancel);
		
		this.add(s, BorderLayout.PAGE_END);
		this.add(c, BorderLayout.CENTER);
		this.pack();
	}
}
