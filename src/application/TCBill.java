package application;

public class TCBill {

	String cname,sd,ed;
	Float tot;
	int status;
	
	public TCBill(String cname, String sd, String ed, Float tot, int status) {
		super();
		this.cname = cname;
		this.sd = sd;
		this.ed = ed;
		this.status = status;
		this.tot = tot;
	}

	public String getCname() {
		System.out.println(cname+"...........");
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getEd() {
		return ed;
	}

	public void setEd(String ed) {
		this.ed = ed;
	}

	public String getStatus() {
		if(status==1)
			return "Paid";
		else
			return "Not Paid";
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Float getTot() {
		return tot;
	}

	public void setTot(Float tot) {
		this.tot = tot;
	}
	
	
}
