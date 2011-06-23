package dbapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inversion {

    private Integer id= null;
		Integer idPlan;
    private Double monto=0d, porcentaje=0d;
    private Date fechaIni=new Date(0), fechaFin=new Date(0);
		private String nombre="";

		public String getNombre(){
				return nombre;
		}
		public void setNombre(String nombre){
				this.nombre = nombre;
		}

		public Date getFechaFin(){
				return fechaFin;
		}
		public void setFechaFin(Date fechaFin){
				this.fechaFin = fechaFin;
		}

		public Date getFechaIni(){
				return fechaIni;
		}
		public void setFechaIni(Date fechaIni){
				this.fechaIni = fechaIni;
		}

		public Double getPorcentaje(){
				return porcentaje;
		}
		public void setPorcentaje(Double porcentaje){
				this.porcentaje = porcentaje;
		}

		public Double getMonto(){
				return monto;
		}
		public void  setMonto(Double monto){
				this.monto = monto;
		}

		public Double resultForYear(Integer year){
			Double res = 0.0d;
			Date ini = new Date(year-1900, 0, 1);

			//Notar que usamos el java.sql.Date y no java.util.date porque el susodicho hace cualquier cosa
			//al momento de calcular los tiempos
			//porque lo del 1900 no me sorprende ? ¬_¬
			/*
			 * forma vieja
			 ini.setYear(year -1900);
			ini.setDate(1);
			ini.setMonth(0);
			ini.setHours(0);
			ini.setMinutes(0);
			ini.setSeconds(0);*/
			Long msyearini = ini.getTime();
			
			ini.setMonth(11);//notar el 11 y no 12
			ini.setDate(31);
			Long msyearfin = ini.getTime();

			//convierto todo a milisegundo para manejarlo mejor
			Long msini = fechaIni.getTime();
			Long msfin = fechaFin.getTime();
			
			//Me fijo que la inversion esté dentro del periodo
			if ((msini<msyearfin) && (msfin>msyearini)){

				Long days = 0L;

				//determinamos la cantidad de tiempo efectivo
				//dentro del periodo
				if (msini < msyearini){
					//si empezó antes, tomo como que empieza desde el inicio del periodo
					msini = msyearini;
				}
				
				if (msfin>msyearfin){
					//Si terminó después tomo el fin efectivo al fin del periodo
					msfin = msyearfin;
				}

				days = (msfin-msini)/1000L;//Segundos
				days /= 60;//minutos
				days /= 60;//horas
				days /= 24;//dias :D

				Integer meses = days.intValue() / 30;
				res = monto * (porcentaje/12d) * meses.doubleValue();
			}
			return res;
		}
		public static ArrayList<Inversion> invsForPlanId(Integer pid){
			ArrayList<Inversion> res = new ArrayList<Inversion>();
			ArrayList<Integer> ids = new ArrayList<Integer>();

			try {
				PreparedStatement ps = SQLManager.getSt("SELECT * FROM INVERSION WHERE id_plan = ?;");
				ps.setInt(1, pid);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					ids.add(rs.getInt(1));
				}
				for (Integer i : ids){
					res.add(new Inversion(i));
				}

			} catch (SQLException ex) {
				Logger.getLogger(Inversion.class.getName()).log(Level.SEVERE, null, ex);
			}

			return res;
		}
		public Inversion(){}
		public Inversion(Integer pid){
			try {
				PreparedStatement ps = SQLManager.getSt(
								"Select id, monto, porcentaje, inicio, fin, nombre, id_plan from inversion where id = ?"
				);
				ps.setInt(1, pid);
				ResultSet rs = ps.executeQuery();
				
				id = pid;
				if (rs.next()) {
					id = rs.getInt(1);
					monto = rs.getDouble(2);
					porcentaje = rs.getDouble(3);
					fechaIni = rs.getDate(4);
					fechaFin = rs.getDate(5);
					nombre = rs.getString(6);
					idPlan = rs.getInt(7);
				}
			} catch (SQLException ex) {
				Logger.getLogger(Inversion.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		public void save(){
			try {
				PreparedStatement ps = SQLManager.getSt(
					"UPDATE OR INSERT INTO Inversion(id, monto, porcentaje, inicio, fin, nombre, id_plan)" +
					"VALUES( ?, ?, ?, ?, ?, ?, ?) MATCHING (id) RETURNING id;");
				if (id == null){
					ps.setNull(1,0);
				}else{
					ps.setInt(1, id);
				}
				ps.setDouble(2, monto);
				ps.setDouble(3, porcentaje);
				ps.setDate(4, 
								new java.sql.Date(fechaIni.getTime()));
				ps.setDate(5, 
								new java.sql.Date(fechaFin.getTime()));
				ps.setString(6, nombre);
				ps.setInt(7, idPlan);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			} catch (SQLException ex) {
				Logger.getLogger(Inversion.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		public void delete(){
			try {
				if (id==null){return;}//por las dudas
				PreparedStatement ps = SQLManager.getSt("DELETE FROM Inversion WHERE id = ?;");
				ps.setInt(1, id);
				ps.execute();
				id = null;
			} catch (SQLException ex) {
				Logger.getLogger(Inversion.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		@Override
		public String toString(){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			NumberFormat nf = NumberFormat.getPercentInstance();
			String sPercent = nf.format(porcentaje);
			return String.format(
					"%s : $%s (%s) [%s - %s]",
					nombre, monto, sPercent, sdf.format(fechaIni), sdf.format(fechaFin)
				);
		}
}