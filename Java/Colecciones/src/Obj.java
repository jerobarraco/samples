
public class Obj {
	private String os;
	private Integer oi;
	public Obj(String ps, Integer pi){
		os = ps;
		oi = pi;		
	}
	public String getOS(){return os;}
	public Integer getOI(){return oi;}
	public String toString(){
		return os + " " + oi;
	}
}
