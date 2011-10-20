package dos;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import models.*;
import java.util.Scanner;
import javax.swing.*;
import forms.FMain;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
/**
 * Factura 1 -- 1..* Item
 * Cliente 1--0..n factura
 * TipoFactura 1--0..n
 * documento_Comercial 
 * Factura y remito desciendende documentocomercial
 */
	//TODO ver una manera de poner todas estas funcionalidades basicas de manera genérica
//(Estas funcionalidades = listar/buscar/delete/persist/managedde los modelos)
//TODO usar alguna constante para el nombre de la tabla en los modelos.
public class Dos  extends SingleFrameApplication{
	
	@Override protected void startup() {
       show(new FMain());
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
    public static Dos getApplication() {
        return Application.getInstance(Dos.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
			try{
				launch(Dos.class, args);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		/*
		TipoFactura tf = TipoFactura.getById(100L);
		//una instancia "obtenida" ya esta managed manager.persist(tf);
		tf.setNombre("A");
		
		Factura f = Factura.getById(550L);
		 * */
		
		
		/*Item i = new Item();
		manager.persist(i);//improtante el persist al hacer new
		
		i.setDescripcion("un item falso");
		f.getItems().add(i);
		 */
		/*
		Cliente c = new Cliente();
		c.setNombre("asldk");
		c.setCuit(123456);
		
		f.setCliente(c);
		manager.persist(f);
		manager.commit();
		
		System.out.println(f.getNumero());
		
		System.out.println(f.getTipo().getNombre());//q buena onda!
		
		System.out.println("\nItems factura id 550");
		for (Item ii : f.getItems()){
			System.out.println(ii.getDescripcion());
		}
		//manager.commit();
		
		//List<Cliente> clientes = Cliente.getAll();
		//for (Cliente c : Cliente.getAll()){
		/*System.out.println("\nClientes linek %ulani%");
		for (Cliente c : Cliente.getByNameLike("ulani") ){
			System.out.println(c.getId() + " " + c.getNombre() + " Managed:"+c.managed());
		}
		
		System.out.println("\nTipoFacturas");
		for (TipoFactura t: TipoFactura.getAll()){
			System.out.println(t);
		}
		
		System.out.println("\nFacturas");
		for (Factura ff: Factura.getAll()){
			System.out.println(ff);
			System.out.println(" --- Mis items");
			for (Item ii: ff.getItems()){
				System.out.println(ii);
			}
		}
		System.out.println("\nItems sueltos");
		for (Item ii: Item.getAll()){
			System.out.println(ii);
		}
		
		
		/*for (TipoFactura t: TipoFactura.getAll()){
			System.out.println(t.toString());
			if (null == t.getNombre() || t.getNombre().equals("")){
				t.remove();
			}
		}
		manager.commit();
		System.out.println("dos");
		for (TipoFactura t: TipoFactura.getAll()){
			System.out.println(t.toString());
		}*/ //works
		
		//Creador
		/*
		TipoFactura tf = new TipoFactura();
		tf.setNombre("A");
		tf.save();
		
		Cliente c = new Cliente();
		c.setNombre("Fulanito");
		c.save();
		
		Factura f = new Factura();
		f.setNumero(30);
		f.setTipo(tf);
		f.save();
		
		Item i = new Item();
		i.setDescripcion("un item");
		i.save();
		
		f.getItems().add(i);
		
		
		Set<Factura> fs =	c.getFacturas();
		fs.add(f);
		
		//c.setFacturas(a); //probar si asi funciona bien teoricamente si porque el set ha de devolverse por referencia
		c.save();*/
		
	}
