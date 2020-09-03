package postgresqltutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RetrieveInfo {
	String password;
	private Connection connection;
	
	public RetrieveInfo(String password) {	
		this.password=password;
		
		connect();
	}
	
	public void connect() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:postgresql://pgserver.mah.se/projectchipahm","ah7947",password);
			
			System.out.println("Connection is established.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public ArrayList<String> getBands() {
		
		ArrayList<String> list = new ArrayList<String>();
		try (PreparedStatement pstatement = connection.prepareStatement("SELECT bandid,bandname,country FROM band")){
			
			ResultSet getResult = pstatement.executeQuery();
			
			while(getResult.next()) {
				list.add(getResult.getInt(1) + "," + getResult.getString(2) + "," + getResult.getString(3));
			}
			return list;
			
		} catch (SQLException e) {
			
		}

		return null;
	}
	
	public ArrayList<String> getMembers(int bandid) {
		
		ArrayList<String> list = new ArrayList<String>();
		   try (PreparedStatement pstatement = connection.prepareStatement("SELECT DISTINCT name FROM partof, member WHERE partof.memberid = member.memberid AND partof.bandid=?;")){
			pstatement.setInt(1, bandid);
			ResultSet getRes = pstatement.executeQuery();
			
			while(getRes.next()) {
				list.add(getRes.getString(1));
			}
			System.out.println("List: " + list.toString());
			return list;
			
		} catch (SQLException e) {
			
		}

		return null;
	}
	
	public ArrayList<String> getSchedule(String name) {
		
		ArrayList<String> list = new ArrayList<String>();
			
		try(PreparedStatement pstatement = connection.prepareStatement("SELECT bandname, time, event, day from schedule, band WHERE schedule.bandid = band.bandid AND day = ?;")){
			pstatement.setString(1, name);
			ResultSet getRes = pstatement.executeQuery();
			
			while(getRes.next()) {
				list.add(" Bandets namn: " + getRes.getString(1) + ", tid: " + getRes.getString(2) + ", event: " + getRes.getString(3) +
						", dag: " + getRes.getString(4) +" ");
			}
			return list;
			
		} catch (SQLException e) {
			
		}

		return null;
	}
	
	public ArrayList<String> getWorkers() {
		
		ArrayList<String> list = new ArrayList<String>();
		try (PreparedStatement pstatement = connection.prepareStatement("SELECT dateofbirth, name FROM worker;")){
			
			
			ResultSet getRes = pstatement.executeQuery();
			
			while(getRes.next()) {
				
				list.add("Personnummer: "+ getRes.getString(1) + ", Namn: " + getRes.getString(2 + "\n"));
				
			}
			
			return list;
			
		} catch (SQLException e) {
			
		}

		return null;
	}

}
