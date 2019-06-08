package it.polito.tdp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.model.Distretto;
import it.polito.tdp.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Distretto> tuttiDistretti(){
		String sql = "SELECT DISTINCT district_id FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Distretto> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Distretto(res.getInt("district_id")));
							
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Event> eventiperAnnoDistretti(int anno,int id){
			String sql = "SELECT * FROM EVENTS WHERE YEAR(reported_date)=? AND district_id=? " ;
			try {
				Connection conn = DBConnect.getConnection() ;

				PreparedStatement st = conn.prepareStatement(sql) ;
				st.setInt(1, anno);
				st.setInt(2, id);
				
				List<Event> list = new ArrayList<>() ;
				
				ResultSet res = st.executeQuery() ;
				
				while(res.next()) {
					try {
						list.add(new Event(res.getLong("incident_id"),
								res.getInt("offense_code"),
								res.getInt("offense_code_extension"), 
								res.getString("offense_type_id"), 
								res.getString("offense_category_id"),
								res.getTimestamp("reported_date").toLocalDateTime(),
								res.getString("incident_address"),
								res.getDouble("geo_lon"),
								res.getDouble("geo_lat"),
								res.getInt("district_id"),
								res.getInt("precinct_id"), 
								res.getString("neighborhood_id"),
								res.getInt("is_crime"),
								res.getInt("is_traffic")));
					} catch (Throwable t) {
						t.printStackTrace();
						System.out.println(res.getInt("id"));
					}
				}
				
				conn.close();
				return list ;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
		}
	
	public List<Integer> anniDisponibili(){
		String sql = "SELECT DISTINCT YEAR(reported_date) AS y FROM EVENTS " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Integer> anni=new LinkedList<Integer>();
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					anni.add(res.getInt("y"));
							
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			Collections.sort(anni);
			return anni;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}


}
