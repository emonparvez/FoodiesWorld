package guiPack;

public class Order {
	int oid;
	int uid;
	int rid;
	int fid;
	int amount;
	int discount;
	int money;
	
	public Order( int uid, int rid, int fid, int amount, int discount,int money) {
		super();
		
		this.uid = uid;
		this.rid = rid;
		this.fid = fid;
		this.amount = amount;
		this.discount = discount;
		this.money = money;
	}
	
	

}
