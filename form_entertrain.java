import java.awt.FlowLayout;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class form_entertrain extends JFrame
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/db";
	static final String USER = "root";
	static final String PASS = "sai1";
	public int trainno,direction;
	public String arrival;
	public String departure;
	public ResultSet count;
	public int train_count;
	Connection conn = null;
    java.sql.Statement stmt = null;
	
	JLabel l,d;
	public void start()
	{   
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        l=new JLabel();
			l.setText("	ID :");
			JTextField id=new JTextField();
			id.setText("Train no");
			JTextField arr=new JTextField();
			arr.setText("Arrival time");
			JTextField depar=new JTextField();
			depar.setText("Departure time");
			d=new JLabel();
			d.setText("	Direction :");
			JTextField dir=new JTextField();
			dir.setText("direction");
			JButton but=new JButton();
			but.setText("Submit");
			add(l);
			add(id);
			add(arr);
			add(depar);
			add(d);
			add(dir);
			add(but);
			but.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						trainno=Integer.parseInt(id.getText());
						arrival=arr.getText();
						departure=depar.getText();
						direction=Integer.parseInt(dir.getText());
						try {
							stmt = conn.createStatement();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						String sql = "INSERT INTO trains" + "(id, arrival, departure, direction)" + "VALUES" + "(" + trainno + ",'" + arrival + "','"+departure + "'," +direction+")";
						try {
							stmt.execute(sql);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						System.out.println(sql);
						
					}
				}
			);
			setLayout(new FlowLayout());
			setSize(400,400);
			setVisible(true);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
	    } catch(SQLException se) {
	        se.printStackTrace();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
//	        try {
//	            if(stmt != null){}
//	                //conn.close();
//	         //catch(SQLException se) {
//	        }
//	        try {
//	            if(conn != null){}
//	               // conn.close();
//	        } catch(SQLException se) {
//	            se.printStackTrace();
//	        }
	    }
	    
	    
		
	}
	
	public static void main(String args[])
	{
		new form_entertrain().start();
	}
}










