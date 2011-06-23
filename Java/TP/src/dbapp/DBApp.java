/*
 * DBApp.java
 */

package dbapp;
import dbapp.forms.FMain;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
/**
 * The main class of the application.
 */
public class DBApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
       show(new FMain(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of DBApp
     */
    public static DBApp getApplication() {
        return Application.getInstance(DBApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
			try{
				launch(DBApp.class, args);
			} catch(Exception e) {
				e.printStackTrace();
			}
			/*
			java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Nuevo_Usuario dialog = new Nuevo_Usuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });*/
		}
}
