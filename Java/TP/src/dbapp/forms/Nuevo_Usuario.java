/*
 * Nuevo_Usuario.java
 *
 * Created on 10/06/2011, 16:57:25
 */

package dbapp.forms;

import org.jdesktop.application.Action;
import dbapp.User;
import javax.swing.JOptionPane;

/**
 *
 * @author Ale
 */
public class Nuevo_Usuario extends javax.swing.JDialog {

    /** Creates new form Nuevo_Usuario */
    public Nuevo_Usuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    label1 = new java.awt.Label();
    label2 = new java.awt.Label();
    label3 = new java.awt.Label();
    label4 = new java.awt.Label();
    label5 = new java.awt.Label();
    label6 = new java.awt.Label();
    jTextField1 = new javax.swing.JTextField();
    jTextField2 = new javax.swing.JTextField();
    jTextField3 = new javax.swing.JTextField();
    jTextField4 = new javax.swing.JTextField();
    jTextField5 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setName("Form"); // NOI18N

    label1.setName("label1"); // NOI18N
    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(dbapp.DBApp.class).getContext().getResourceMap(Nuevo_Usuario.class);
    label1.setText(resourceMap.getString("label1.text")); // NOI18N

    label2.setName("label2"); // NOI18N
    label2.setText(resourceMap.getString("label2.text")); // NOI18N

    label3.setName("label3"); // NOI18N
    label3.setText(resourceMap.getString("label3.text")); // NOI18N

    label4.setName("label4"); // NOI18N
    label4.setText(resourceMap.getString("label4.text")); // NOI18N

    label5.setName("label5"); // NOI18N
    label5.setText(resourceMap.getString("label5.text")); // NOI18N

    label6.setName("label6"); // NOI18N
    label6.setText(resourceMap.getString("label6.text")); // NOI18N

    jTextField1.setText(resourceMap.getString("tbxApellido.text")); // NOI18N
    jTextField1.setName("tbxApellido"); // NOI18N

    jTextField2.setText(resourceMap.getString("tbxNombre.text")); // NOI18N
    jTextField2.setName("tbxNombre"); // NOI18N

    jTextField3.setText(resourceMap.getString("tbxNUsuario.text")); // NOI18N
    jTextField3.setName("tbxNUsuario"); // NOI18N

    jTextField4.setText(resourceMap.getString("tbxContraseña.text")); // NOI18N
    jTextField4.setName("tbxContraseña"); // NOI18N

    jTextField5.setText(resourceMap.getString("tbxDNI.text")); // NOI18N
    jTextField5.setName("tbxDNI"); // NOI18N

    javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(dbapp.DBApp.class).getContext().getActionMap(Nuevo_Usuario.class, this);
    jButton1.setAction(actionMap.get("ok")); // NOI18N
    jButton1.setText(resourceMap.getString("btnGuardarU.text")); // NOI18N
    jButton1.setName("btnGuardarU"); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jButton2.setAction(actionMap.get("cancelar")); // NOI18N
    jButton2.setText(resourceMap.getString("btnCancelarU.text")); // NOI18N
    jButton2.setName("btnCancelarU"); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jButton1)
          .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .addComponent(jButton2)
          .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(layout.createSequentialGroup()
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
              .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
          .addGroup(layout.createSequentialGroup()
            .addGap(63, 63, 63)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addGap(7, 7, 7)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(6, 6, 6)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(jButton1)
              .addComponent(jButton2)))
          .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(22, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
			// TODO add your handling code here:
			result = true;
			this.setVisible(false);
		}//GEN-LAST:event_jButton1ActionPerformed

   
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JTextField jTextField1;
  private javax.swing.JTextField jTextField2;
  private javax.swing.JTextField jTextField3;
  private javax.swing.JTextField jTextField4;
  private javax.swing.JTextField jTextField5;
  private java.awt.Label label1;
  private java.awt.Label label2;
  private java.awt.Label label3;
  private java.awt.Label label4;
  private java.awt.Label label5;
  private java.awt.Label label6;
  // End of variables declaration//GEN-END:variables


	//Cosas nuestras
	private User u;
	public boolean result;

	@Action
	public void cancelar() {
		result = false;
		setVisible(false);
	}

	public void setUsuario(User pu){
		this.u = pu;
		jTextField1.setText(u.getNombre());
		jTextField2.setText(u.getApellido());
		jTextField3.setText(u.getUser());
		jTextField4.setText(u.getPassword());
		jTextField5.setText(u.getDni().toString());
	}

	@Action
	public void ok() {
		//no podemos permitir que creen un usuario admin
		if (jTextField3.getText().equals("admin")){
				JOptionPane.showMessageDialog(null, "Imposible crear un usuario admin.\nI'm sorry, Dave. I'm afraid I can't do that.");
				return;
		}
		u.setNombre(jTextField1.getText());
		u.setApellido(jTextField2.getText());
		if (u.getUser().equals("admin")){
				//no se le puede cambiar el "nombre de usuario" al administrador
				JOptionPane.showMessageDialog(null, "No puede cambiar el nombre de usuario. Los demás datos han sido guardados. \nI'm sorry, Dave. I'm afraid I can't do that.");
		}else {
				u.setUser(jTextField3.getText());
		}

		u.setPassword(jTextField4.getText());
		u.setDni(Integer.parseInt(jTextField5.getText()));
		u.save();
		result = true;
		setVisible(false);
	}
	
}