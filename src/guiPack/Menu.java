package guiPack;

import java.util.ArrayList;

public class Menu {
	int rid;
	ArrayList<String> foodType;
	ArrayList<Integer> fid;
	ArrayList<String> fName;
	ArrayList<Integer> review;
	ArrayList<String> description;
	ArrayList<Integer> price;
	public Menu(int rid, ArrayList<String> foodType, ArrayList<Integer> fid, ArrayList<String> fName,
			ArrayList<Integer> review, ArrayList<String> description, ArrayList<Integer> price) {
		super();
		this.rid = rid;
		this.foodType = foodType;
		this.fid = fid;
		this.fName = fName;
		this.review = review;
		this.description = description;
		this.price = price;
	}
	
	
	
	
}
