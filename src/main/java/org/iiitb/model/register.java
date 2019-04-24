package org.iiitb.model;

public class register {
	private String date;
	public String bf,dinner,lunch, rollNo;
	public String getbf()
	{
		return bf;
	}
	public void setbf(String bf)
	{
		this.bf = bf;
	}
	public String getlunch()
	{
		return lunch;
	}
	public void setlunch(String lunch)
	{
		this.lunch = lunch;
	}
	public void setdinner(String dinner)
	{
		this.dinner = dinner;
	}
	public String getdinner()
	{
		return dinner;
	}
	public String getdate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public void setRollNum(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getRollNum(){
		return rollNo;
	}
}
