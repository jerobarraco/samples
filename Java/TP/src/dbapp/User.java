package dbapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class User {
	private Integer id = null;
	private Integer isAdmin=0;
	private String apellido="";
	private String nombre="";
	private String user="";
	private String password="";
	private Integer dni=0;
	private ArrayList<Plan> planes = new ArrayList<Plan>();

	public Plan getPlan(Integer indice){
			return planes.get(indice);
	}
	public Integer getCantPlanes(){
			return planes.size();
	}
	public void setDni(Integer dni){
			this.dni = dni;
	}
	public Integer getDni(){
			return dni;
	}
	public void setPassword(String password){
			this.password = password;
	}
	public String getPassword(){
			return password;
	}
	public void setUser(String user){
			this.user = user;
	}
	public String getUser(){
			return user;
	}
	public void setNombre(String nombre){
			this.nombre = nombre;
	}
	public String getNombre(){
			return nombre;
	}
	public void setApellido(String apellido){
			this.apellido = apellido;
	}
	public String getApellido(){
			return apellido;
	}
	public void setAdmin(boolean B){
			if(B){
					isAdmin = 1;
			}else{
					isAdmin = 0;
			}
	}
	public boolean getAdmin(){
			if(isAdmin == 0){
					return false;
			}else{
					return true;
			}
	}

	private void set(ResultSet rs){
		try {
			id = null;//si algo falla el id queda en null.
			if (rs.next()) {
				user = rs.getString(1);
				password = rs.getString(2);
				nombre = rs.getString(3);
				apellido = rs.getString(4);
				dni = rs.getInt(5);
				id = rs.getInt(6);
				isAdmin = rs.getInt(7);
				planes = Plan.plansForUserId(id);
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public User(){}
	public User(String name, String pwd){
		try {
			PreparedStatement ps = SQLManager.getSt(
							"SELECT username, pwd, nombre, apellido, dni, id, isAdmin "
							+ "FROM usuario WHERE (username = ?) and (pwd = ?);");
			ps.setString(1, name);
			ps.setString(2, pwd);
			set(ps.executeQuery());
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public User(int pid){
		try {			
			PreparedStatement ps = SQLManager.getSt("Select user, pwd, nombre, apellido, dni, id, isAdmin from usuario where id = ?");
			ps.setInt(1, pid);
			set(ps.executeQuery());
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void delete(){
		try {
			if (id==null){return;}//por las dudas
			for (Plan p : planes) {
				p.delete(); 
			}
			PreparedStatement ps = SQLManager.getSt("DELETE FROM usuario WHERE id = ?");
			ps.setInt(1, id);
			ps.execute();
			id = null;
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public void save(){
		try {
			PreparedStatement ps = SQLManager.getSt(
				"UPDATE OR INSERT INTO USUARIO (username, pwd, nombre, apellido, dni, id)"
				+ "VALUES( ?, ?, ?, ?, ?, ?) MATCHING (id) RETURNING id;"
			);
			
			ps.setString(1, user);
			ps.setString(2, password);
			ps.setString(3, nombre);
			ps.setString(4, apellido);
			ps.setInt(5, dni);
			if (id==null){
				ps.setNull(6, 0);
			}else{
				ps.setInt(6, id);
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			for (Plan p:planes){
				p.save();
			}
		} catch (SQLException ex) {
			Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	public Plan newPlan(){
		if (this.id == null) {save();}
		Plan p  = new Plan();
		p.idUsuario = id;
		planes.add(p);
		return p;
	}
	public void delPlan(Integer index){
			Plan p = planes.get(index);
			planes.remove(p);
			p.delete();
	}
	public Double getTotal(){
		Double r = 0.0d;
		for (Plan p: planes){
			r+=p.getTotal();
		}
		return r;
	}
}
