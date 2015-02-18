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


public class form_deltrain extends JFrame
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/db";
	static final String USER = "root";
	static final String PASS = "sai1";
	public int trainno;
	public String arrival;
	public String departure;
	Connection conn = null;
    java.sql.Statement stmt = null;
	
	JLabel l;
	public void start()
	{   
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        l=new JLabel();
			l.setText("	ID :");
			JTextField id=new JTextField();
			id.setText("Train no");
			JButton but=new JButton();
			but.setText("Submit");
			add(l);
			add(id);
			add(but);
			but.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						trainno=Integer.parseInt(id.getText());
						try {
							stmt = conn.createStatement();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String sql = "DELETE FROM trains WHERE id=" + trainno;
						try {
						stmt.executeUpdate(sql);
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
		new form_deltrain().start();
	}
}










