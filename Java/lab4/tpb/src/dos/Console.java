package dos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import models.Articulo;
import models.Cliente;
import models.Direccion;
import models.DocumentoComercial;
import models.Empleado;
import models.FCompra;
import models.FVenta;
import models.Item;
import models.Proveedor;
import models.manager;
import javax.persistence.Query;

public class Console {
	private static Empleado e;
	private static Proveedor p;
	private static Cliente c;
	private static FCompra fc;
	private static FVenta fv;
	
	private static void crearPersonas(){
			e = manager.getById(Empleado.class,  50L);
			if (e == null){
				e = new Empleado();
				e.setCargo("HAZLOTODO");
				e.setNombre("John");
				Direccion d = new Direccion();
				d.setCalle("Falsa");
				d.setDepto("");
				d.setNumero(123);
				e.setDirPersonal(d);
				manager.save(e);
				manager.commit();
			}
			
			c = manager.getById(Cliente.class, 100L);
			if (c==null){
				c = new Cliente();
				c.setCuit("20314395779");
				c.setNombre("Cliente1");
				c.setNotas("asdlkasdlñkasldasl ñkd alñskdlñaskdlñask dlñkasñldkas ñdkasñkdñaskdñaskñdakñasdkasñdka");
				c.setNumero(11);
				c.setSitIva(Cliente.SituacionIva.RI);
			
				Direccion d = new Direccion();
				d.setCalle("OTra Calle falsa");
				d.setDepto("A");
				d.setNumero(23);
				c.setDirPersonal(d);
				manager.save(c);
				manager.commit();
			}
			
			p = manager.getById(Proveedor.class, 150L);
			if (p==null){
				p = new Proveedor();
				p.setCuit("202020202");
				p.setNombre("Proveedor1");
				p.setNotas("le ponemos un 3 como nota");
				p.setNumero(1);
				p.setRazonSocial("Juan Pepito");//en realidad deberia usar nombre, pero el uml decia asi
				p.setSitIva(Cliente.SituacionIva.EXENTO);
				
				Direccion d = new Direccion();
				d.setCalle("calle asdf");
				d.setDepto("");
				d.setNumero(42);
				p.setDirPersonal(d);
				
				manager.save(p);
				manager.commit();
			}
	}
	private static void crearArticulos(){
		Articulo a = manager.getById(Articulo.class , 50L);
			if (a==null){
				a = new Articulo();
				a.setNombre("Pan");
				a.setPvp(30.0);
				a.setExistencia(1);
				manager.save(a);
				manager.commit();
			}
		a = manager.getById(Articulo.class , 100L);
			if (a==null){
				a = new Articulo();
				a.setNombre("Leche");
				a.setPvp(4.30);
				a.setExistencia(10);
				manager.save(a);
				manager.commit();
			}
			 a = manager.getById(Articulo.class , 150L);
			if (a==null){
				a = new Articulo();
				a.setNombre("Manteca");
				a.setPvp(5.0);
				a.setExistencia(13);
				manager.save(a);
				manager.commit();
			}
	}
	private static void crearDocumentos(){
		//Para ver de que tipo es la factura
		//Hibernate se pasó al autoreconocer de que tipo es
		//System.out.println(p.getDocs().iterator().next() instanceof FCompra); //-> prints "true"
		fc = null;
		for (DocumentoComercial dc: p.getDocs()){
			if (dc instanceof FCompra ){
				fc = (FCompra) dc;
				break;
			}
		}
		if (fc==null){
				System.out.println("creando factura compra");
				fc = new FCompra();
				fc.setDestino(p);
				fc.setEnviar(true);
				fc.setLetra('A');
				fc.setNotas("Todo en orden");
				fc.setTotal(BigDecimal.valueOf(0xaaee538190L));
				p.getDocs().add(fc);//append, para no sobreescribir los que ya existan
				manager.save(p);
				manager.commit();	
		}
		
		
		fv = null;
		for (DocumentoComercial dc: c.getDocs()){
			if (dc instanceof FVenta ){
				fv = (FVenta) dc;
				break;
			}
		}
		if (fv==null){
				System.out.println("creando factura venta");
				fv = new FVenta();
				fv.setDestino(c);
				fv.setEnviar(false);
				fv.setLetra('B');
				fv.setNotas("nada");
				fv.setTotal(BigDecimal.valueOf(0xFF07CFL));
				c.getDocs().add(fv);//append, para no sobreescribir los que ya existan
				manager.save(c);
				manager.commit();	
		}
		setItems();
		//seteamos los items ... asi imprime bien.
		
	}
	private static void setItems(){		
		Set<Item> its = fc.getItems();
		if (its.isEmpty())	{
			Item i = new Item();
			i.setArticulo(manager.getById(Articulo.class, 50L));
			i.setCantidad(1);
			i.setDescripcion("un item");
			i.setPrecio(2.3);
			
			its.add(i);
			
			i = new Item();//reusar variables es de cochino :)
			i.setArticulo(manager.getById(Articulo.class, 150L));
			i.setCantidad(1);
			i.setDescripcion("otra cosa");
			i.setPrecio(20.0);
			
			its.add(i);
			manager.save(fc);//teoricamente its es por referencia, asi que deberia poder hacerse estos
			manager.commit();	
		}
		
		its = fv.getItems();
		if (its.isEmpty())	{
			Item i = new Item();
			i.setArticulo(manager.getById(Articulo.class, 150L));
			i.setCantidad(2);
			i.setDescripcion("Ponele que algo");
			i.setPrecio(30.0);
			
			its.add(i);
			
			i = new Item();//reusar variables es de cochino :)
			i.setArticulo(manager.getById(Articulo.class, 100L));
			i.setCantidad(4);
			i.setDescripcion("otro item mas");
			i.setPrecio(20.0);
			
			its.add(i);
			manager.save(fv);//teoricamente its es por referencia, asi que deberia poder hacerse estos
			manager.commit();	
		}
	}
	public static void main(String[] args) {
		if (args.length >0){
			Query q;
			
			switch (args[0]) {
				case "ej1":
					//where f.fecha  ='2011-10-24'
					//where f.fecha = ?
					//q.setdate(0 , new Date());
					//where f.fecha=:fechafactura
					//q.setdate("fechafactura", new Date())";
					q = manager.Query(FCompra.class, "from FCompra f");
					List<FCompra> lfc = q.getResultList();
					for(FCompra fci:lfc){
						System.out.println(fci);
					}
					break;
					
				case "ej2":
					q = manager.Query(FCompra.class , "from FCompra f where fecha=?");
					break;
					
				case "crear":
					crearPersonas();
					crearArticulos();
					crearDocumentos();
					System.out.println("Proveedor: "+ p);
					System.out.println("Cliente: "+ c);
					System.out.println("Empleado: " + e);
					System.out.print(fc);
					//probando bidireccionalidad
					System.out.println(" -> " + fc.getDestino().getNombre());
					System.out.print(fv);
					System.out.println(" -> " + fv.getDestino().getNombre());
					break;
			}
		}
	}
}
