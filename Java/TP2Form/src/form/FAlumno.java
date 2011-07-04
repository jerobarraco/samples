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
	private int ia;
	public void setAlumno(Integer pa){
		result = false;
		ia = pa;
		if (m.uni.getAlumnosLibs().contains(ia)){
			al = m.uni.getAlumno(ia);
		}else{
			al = new Alumno(ia);
		}
		
		tLibreta.setText(al.getLibreta().toString());
		tDni.setText(al.getDni().toString());
		tNombre.setText(al.getNombre());
		tApellido.setText(al.getApellido());	
		tTelefono.setText(al.getTelefono());
		tDireccion.setText(al.getDireccion());
		tAprobadas.setText(al.getAprobadas().toString());
		DefaultComboBoxModel lm = new DefaultComboBoxModel();
		for (int i = 0; i< m.uni.getCantCarreras();i++){
			lm.addElement(m.uni.getCarrera(i));
		}
		cCarreras.setModel(lm);
		cCarreras.setSelectedIndex(-1);
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
	private JTextField tAprobadas = new JTextField();
	private JComboBox cCarreras = new JComboBox();
	
	public FAlumno(JFrame parent){
		super(parent, "Agregar Alumno", true);
		setForm();
	}
	/*usando setAlumno evitamos usar el show
	public void show(){
		
	}*/
	public boolean result(){
		return result;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals(acOk)){
			al.setDni(Long.parseLong(tDni.getText()));
			al.setNombre(tNombre.getText());
			al.setApellido(tApellido.getText());
			al.setTelefono(tTelefono.getText());
			al.setDireccion(tDireccion.getText());
			al.setAprobadas(Integer.parseInt(tAprobadas.getText()));
			int sel = this.cCarreras.getSelectedIndex();
			if (sel>=0){
				al.setCarrera(m.uni.getCarrera(sel));
			}
			if (!m.uni.getAlumnosLibs().contains(ia)){
				m.uni.addAlumno(al);
			}
			result = true;
		}
		setVisible(false);
	}
	private void setForm(){
		bOk.addActionListener(this);
		bCancel.addActionListener(this);
		
		JPanel c= new JPanel();
		c.setLayout(new GridLayout(8, 2));
		c.add(new JLabel("Libreta"));
		c.add(tLibreta);
		
		c.add(new JLabel("Dni"));
		c.add(tDni);
		
		c.add(new JLabel("Nombre"));
		c.add(tNombre);
		
		c.add(new JLabel("Apellido"));
		c.add(tApellido);
		
		c.add(new JLabel("Telefono"));
		c.add(tTelefono);
		
		c.add(new JLabel("Dirección"));
		c.add(tDireccion);
		
		c.add(new JLabel("Aprobadas"));
		c.add(tAprobadas);
		
		c.add(new JLabel("Carrera"));
		c.add(cCarreras);
		
		JPanel s = new JPanel();
		s.setLayout(new FlowLayout(FlowLayout.RIGHT));
		s.add(bOk); s.add(bCancel);
		
		this.setLayout(new BorderLayout());
		this.add(s, BorderLayout.PAGE_END);
		this.add(c, BorderLayout.CENTER);
		this.pack();
	}
}
