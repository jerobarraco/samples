/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Nueva_Inversion.java
 *
 * Created on 13/06/2011, 22:19:31
 */

package dbapp.forms;
import dbapp.Inversion;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrador
 */
public class Nueva_Inversion extends javax.swing.JDialog {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    /** Creates new form Nueva_Inversion */
    public Nueva_Inversion(java.awt.Frame parent, boolean modal) {
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

    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jTextField1 = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jTextField2 = new javax.swing.JTextField();
    jTextField3 = new javax.swing.JTextField();
    jFormattedTextField1 = new javax.swing.JFormattedTextField();
    jFormattedTextField2 = new javax.swing.JFormattedTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setName("Form"); // NOI18N

    org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(dbapp.DBApp.class).getContext().getResourceMap(Nueva_Inversion.class);
    jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
    jButton1.setName("jButton1"); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
    jButton2.setName("jButton2"); // NOI18N
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
    jTextField1.setName("jTextField1"); // NOI18N
    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });

    jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
    jLabel1.setName("jLabel1"); // NOI18N

    jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
    jLabel2.setName("jLabel2"); // NOI18N

    jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
    jLabel3.setName("jLabel3"); // NOI18N

    jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
    jLabel4.setName("jLabel4"); // NOI18N

    jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
    jLabel5.setName("jLabel5"); // NOI18N

    jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
    jTextField2.setName("jTextField2"); // NOI18N

    jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
    jTextField3.setName("jTextField3"); // NOI18N

    jFormattedTextField1.setText(resourceMap.getString("jFormattedTextField1.text")); // NOI18N
    jFormattedTextField1.setName("jFormattedTextField1"); // NOI18N

    jFormattedTextField2.setText(resourceMap.getString("jFormattedTextField2.text")); // NOI18N
    jFormattedTextField2.setName("jFormattedTextField2"); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jButton1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton2))
          .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addComponent(jLabel2)
              .addComponent(jLabel3)
              .addComponent(jLabel4)
              .addComponent(jLabel5))
            .addGap(30, 30, 30)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
              .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
              .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
              .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
              .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel5)
          .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jButton1)
          .addComponent(jButton2))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

		private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

			try {
					i.setNombre(jTextField1.getText());
					i.setMonto(Double.parseDouble(this.jTextField2.getText()));
					i.setPorcentaje( Double.parseDouble(this.jTextField3.getText()));

					i.setFechaIni(new Date(sdf.parse(this.jFormattedTextField1.getText()).getTime()));
					i.setFechaFin(new Date(sdf.parse(this.jFormattedTextField2.getText()).getTime()));
			} catch (ParseException ex) {
						Logger.getLogger(Nueva_Inversion.class.getName()).log(Level.SEVERE, null, ex);
			}
			result=true;
			
			i.save();
			setVisible(false);
		}//GEN-LAST:event_jButton1ActionPerformed

		private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
			result=false;
			setVisible(false);
		}//GEN-LAST:event_jButton2ActionPerformed

		private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
			// TODO add your handling code here:
		}//GEN-LAST:event_jTextField1ActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JFormattedTextField jFormattedTextField1;
  private javax.swing.JFormattedTextField jFormattedTextField2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JTextField jTextField1;
  private javax.swing.JTextField jTextField2;
  private javax.swing.JTextField jTextField3;
  // End of variables declaration//GEN-END:variables

	//nuestro
	public boolean result;
	private Inversion i;
	
	public void setInv(Inversion pI){
		i=pI;
		jTextField1.setText(i.getNombre());
		jTextField2.setText(i.getMonto().toString());
		jTextField3.setText(i.getPorcentaje().toString());
		jFormattedTextField1.setText(sdf.format(i.getFechaIni()));
		jFormattedTextField2.setText(sdf.format(i.getFechaFin()));
	}
}