package application;

public class TCCustomers {

	String cname,mob,adr,city,dos;

	public TCCustomers(String cname, String mob, String adr, String city, String dos) {
		super();
		this.cname = cname;
		this.mob = mob;
		this.adr = adr;
		this.city = city;
		this.dos = dos;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDos() {
		return dos;
	}

	public void setDos(String dos) {
		this.dos = dos;
	}
	
	
}
