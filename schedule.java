
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.Statement;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.TimeZone;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;

public class schedule extends Applet implements Runnable {
	
	
	long t = 0,arrivalt,departuret;
	public void start(){
		
		
		Thread t = new Thread(this);
		t.start();
	}
	
	
	

	@Override
	public void run() {
		
		try
	    {
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/db";
//	      com.mysql.jdbc.Driver
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "sai1");
	      java.sql.Statement st = conn.createStatement();
	      
	      String query = "SELECT * FROM trains";
	      System.out.println(query);
	      ResultSet rs = st.executeQuery(query);
	      while (rs.next())
	      {
	        int id = rs.getInt("id");
	        String arrivaltime = rs.getString("arrival");
	        String departuretime = rs.getString("departure");
	        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
	        formatter1.setTimeZone(TimeZone.getTimeZone("GMT"));
	        Date arrivaldate = formatter1.parse(arrivaltime);
	        Date departuredate = formatter1.parse(departuretime);
	        arrivalt=arrivaldate.getTime();
	        departuret=departuredate.getTime();
	        
	        if(departuret==t){
	        	trainarrival(id);
	        }
	        else if(arrivalt==t){
	        	traindeparture(id);
	        }
	        System.out.format("%s, %s\n", arrivaldate, departuredate);
	        System.out.format("%s, %s, %s\n", id, arrivalt, departuret);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		// TODO Auto-generated method stub
//		while(true){
//			repaint();
//			
//			
//			
//			try {
//				Thread.sleep(17);
//				t+=500;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}

	private void trainarrival(int id) {
		//System.out.println("This train has arrived");
	}

	private void traindeparture(int id) {
		
	}


}
