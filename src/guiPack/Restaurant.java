package guiPack;

public class Restaurant {
	int rid;
	String rName;
	int aid;
	String address;
	int review;
	
	@Override
	public String toString() {
		return rName + "";
	}

	public Restaurant(int rid, String rName, int aid, String address, int review) {
		super();
		this.rid = rid;
		this.rName = rName;
		this.aid = aid;
		this.address = address;
		this.review = review;
	}
	
	

}
