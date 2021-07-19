package com.elevator.restapi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ElevatorRepository {
	Connection con = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "SYSTEM";
	String password = "oracle";
	
	public ElevatorRepository() {		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, username , password);
		}
		catch(Exception e1) {
			System.out.println(e1);
		}
	}
	public List<Elevator> getElevatorDetails() {
		List<Elevator> elevators = new ArrayList<>();
		String sql = "select * from Elevator";
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);	
		while(rs.next()) {
			Elevator e = new Elevator();
			e.setId(rs.getInt(1));
			e.setCapacity(rs.getInt(2));
			e.setCompany(rs.getString(3));
			e.setBuilding(rs.getString(4));
			e.setFloors(rs.getInt(5));
			e.setStatus(rs.getString(6));
			
			elevators.add(e);
			}
		}
		catch(Exception e1) {
			System.out.println(e1);
		}
		return elevators;
	}
		
	
	public Elevator getElevator(int id) {
		String sql = "select * from Elevator where id="+id;
		Elevator e = new Elevator();
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next()) {			
			e.setId(rs.getInt(1));
			e.setCapacity(rs.getInt(2));
			e.setCompany(rs.getString(3));
			e.setBuilding(rs.getString(4));
			e.setFloors(rs.getInt(5));
			e.setStatus(rs.getString(6));
			
			}
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		return e;	
	}

	public void create(Elevator e1) {
		String sql = "insert into Elevator (ID,COMPANY,BUILDING,FLOORS,STATUS,CAPACITY) values(?,?,?,?,?,?)";
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, e1.getId());
		st.setString(2, e1.getCompany());
		st.setString(3, e1.getBuilding());
		st.setInt(4, e1.getFloors());
		st.setString(5, e1.getStatus());
		st.setInt(6, e1.getCapacity());
		st.executeUpdate();	
		}
		catch(Exception e11) {
			System.out.println(e11);
		}
	}
	
	public void update(Elevator e1) {
		String sql = "update Elevator set ID=?,COMPANY=?,BUILDING=?,FLOORS=?,STATUS=?,CAPACITY=?";
		try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, e1.getId());
		st.setString(2, e1.getCompany());
		st.setString(3, e1.getBuilding());
		st.setInt(4, e1.getFloors());
		st.setString(5, e1.getStatus());
		st.setInt(6, e1.getCapacity());
		st.executeUpdate();	
		}
		catch(Exception e11) {
			System.out.println(e11);
		}
	}
}


