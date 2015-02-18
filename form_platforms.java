
import java.awt.FlowLayout;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class form_platforms extends JFrame
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/db";
	static final String USER = "root";
	static final String PASS = "sai1";
	Connection conn = null;
    java.sql.Statement stmt = null;
    
    main m1;
	
	JLabel plat;
	JLabel outer_r;
	JLabel outer_l;
	
	public void start()
	{   
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection(DB_URL, USER, PASS);
		
	        plat=new JLabel();
			plat.setText("	Platforms :");
			JTextField platform=new JTextField();
			platform.setText("     ");
			
			outer_l=new JLabel();
			outer_l.setText("left_outer_lines :");
			JTextField outer_left=new JTextField();
			outer_left.setText("     ");
			
			outer_r=new JLabel();
			outer_r.setText("right_outer_lines :");
			JTextField outer_right=new JTextField();
			outer_right.setText("     ");
			
			JButton but=new JButton();
			but.setText("Submit");
			add(plat);
			add(platform);
			add(outer_l);
			add(outer_left);
			add(outer_r);
			add(outer_right);
			add(but);
			but.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						int platforms=Integer.parseInt(platform.getText());
						int outer_linesl=Integer.parseInt(outer_left.getText());
						int outer_linesr=Integer.parseInt(outer_right.getText());
						try {
							stmt = conn.createStatement();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String sql = "INSERT INTO platforms" + "(platforms, outer_left, outer_right)" + "VALUES" + "(" + platforms + ",'" + outer_linesl + "','" + outer_linesr + "')";
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
		new form_platforms().start();
	}
}










