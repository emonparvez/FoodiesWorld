package jdbcPack;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import guiPack.Area;
import guiPack.Restaurant;
import guiPack.Users;

public class DataProvider {

	public static String getPassword(String UID) throws Exception {
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from Users where UID ="+UID;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		
		return rs.getString("Pass");
	}
	
	public static Users getUsers(String UID) throws Exception{
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from Users where UID ="+UID;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		
		return new Users(rs.getInt("UID"),rs.getString("UName"),rs.getString("Pass"),rs.getInt("PhoneNo"),rs.getString("Address"),rs.getInt("TotalOrder"),rs.getInt("Point"));
		
	}

	public static ArrayList<Area> getAreaList() throws Exception {
		
		ArrayList<Area> areaList=new ArrayList<Area>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from Area";
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			areaList.add(new Area(rs.getInt("AID"),rs.getString("areaName")) );
		}
		
		return areaList ;
	}

	public static void setUsers(String uName, String pass, int phoneNo,String address) throws Exception {
		Statement stmt = StatementProvider.getStatement();
		String query = "insert into users(UName,Pass,PhoneNo,Address,TotalOrder,Point) values('"+uName+"','"+pass+"',"+phoneNo+",'"+address+"',0,5)";
		
		stmt.executeUpdate(query);	
		System.out.println("User inserted into database...");
	}

	public static ArrayList<Restaurant> getRestaurantList(int aID) throws Exception {
		
		ArrayList<Restaurant> restaurantList=new ArrayList<Restaurant>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from Restaurant where aid="+aID;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			restaurantList.add(new Restaurant(rs.getInt("RID"),rs.getString("RName"),rs.getInt("AID"),rs.getString("Address"),rs.getInt("Review")) );
		}
		
		return restaurantList ;
	}

	public static ArrayList<String> getFoodType(int rid) throws Exception {
		
		ArrayList<String> s= new ArrayList<String>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from menu where rid="+rid;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(rs.getString("FoodType"));
		}
		
		return s;
	}

	public static ArrayList<Integer> getFid(int rid) throws Exception {
		
		ArrayList<Integer> s= new ArrayList<Integer>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from menu where rid="+rid;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(Integer.parseInt(rs.getString("FID")));
		}
		
		return s;
	}

	public static ArrayList<String> getFname(int rid) throws Exception {
		ArrayList<String> s= new ArrayList<String>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from menu where rid="+rid;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(rs.getString("FName"));
		}
		
		return s;
	}

	public static ArrayList<Integer> getReview(int rid) throws Exception {
		ArrayList<Integer> s= new ArrayList<Integer>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from menu where rid="+rid;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(Integer.parseInt(rs.getString("Review")));
		}
		return s;
	}

	public static ArrayList<String> getDescription(int rid) throws Exception {
		
		ArrayList<String> s= new ArrayList<String>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from menu where rid="+rid;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(rs.getString("Description"));
		}
		
		return s;
	}

	public static ArrayList<Integer> getPrice(int rid) throws Exception {
		ArrayList<Integer> s= new ArrayList<Integer>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from menu where rid="+rid;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(Integer.parseInt(rs.getString("Price")));
		}
		return s;
	}

	public static void setOrder(int uID, int rid, Integer fid, int amount, int discount, int money) throws Exception {
		
		Statement stmt = StatementProvider.getStatement();
		String query = "insert into orders(UID,RID,FID,Amount,Discount,Money) values("+uID+","+rid+","+fid.intValue()+","+amount+","+discount+","+money+")";
		
		stmt.executeUpdate(query);
		System.out.println("Order inserted into database...");
	}

	public static void setUsersPoint(int uID) throws Exception {
		Statement stmt = StatementProvider.getStatement();
		String query = "update users set Point = Point+5 where UID="+uID;
		
		stmt.executeUpdate(query);
		
	}

	public static ArrayList<String> getDonatorsName() throws Exception {
		
		ArrayList<String> s= new ArrayList<String>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from Donators";
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(rs.getString("DName"));
		}
		return s;
	}

	public static ArrayList<String> getDonatorsAddress() throws Exception {
		ArrayList<String> s= new ArrayList<String>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from Donators";
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(rs.getString("Address"));
		}
		return s;
	}

	public static ArrayList<Integer> getDonatorsPhone() throws Exception {
		
		ArrayList<Integer> s= new ArrayList<Integer>();
		
		Statement stmt = StatementProvider.getStatement();
		String query = "select * from donators";
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			s.add(Integer.parseInt(rs.getString("PhoneNo")));
		}
		return s;
	}

	public static void setVolunteerApply(String text) throws Exception {
		Statement stmt = StatementProvider.getStatement();
		String query = "insert into applyVolunterr(Application) values('"+text+"');";
		
		stmt.executeUpdate(query);
		System.out.println("Successfully applied for volunteer");
		System.out.println("Volunteer table updated");
		
	}

	public static void setReview(Integer rate, int fid) throws Exception {	
		// (oldReview * OldCount)+rate /newCount
		// Get oldReview
		Statement stmt1 = StatementProvider.getStatement();
		String query1 = "select * from menu where fid = "+fid;
		
		ResultSet rs1 = stmt1.executeQuery(query1);
		rs1.next();
		int oldReview = Integer.parseInt(rs1.getString("review"));
		System.out.println("Old Review: "+oldReview);
		
		
		// Get oldCount
		ResultSet rs2 = stmt1.executeQuery(query1);
		rs2.next();
		int oldCount = Integer.parseInt(rs2.getString("count"));
		//System.out.println(oldCount);
		
		
		// Update count
		Statement stmt = StatementProvider.getStatement();
		String query = "update menu set count = count +1 where fid="+fid;
		stmt.executeUpdate(query);
		
		// Get New count
		Statement stmt4 = StatementProvider.getStatement();
		ResultSet rs3 = stmt4.executeQuery(query1);
		rs3.next();
		int newCount = Integer.parseInt(rs3.getString("count"));
		//System.out.println(newCount);
		
		
		// setNewReview
		int newReview = ((oldReview*oldCount)+rate)/newCount;
		System.out.println("New Review: "+newReview);
		
		Statement stmt2 = StatementProvider.getStatement();
		String query2 = "update menu set review = "+newReview+" where fid="+fid;
		stmt2.executeUpdate(query2);
		System.out.println("Successfully rated...");
		
	}

	public static void setUsersTotalOrder(int uID) throws Exception {
		Statement stm = StatementProvider.getStatement();
		String query = "update users set TotalOrder = TotalOrder+1 where UID="+uID;
		
		stm.executeUpdate(query);
		
	}

	public static String getUserID(String name) throws Exception {
		Statement stm = StatementProvider.getStatement();
		String query = "select * from users where uname = '"+name+"'";
		
		ResultSet rs = stm.executeQuery(query);
		rs.next();
		String s= rs.getString("UID");
		return s;
	}
	

}
