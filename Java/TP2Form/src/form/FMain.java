package form;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FMain 
	extends JFrame
	implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500006548956921511L;
	
	
	
	//interface objects
	private FAlumno fa;
	private FCarrera fc;
	
	
	
	private String 
		acAddAl = "Agregar/Modificar Alumno", 
		acAddC = "Agregar/Modificar Carrera",
		acPrint = "Imprimir en Consola",
		acDelAl = "Eliminar Alumno",
		acDelC = "Eliminar Carrera";
	
	private JButton 
		bAddAl = new JButton(acAddAl), 
		bAddC = new JButton(acAddC),
		bPrint = new JButton(acPrint),
		bDelAl = new JButton(acDelAl),
		bDelC = new JButton(acDelC);
	
	private JList lAlumnos, lCarreras;	
	
	public FMain(){
		super();
		setForm();		
	}
	public Integer getSelectedAlumno(){
		return Integer.parseInt(this.lAlumnos.getSelectedValue().toString().split(" ")[0]);
	}
	public void showAlumno(){
		Integer lib;
		if (this.lAlumnos.getSelectedIndex()>=0){
			//pequeño truco para robarnos el numero de libreta
			lib = getSelectedAlumno();
		}else{
			try{
				lib = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de libreta"));
			}catch(Exception e){
				return;
			}	
		}
		fa.setAlumno(lib);
		fa.setVisible(true);
	}
	public void delAlumno(){
		if (lAlumnos.getSelectedIndex()<0){return;}
		
		m.uni.delAlumno(getSelectedAlumno());
	}
	public void showCarrera(){
		fc.setCarrera(this.lCarreras.getSelectedIndex());
		fc.setVisible(true);
	}
	public void delCarrera(){
		int s = lCarreras.getSelectedIndex(); 
		if (s<0){return;}
		m.uni.delCarrera(s);
	}
	public void refresh(){
		DefaultListModel lm = new DefaultListModel();
		for(Integer i:m.uni.getAlumnosLibs()){
			lm.addElement(m.uni.getAlumno(i).toString());
		}
		lAlumnos.setModel(lm);
		
		lm = new DefaultListModel();
		for(int i=0; i< m.uni.getCantCarreras(); i++){
			lm.addElement(m.uni.getCarrera(i).toString());
		}
		lCarreras.setModel(lm);
	}
	
	//cosas molestas de los forms
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String ac = arg0.getActionCommand();
		if(ac.equals(acPrint)){
			for (Integer i: m.uni.getAlumnosLibs()){
				System.out.println("\n------\n");
				System.out.println(m.uni.getAlumno(i));
			}
			return;//porque no necesita refrescar
		}
		
		if (ac.equals(acAddAl)){
			//todo reemplazar con lista
			showAlumno();
		}
		if (ac.equals(acAddC)){
			showCarrera();
		}
		if (ac.equals(acDelAl)){
			delAlumno();
		}
		if (ac.equals(acDelC)){
			delCarrera();
		}
		refresh();
	}
	private void setForm(){
		//form stuff
		//create stuff
		fa = new FAlumno(this);
		fc = new FCarrera(this);
		
		bAddAl.addActionListener(this);
		bAddC.addActionListener(this);
		bPrint.addActionListener(this);
		bDelAl.addActionListener(this);
		bDelC.addActionListener(this);
		
		JPanel s = new JPanel();
		s.setLayout(new GridLayout(2, 3));
		s.add(bAddAl);
		s.add(bAddC);
		s.add(bPrint);
		s.add(bDelAl);
		s.add(bDelC);
		
		//Create center and add

		lAlumnos  = new JList();
		lAlumnos.setPreferredSize(new Dimension(250, 80));
		//lAlumnos.setAlignmentX(LEFT_ALIGNMENT);
		
		lCarreras = new JList();
		lCarreras.setPreferredSize(new Dimension(250, 80));
		//lAlumnos.setAlignmentX(LEFT_ALIGNMENT);
		
		JPanel center = new JPanel();
		center.setLayout (new BoxLayout(center, BoxLayout.PAGE_AXIS));
				
		center.add(new JLabel("Alumnos"));
		center.add(Box.createRigidArea(new Dimension(0,5)));
		center.add(new JScrollPane(lAlumnos));
		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		center.add(new JLabel("Carreras"));
		center.add(Box.createRigidArea(new Dimension(0,5)));
		center.add(new JScrollPane(lCarreras));
		center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		
		this.setLayout(new BorderLayout());
		this.add(s, BorderLayout.SOUTH);
		this.add(center, BorderLayout.CENTER);
		pack();
	}
}
