/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Tpmet {

	/**
	 * @param args the command line arguments
	 */
	
	public static void main(String[] args) {
		// TODO code application logic here
		/*Categoria cat = new Categoria();
		cat.setNombre("estreno");
		cat.setBonificacion(1.3);
		manager.save(cat);
		 cat = new Categoria();
		cat.setNombre("media");
		cat.setBonificacion(1.0);
		manager.save(cat);
		 */
		Categoria cat = manager.nativeQueryList(Categoria.class, "Select * from CATEGORIAS").get(0);
		System.out.println(cat.nombre);
		
		/*
		Media m = new Media();
		m.nombre = "VHS";
		m.precio = 5.0d;
		manager.save(m);
		
		m = new Media();
		m.nombre = "DVD";
		m.precio = 8.0d;
		manager.save(m);
		
		m = new Media();
		m.nombre = "DVD - WII";
		m.precio = 5.0d;
		manager.save(m);
		
		m = new Media();
		m.nombre = "BluRay - PS3";
		m.precio = 10.0d;
		manager.save(m);*/
		Media m = manager.nativeQueryList(Media.class, "Select * from MEDIAS").get(0);
		System.out.println(m.nombre);
	
		/*Sucursal s = new Sucursal();
		s.nombre = "Gazano";
		s.direccion =" una direccion en ganzano nº 123";
		s.caja =0d;
		manager.save(s);*/
		Sucursal s = Sucursal.getAll().get(0);//manager.nativeQueryList(Sucursal.class, "Select * from SUCURSALES").get(0);
		System.out.println(s.nombre);

	
		/*Genero g = new Genero();
		g.setNombre("Acción");
		manager.save(g);*/
		
		Genero g = manager.nativeQueryList(Genero.class, "Select * from GENEROS").get(0);
		System.out.println(g.nombre);
		
		
		/*Producto p = new Producto();
		p.titulo = "Johnny Mnemonic";
		p.fecha = new java.sql.Date(1995, 04, 26);
		p.detalles ="Director: Robert Longo\nEscritor:William Gibson\nActores:Keanu Reeves\nDolph Lundgren\nDina Meyer";
		p.pais = "USA";
		p.genero = g;
		p.categoria = cat;
		manager.save(p);*/
		
		Producto p = manager.nativeQueryList(Producto.class, "Select * from Productos").get(0);
		System.out.println(p.titulo);
		System.out.println(p.detalles);
		System.out.println(p.fecha);
		System.out.println("Disponible:" + p.CantDisponibles());
		
		/*Copia c = new Copia();
		c.alta = new java.sql.Date(0);
		c.producto = p;
		c.sucursal = s;
		c.numero = 2301231L;
		c.tipo = m;
		manager.save(c);*/
		Copia c = manager.nativeQueryList(Copia.class, "Select * from Copias").get(0);
		System.out.println(c.numero);
		
		/*
		EstadoCliente ec = new EstadoCliente();
		ec.nombre = "Esporadico"; //Frecuente - Normal
		ec.bonificacion = 1.0;
		 manager.save(ec);
		*/
		EstadoCliente ec = manager.nativeQueryList(EstadoCliente.class, "Select * from ESTADOCLIENTES").get(0);
		System.out.println(ec.nombre);
		
		/*Cliente cli = new Cliente ();
		cli.nombre = "Jeronimo";
		cli.apellido = "Barraco";
		cli.celular = "0343-154xxxxxx";
		cli.codigo = 11L;
		cli.alta = new java.sql.Date(0);
		cli.direccion = "Avenida siempre viva 123";
		cli.dni = 31439577L;
		cli.fcDni = true;
		cli.fcServicio = true;
		cli.mail = "lol@nande.com.ar";
		cli.cargarFoto("c:\\foto.png");
		cli.estado = ec;
		manager.save(cli);*/
		Cliente cli = manager.nativeQueryList(Cliente.class, "Select * from Clientes").get(0);
		
		System.out.println(cli.nombre + " " + cli.apellido);
		System.out.println("Ha alquilado: "+ String.valueOf(cli.alquileres.size()));
		
		//Double costo = c.CalcPrecio(cli);
		//same as 
		Double costo = c.Alquilar(cli);
		System.out.println("Cuesta "+ String.valueOf(costo));
		//s.caja += costo;
		manager.save(s);

	}
}
