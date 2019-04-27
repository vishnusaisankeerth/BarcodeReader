package org.iiitb.BarcodeReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.iiitb.model.register;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import org.apache.commons.net.ntp.NTPUDPClient; 
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;



public class DatabaseConnection {
	Statement statement;
	ResultSet resultSet;
	ResultSet resultSet1;
	Connection connection = null;
	String query = null;
	int fTime;
	String currDate;
	
	public void setfTime(String time)
	{
		this.fTime = Integer.parseInt(time);
	}
	public int getTime() {
		return fTime;
	}
	public void setDate(String date) {
		this.currDate = date;
	}
	public String getDate() {
		return currDate;
	}
	
	// Constructor for opening the Database Connection

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Found");
		}

		catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found: " + e);
		}

		String url = "jdbc:mysql://db:3306/student_info?verifyServerCertificate=false&useSSL=true";
		System.out.println(url);
		String user = "root";
		String password = "admin";
		connection = null;

		try {
			connection = (Connection) DriverManager.getConnection(url, user, password);
			System.out.println("Successfully Connected to Database");
		} catch (SQLException e) {
			System.out.println("SQL Exception: " + e);
		}

	}
	
	public ArrayList<register> dashBoard(){
		//String currDate = getDate();
		ArrayList<register> list = new ArrayList<register>();
		java.sql.PreparedStatement preparedStatement = null;		
		
		try {
			query = "select student_id, currDate, breakfast, lunch, dinner from messRegister where currDate=CURDATE();";
			preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, currDate);
			register register_object = new register();
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
					String bf="",lunch="",dinner="";
					if(rs.getInt("breakfast") == 1)
					{	
						bf="Yes"; 
					}
					else if(rs.getInt("breakfast") == 0)
					{
						bf="No";
					}
					if(rs.getInt("lunch") == 1)
					{	
						lunch="Yes"; 
					}
					else if(rs.getInt("lunch") == 0)
					{
						lunch="No";
					}
					if(rs.getInt("dinner") == 1)
					{	
						dinner="Yes"; 
					}
					else if(rs.getInt("dinner") == 0)
					{
						dinner="No";
					}
					String date = rs.getString("currDate");
					String[] x = date.split("-");
					String yyyy = x[0];
					String mm = x[1];
					String dd = x[2];
					date = dd + "-" +mm+"-"+yyyy;
					register_object.setbf(bf);
					register_object.setlunch(lunch);
					register_object.setdinner(dinner);
					register_object.setDate(date);
					register_object.setRollNum(rs.getString("student_id"));
					
					list.add(register_object);
					register_object = new register();
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
	public register get_student_date_status(String rollnumber,String currDate) {
		java.sql.PreparedStatement preparedStatement = null;		
		register register_object = new register();

		try {
			query = "select student_id, SUM(breakfast) as bf,SUM(lunch) as lunch,SUM(dinner) as dinner,currDate from messRegister where student_id=? and currDate=?;";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, rollnumber);
			preparedStatement.setString(2, currDate);
			ResultSet rs = preparedStatement.executeQuery();
//			for(int i=0;i<5;i++) {
			if(rs.next()) {
					String bf="",lunch="",dinner="";
					if(rs.getInt("bf") == 1)
					{	
						bf="Yes"; 
					}
					else if(rs.getInt("bf") == 0)
					{
						bf="No";
					}
					if(rs.getInt("lunch") == 1)
					{	
						lunch="Yes"; 
					}
					else if(rs.getInt("lunch") == 0)
					{
						lunch="No";
					}
					if(rs.getInt("dinner") == 1)
					{	
						dinner="Yes"; 
					}
					else if(rs.getInt("dinner") == 0)
					{
						dinner="No";
					}
					String date = rs.getString("currDate");
					String[] x = date.split("-");
					String yyyy = x[0];
					String mm = x[1];
					String dd = x[2];
					date = dd + "-" +mm+"-"+yyyy;
					register_object.setbf(bf);
					register_object.setlunch(lunch);
					register_object.setdinner(dinner);
					register_object.setDate(date);
					register_object.setRollNum(rs.getString("student_id"));
			}

			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return register_object;
	}
	public ArrayList<register> get_Top5_Items(String rollnumber) {
		ArrayList<register> list = new ArrayList<register>();
		java.sql.PreparedStatement preparedStatement = null;		
		
		try {
			query = "select student_id, SUM(breakfast) as bf,SUM(lunch) as lunch,SUM(dinner) as dinner,currDate from messRegister where student_id=? group by currDate;";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, rollnumber);
			register register_object = new register();
			ResultSet rs = preparedStatement.executeQuery();
//			for(int i=0;i<5;i++) {
			while(rs.next()) {
					String bf="",lunch="",dinner="";
					if(rs.getInt("bf") == 1)
					{	
						bf="Yes"; 
					}
					else if(rs.getInt("bf") == 0)
					{
						bf="No";
					}
					if(rs.getInt("lunch") == 1)
					{	
						lunch="Yes"; 
					}
					else if(rs.getInt("lunch") == 0)
					{
						lunch="No";
					}
					if(rs.getInt("dinner") == 1)
					{	
						dinner="Yes"; 
					}
					else if(rs.getInt("dinner") == 0)
					{
						dinner="No";
					}
					String date = rs.getString("currDate");
					String[] x = date.split("-");
					String yyyy = x[0];
					String mm = x[1];
					String dd = x[2];
					date = dd + "-" +mm+"-"+yyyy;
					register_object.setbf(bf);
					register_object.setlunch(lunch);
					register_object.setdinner(dinner);
					register_object.setDate(date);
					register_object.setRollNum(rs.getString("student_id"));
					
					list.add(register_object);
					register_object = new register();

//				}
			}

			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;
	}
	
	
	public JSONObject userInfo(String rollnumber) throws JSONException {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();
		try {
			query = "select studentName,studentId from studentDetails where studentId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, rollnumber);
		
			resultSet = preparedStatement.executeQuery();  
			System.out.println("Database CConnection");
			if(resultSet.next()){
				
				user.put("name", resultSet.getString("studentName"));
				user.put("student_id", resultSet.getString("studentId"));
				user.put("result", "success");
			}
			else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		//System.out.println(user);
		return user;

	}
	public String userDetails(String rollnumber) throws JSONException {

		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();
		try {
			query = "select studentName,studentId from studentDetails where studentId=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, rollnumber);
		
			resultSet = preparedStatement.executeQuery();  
			System.out.println("Database CConnection"+rollnumber);
			
			if(resultSet.next()){
				addUser(resultSet.getString("studentId"), resultSet.getString("studentName"));
				int fTime = getTime();
				System.out.println(fTime);
				String mealType = getMeal(fTime);
				
				user.put("name", resultSet.getString("studentName"));
				user.put("student_id", resultSet.getString("studentId"));
				user.put("mealType", mealType);
				user.put("result", "success");
			}
			else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		//System.out.println(user);
		return user.toString();

	}
	
	public String refundDetails(String rollnumber) throws JSONException {
		System.out.println("Hello");
		java.sql.PreparedStatement preparedStatement = null;
		JSONObject user = new JSONObject();
		String grade="";
		JSONObject json = userInfo(rollnumber); 
		
		try {
			query = "select SUM(breakfast) as CountBF,SUM(lunch) as CountLunch,SUM(dinner) as CountDinner from messRegister where student_id=? order by currDate";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, rollnumber);
		
			resultSet = preparedStatement.executeQuery();  
			//System.out.println(resultSet);
			if(resultSet.next()){
				int refund = getRefund(resultSet.getInt("CountBF"),resultSet.getInt("CountLunch"),resultSet.getInt("CountDinner"));
				
				
				
				user.put("name", json.getString("name"));
				user.put("student_id", json.getString("student_id"));
				user.put("refund", refund);
				user.put("result", "success");
			}
			else {
				user.put("result", "fail");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user.toString());
		System.out.println(user);
		return user.toString();

	}
	public int getRefund(int bf,int lunch,int dinner)
	{
		System.out.println("Hello");
		java.sql.PreparedStatement preparedStatement = null;
		int bfcharge=0,lunchcharge=0,dinnercharge=0,semestercharge=0;
		try {
			query = "select breakfastcharge,lunchcharge,dinnercharge,semestercharge from messCharges";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				bfcharge=resultSet.getInt("breakfastcharge");
				lunchcharge = resultSet.getInt("lunchcharge");
				dinnercharge = resultSet.getInt("dinnercharge");
				semestercharge = resultSet.getInt("semestercharge");
			}
		} catch (SQLException e) {
			System.out.println("Hjhdbkhjabkjh catchs");
			e.printStackTrace();
		}
		System.out.println(bfcharge+lunchcharge+dinnercharge+semestercharge);
		int refund = semestercharge - ((bfcharge)*(bf) + (lunch)*(lunchcharge) + (dinnercharge)*(dinner));
		return refund;
		
	}
	public String getcharges() throws JSONException
	{
		System.out.println("Hello");
		java.sql.PreparedStatement preparedStatement = null;
		int bfcharge=0,lunchcharge=0,dinnercharge=0,semestercharge=0;
		JSONObject json = new JSONObject();
		try {
			query = "select breakfastcharge,lunchcharge,dinnercharge,semestercharge from messCharges";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				bfcharge=resultSet.getInt("breakfastcharge");
				lunchcharge = resultSet.getInt("lunchcharge");
				dinnercharge = resultSet.getInt("dinnercharge");
				semestercharge = resultSet.getInt("semestercharge");
				json.put("bf", bfcharge);
				json.put("lunch", lunchcharge);
				json.put("dinner",dinnercharge);
				json.put("sem",semestercharge);
				
			}
		} catch (SQLException e) {
			System.out.println("Hjhdbkhjabkjh catchs");
			e.printStackTrace();
		}
		return json.toString();
		
	}
	
	
	public String getMeal(int fTime) {
		String meal = "";
		if(fTime >= 700 && fTime <= 1130) {
			meal = "breakfast";
		}
		else if(fTime >= 1230 && fTime <= 1850) {
			meal = "lunch";
		}
		else if(fTime >= 1800 && fTime <= 2359) {
			meal = "dinner";
		}
		return meal;
	}
	
	public Boolean checkDupMeal(String rollnum) {
		boolean checkDup = false;
		String currDate = getDate();
		String meal = getMeal(getTime());
		java.sql.PreparedStatement preparedStatement = null;
		
		if(meal == "breakfast") {
			try {
				query = "select student_id, name, breakfast from messRegister where student_id=? AND breakfast=? AND currDate=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, rollnum);
				preparedStatement.setInt(2, 1);
				preparedStatement.setString(3, currDate);
				resultSet1 = preparedStatement.executeQuery();  

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if(meal == "lunch") {
			try {
				query = "select student_id, name, breakfast from messRegister where student_id=? AND lunch=? AND currDate=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, rollnum);
				preparedStatement.setInt(2, 1);
				preparedStatement.setString(3, currDate);
				resultSet1 = preparedStatement.executeQuery();  

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if(meal == "dinner") {
			try {
				query = "select student_id, name, breakfast from messRegister where student_id=? AND dinner=? AND currDate=?";
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, rollnum);
				preparedStatement.setInt(2, 1);
				preparedStatement.setString(3, currDate);
				resultSet1 = preparedStatement.executeQuery();  

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try { 
			if(resultSet1.next()){
				checkDup = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return checkDup;
	}
	
	public void addUser(String rollnum, String name) throws JSONException{
		System.out.println("this is harsha");
//		try {
//			getTime2();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		int bFast=0, lunch=0, dinner=0;
		//Getting current time to decide what meal the student is having 
		int fTime = getTime();
		System.out.println("hello container"+fTime+getDate());
		String meal = getMeal(fTime);
		System.out.println(rollnum+name);
		if(meal == "breakfast") {
			bFast = 1;
		}
		else if(meal == "lunch") {
			lunch = 1;
		}
		else if(meal == "dinner") {
			dinner = 1;
		}
		
		//Inserting the student into the mess register table
		if(!checkDupMeal(rollnum)) {
			java.sql.Statement stmt = null;
			try {
				stmt = connection.createStatement();
				String sql = "INSERT INTO messRegister(student_id,name,currDate,currTime,breakfast,lunch,dinner) " + "VALUES ('"+rollnum+"','"+name+"',CURDATE(),CURTIME(),'"+bFast+"','"+lunch+"','"+dinner+"')";
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("User already took his meal");
		}
	}
	public boolean UpdateCharges(int b,int l,int d,int s)
	{
		System.out.println(b+l+d+s);
		java.sql.PreparedStatement preparedStatement = null;
		try {
			query = "update messCharges set breakfastcharge=?,lunchcharge=?,dinnercharge=?,semestercharge=? where id=1";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, b);
			preparedStatement.setInt(2, l);
			preparedStatement.setInt(3, d);
			preparedStatement.setInt(4, s);
			int rs = preparedStatement.executeUpdate();
			  if(rs ==0) return false;
			  else return true;

			 } catch (SQLException e) {
			  System.out.println(e.getMessage());
			 }
			 //return item.toString();
			 return false;
	}
	
	
}
