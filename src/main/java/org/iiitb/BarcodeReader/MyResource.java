package org.iiitb.BarcodeReader;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.POST;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.iiitb.BarcodeReader.DatabaseConnection;

import org.iiitb.model.register;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	private static final String SUCCESS_RESULT="Success";
	private static final String FAILURE_RESULT="Failure";	

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@Path("userInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String userInfo(String request) throws Exception{
      
    	JSONObject data = new JSONObject(request);
    	String roll_no = data.getString("roll_no");
    	String fTime = data.getString("fTime");
    	String date = data.getString("date");
    	
    	DatabaseConnection dc = new DatabaseConnection();
    	dc.setDate(date);
    	dc.setfTime(fTime);
    	return dc.userDetails(roll_no);
    }
	
	@Path("refundInfo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String refundInfo(String request) throws Exception{
      
	
    	JSONObject data = new JSONObject(request);
    	String roll_no = data.getString("roll_no");
    	
    	DatabaseConnection dc = new DatabaseConnection();
    	return dc.refundDetails(roll_no);
    }
	@Path("dashboard")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String dashboard() throws Exception{
		
		DatabaseConnection dc = new DatabaseConnection();
		ArrayList<register> registers = dc.dashBoard();
		
		JSONArray register_json_array = new JSONArray();
		for(register item : registers) {
			JSONObject register_json = new JSONObject();
			register_json.put("rollNum", item.getRollNum());
			register_json.put("date", item.getdate());
			register_json.put("bf", item.getbf());
			register_json.put("lunch", item.getlunch());
			register_json.put("dinner", item.getdinner());
			register_json_array.put(register_json);
		}
		JSONObject result = new JSONObject();
		
		result.put("items", register_json_array);
		System.out.print(register_json_array.toString());
		return register_json_array.toString();
    	
	}
	@Path("/history/{roll_no}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String history(@PathParam("roll_no") String roll_no) throws Exception{
		
		System.out.println(roll_no);
		DatabaseConnection dc = new DatabaseConnection();
		ArrayList<register> registers = dc.get_Top5_Items(roll_no);
		
		JSONArray register_json_array = new JSONArray();
		for(register item : registers) {
			JSONObject register_json = new JSONObject();
			register_json.put("rollNum", item.getRollNum());
			register_json.put("date", item.getdate());
			register_json.put("bf", item.getbf());
			register_json.put("lunch", item.getlunch());
			register_json.put("dinner", item.getdinner());
			register_json_array.put(register_json);
		}
		JSONObject result = new JSONObject();
		
		result.put("items", register_json_array);
		System.out.print(register_json_array.toString());
		return register_json_array.toString();
    	
	}
	@Path("charges")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String charges() throws Exception{
		DatabaseConnection dc = new DatabaseConnection();
		return  dc.getcharges();
	}
	
	@Path("updatecharges")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String update(String request) throws Exception{
		System.out.println("kfjggnf");
		System.out.println(request);
		JSONObject data = new JSONObject(request);
    	int bcharge= data.getInt("bfcharge");
    	int lcharge = data.getInt("lcharge");
    	int dcharge = data.getInt("dcharge");
    	int semcharge = data.getInt("semcharge");
    	System.out.println("ksjdnkjdn "+ bcharge);
    	
    	DatabaseConnection dc = new DatabaseConnection();
    	boolean result = dc.UpdateCharges(bcharge,lcharge,dcharge,semcharge);
    	return result ? "{ \"Response\" : \"" + SUCCESS_RESULT + "\" }" : "{ \"Response\" : \"" + FAILURE_RESULT + "\" }";
    		
    	
	}
}
