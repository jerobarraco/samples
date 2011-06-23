package dbapp;
import java.sql.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plan {
		private Integer id=null;
		Integer idUsuario;
		private Integer	anio=0;
    private String nombre="", descripcion="";
    private boolean realizable=false; //No esta en la consigna.
    private ArrayList<Inversion> inversiones = new ArrayList<Inversion>();

		public Integer getCanInversion(){
				return inversiones.size();
		}
		public Inversion getInversion(Integer index){
				return inversiones.get(index);
		}

		public void setDescripcion(String descripcion){
				this.descripcion = descripcion;
		}
		public String getDescripcion(){
				return descripcion;
		}

		public void setNombre(String nombre){
				this.nombre = nombre;
		}
		public String getNombre(){
				return nombre;
		}

		public void setanio(Integer anio){
				this.anio = anio;
		}
		public Integer getAnio(){
				return anio;
		}

		public static ArrayList<Plan> plansForUserId(Integer uid){
			ArrayList<Plan> res = new ArrayList<Plan>();
			try {
				PreparedStatement ps = SQLManager.getSt("SELECT id FROM \"PLAN\" WHERE id_usuario = ?;");
				ps.setInt(1, uid);
				ResultSet rs = ps.executeQuery();
				ArrayList<Integer> l = new ArrayList<Integer>();
				//Cargar un plan ejecuta un nuevo query,
				//Ejecutar un nuevo query cierra el resultset anterior ¬_¬#
				//asi que tenemos que cachear la info
				while (rs.next()) {
					l.add(rs.getInt(1));
				}
				for (Integer i: l){
					res.add (new Plan(i));
				}
				/* //yo preferia esto D:!
				while ((rs.next()) {
					res.add(new Plan(rs.getInt(1)));
				}*/
			} catch (SQLException ex) {
				Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
			}
			return res;
		}
		public Plan(){}
		public Plan(Integer pid){
			try {
				id = pid;
				PreparedStatement ps = SQLManager.getSt(
					"Select id, anio, nombre, descripcion, id_usuario, realizable from \"PLAN\" where id = ?"
				);
				ps.setInt(1, pid);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					id = rs.getInt(1);
					anio = rs.getInt(2);
					nombre = rs.getString(3);
					descripcion = rs.getString(4);
					idUsuario = rs.getInt(5);
					realizable = rs.getInt(6)==1;
					inversiones = Inversion.invsForPlanId(id);
				}
			} catch (SQLException ex) {
				Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		public void save(){
			try {
				PreparedStatement ps = SQLManager.getSt(
								"UPDATE OR INSERT INTO \"PLAN\" (id, anio, nombre, descripcion, id_usuario, realizable)"
								+ "VALUES( ?, ?, ?, ?, ?, ?) MATCHING (id) RETURNING id;"
					);
				if (id == null){
					ps.setNull(1, 0);
				}else{
					ps.setInt(1, id);
				}
				
				ps.setInt(2, anio);
				ps.setString(3, nombre);
				ps.setString(4, descripcion);
				ps.setInt(5, idUsuario);
				ps.setInt(6, realizable?1:0);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					id = rs.getInt(1);
				}
				for (Inversion i: inversiones){
					i.save();
				}
			} catch (SQLException ex) {
				Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		public void delete(){
			try {
				if (id==null){return;}//por las dudas
				for (Inversion i : inversiones) {
					i.delete();
				}
				PreparedStatement ps = SQLManager.getSt("DELETE FROM \"PLAN\" WHERE id = ?;");
				ps.setInt(1, id);
				ps.execute();
				id = null;
			} catch (SQLException ex) {
				Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		public Inversion newInversion(){
			//La posta para esta funcion y la de abajo sería crear un descendiente de ArrayList
			//con Add y Remove sobreescritos pero no nos da el tiempo :P
			if (id == null){save();}
			Inversion i = new Inversion();
			i.idPlan = id;
			inversiones.add(i);
			return i;
		}
		public void delInversion(Integer index){
				Inversion i = inversiones.get(index);
				inversiones.remove(i);
				i.delete();
		}
		public Double getTotal(){
			Double res = 0d;
			for (Inversion i:inversiones){
				res += i.resultForYear(anio);
			}
			return res;
		}
		@Override
		public String toString(){
			//String strReal = realizable ? "Realizable": "No Realizable";
			//no esta dentro de la consigna
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			String total = nf.format(this.getTotal());
			return String.format(
					"%s (%s) %s : '%s'",
					nombre, anio, total, descripcion);
		}
}
