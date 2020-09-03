package postgresqltutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InfoModifier {
	private Connection connection;
	
	private String password;
	
	public InfoModifier(String password) {
		this.password=password;
		
		connect();
		
	}
	public void connect() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();

			connection = DriverManager.getConnection("jdbc:postgresql://pgserver.mah.se/projectchipahm", "ah7947", password);
			System.out.println("Connection is established.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addBand(String band) {
		String []parts = band.split(",");
		
		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO band (bandid,country,bandname,contactperson) VALUES (?,?,?,?)")) {

			statement.setInt(1, Integer.parseInt(parts[0]));
			statement.setString(2, parts[1]);
			statement.setString(3, parts[2]);
			statement.setInt(4, Integer.parseInt(parts[3]));

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeBand (int id) {
		
		try (PreparedStatement statement3 = connection
				.prepareStatement("DELETE FROM schedule WHERE bandid=?")) {

			statement3.setInt(1, id);
			statement3.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (PreparedStatement statement2 = connection
				.prepareStatement("DELETE FROM partof WHERE bandid=?")) {

			statement2.setInt(1, id);
			statement2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (PreparedStatement statement = connection
				.prepareStatement("DELETE FROM band WHERE bandid=?")) {

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addWorker(String worker) {
		String []parts = worker.split(",");
		
		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO worker (dateofbirth,name,address) VALUES (?,?,?)")) {

			statement.setInt(1, Integer.parseInt(parts[0]));
			statement.setString(2, parts[1]);
			statement.setString(3, parts[2]);

			statement.executeUpdate();
			System.out.println("Successful");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeWorker (int personnumber) {
		try (PreparedStatement statement2 = connection
				.prepareStatement("UPDATE band SET contactperson=NULL WHERE contactperson=?")) {

			statement2.setInt(1, personnumber);
			statement2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (PreparedStatement statement3 = connection
				.prepareStatement("DELETE FROM worker WHERE dateofbirth=?")) {

			statement3.setInt(1, personnumber);
			statement3.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addMember(int memberid,String name) {
		
		
		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO member (memberid,name) VALUES (?,?)")) {

			statement.setInt(1, memberid);
			statement.setString(2, name);


			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatePartOf(int bandid,int memberid) {
		
		
		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO partof (memberid,bandid) VALUES (?,?)")) {

			statement.setInt(1, memberid);
			statement.setInt(2, bandid);


			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void removeMember(int memberid,int bandid) {
		
		try(PreparedStatement statement2 = connection.prepareStatement("DELETE FROM partof WHERE memberid =? AND bandid=?")){
			
			statement2.setInt(1, memberid);			
			statement2.setInt(2, bandid);

			statement2.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(PreparedStatement statement = connection.prepareStatement("DELETE FROM member WHERE memberid =?")){
			
			statement.setInt(1, memberid);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void addPlay(String member) {
		String parts[] = member.split(",");
		
		try (PreparedStatement statement = connection
				.prepareStatement("INSERT INTO schedule (bandid,time,event,day,securityid) VALUES (?,?,?,?,?)")) {

			statement.setInt(1, Integer.parseInt(parts[0]));
			statement.setString(2, parts[1]);
			statement.setString(3, parts[2]);
			statement.setString(4, parts[3]);
			statement.setInt(5, Integer.parseInt(parts[4]));

			statement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removePlay(String time,String scen) {
		
		try(PreparedStatement statement = connection.prepareStatement("DELETE FROM schedule WHERE time =? AND event=?")){
			
			statement.setString(1, time);
			statement.setString(2, scen);

			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getWorkers() {
		
		ArrayList<String> list = new ArrayList<String>();
		try (PreparedStatement pstatement = connection.prepareStatement("SELECT dateofbirth,name FROM worker;")){
			
			
			ResultSet getRes = pstatement.executeQuery();
			
			while(getRes.next()) {
				
				list.add("Personnummer: "+getRes.getString(1) + ",Namn: " + getRes.getString(2) + "\n");
				
			}
			
			
			return list;
			
		} catch (SQLException e) {
			
		}

		return null;
	}
	
	public ArrayList<String> getContactList() {
		
		ArrayList<String> list = new ArrayList<String>();
		  try (PreparedStatement pstatement = connection.prepareStatement("SELECT worker.name, member.name, band.bandname FROM partof, member, band, worker WHERE partof.memberid=member.memberid AND partof.bandid=band.bandid AND band.contactperson = dateofbirth;")){
			
			ResultSet getRes = pstatement.executeQuery();
			
			while(getRes.next()) {
				
				list.add("\n" + getRes.getString(1) + " ansvarig för: " + getRes.getString(2) + " (" + getRes.getString(3) +")" + "\n");
				
			}
			
			
			return list;
			
		} catch (SQLException e) {
			
		}

		return null;
	}
	

}
