
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JRadioButton;

public class main extends Applet implements Runnable , ActionListener{

	
	public int outer_linesl=get_outerl();
	public int outer_linesr=get_outerr();
	public int platforms=get_platforms();
	//public String hms;
	//get_platforms();
	public int gap=0;
	public int gap5=0;
	public int gap2=0;
	public int gap3=0;
	public int lines=37;
	public int gap4=0;
	long time=0,arrivalt,departuret;
	private Image img_bufff;
	private Graphics buffg;
	public int count=0;
	int train_dir;
    long dir=0;
    public int pr;
    public long time_inc=0;
     public long[][] outl= new long[outer_linesl][2];
     public long[][] outr= new long[outer_linesr][2];
     public long[][] plt= new long[platforms][2];
     public int[] prev= new int[3];
	//public boolean outer_assgn=false;
	//public boolean platform_assgn=false;
public int id;
URL u;
Image train;
public String query;
java.sql.Statement st;
java.sql.Statement st1,st2;
public long arrival_temp,departure_temp;
int r=count_trains();
//r= count_trains();

trains tn[] = new trains[r];
trains tno[]= new trains[r];
	
      public void start() {
    	  
//    	  for(int j=0;j<outer_linesl-1;j++){
//    		  outl[j]=1234;
//    	  }
       // outl[0]=12345;
       // outl[1]=1234;
        
    	  for(int i=0;i<r;i++){
    		  tn[i]= new trains(id);
    	 }
    	  for(int i=0;i<r;i++){
    		  tno[i]= new trains(id);
    	 }
//    	  for(int i=0;i<r;i++){
//    		  tn[i].id= 1+i;
//    	 }
    	  System.out.println(r);
		Thread t = new Thread(this);
		t.start();
	};
	
	public int get_outerl(){
		ResultSet rs;
		try{
			
		String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/db";
//	      com.mysql.jdbc.Driver
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "sai1");
	       st2 = conn.createStatement();
	      query = "SELECT * FROM platforms";
	      rs=st2.executeQuery(query);
	     
	      while(rs.next()){
	    	 
	    	  outer_linesl=rs.getInt("outer_left");
	    	 // outer_linesr=rs.getInt("outer_right");
	    	  //platforms=rs.getInt("platforms");
	      }
	      st1.close();
	      }
		
		catch (Exception e)
	    {
	      System.err.println("Got an exception hhh! ");
	      System.err.println(e.getMessage());
	    }
		return outer_linesl;
	}
	
	public int get_outerr(){
		ResultSet rs;
		try{
			
		String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/db";
//	      com.mysql.jdbc.Driver
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "sai1");
	       st2 = conn.createStatement();
	      query = "SELECT * FROM platforms";
	      rs=st2.executeQuery(query);
	     
	      while(rs.next()){
	    	 
	    	 // outer_linesl=rs.getInt("outer_left");
	    	  outer_linesr=rs.getInt("outer_right");
	    	  //platforms=rs.getInt("platforms");
	      }
	      st1.close();
	      }
		
		catch (Exception e)
	    {
	      System.err.println("Got an exception hhh! ");
	      System.err.println(e.getMessage());
	    }
		return outer_linesr;
	}
	
	public int get_platforms(){
		ResultSet rs;
		try{
			
		String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/db";
//	      com.mysql.jdbc.Driver
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "sai1");
	       st2 = conn.createStatement();
	      query = "SELECT * FROM platforms";
	      rs=st2.executeQuery(query);
	     
	      while(rs.next()){
	    	 
	    	  platforms=rs.getInt("platforms");
	      }
	      st1.close();
	      }
		
		catch (Exception e)
	    {
	      System.err.println("Got an exception hhh! ");
	      System.err.println(e.getMessage());
	    }
		return platforms;
	}

	
	public int count_trains(){
		
		// tn[i].id=0;
		ResultSet rs;
		try{
			
		String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost/db";
//	      com.mysql.jdbc.Driver
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "sai1");
	       st1 = conn.createStatement();
	      query = "SELECT * FROM trains";
	      rs=st1.executeQuery(query);
	     
	      while(rs.next()){
	    	 
	    	  count++;
	      }
	      st1.close();
	      }
		
		catch (Exception e)
	    {
	      System.err.println("Got an exception hhh! ");
	      System.err.println(e.getMessage());
	    }
		return count;
	}
	public void init() {

		setSize(1366, 768);
		
		try {
			u = getDocumentBase();
			train = getImage(u, "train.png");
		} catch (Exception e) {

		}
		
		
		previousleft=new Label("PREVIOUS STATION");
		previousright=new Label("PREVIOUS STATION");
		previousl1 = new Label(null);
		previousl2 = new Label(null);
		previousl3 = new Label(null);
		previousr1 = new Label(null);
		previousr2 = new Label(null);
		previousr3 = new Label(null);
		add(previousleft);
		add(previousright);
		add(previousl1);
		add(previousl2);
		add(previousl3);
		add(previousr1);
		add(previousr2);
		add(previousr3);
		update_platforms = new Button("Update Platforms");
		add_train = new Button("Add Train");
		del_train=new Button("Delete Train");
		add(update_platforms);
		add(add_train);
		add(del_train);
		update_platforms.addActionListener(this);
		add_train.addActionListener(this);
		del_train.addActionListener(this);
		rb1= new Button("1x");
		rb2= new Button("15x");
		rb3= new Button("30x");
		rb4= new Button("60x");
		rb5= new Button("120x");
		rb6= new Button("300x");
		rb7= new Button("600x");
		add(rb1);
		add(rb2);
		add(rb3);
		add(rb4);
		add(rb5);
		add(rb6);
		add(rb7);
		
		rb1.addActionListener(this);
		rb2.addActionListener(this);
		rb3.addActionListener(this);
		rb4.addActionListener(this);
		rb5.addActionListener(this);
		rb6.addActionListener(this);
		rb7.addActionListener(this);
		
	}
   Button add_train, del_train,update_platforms;
   Label previousl1,previousl2,previousl3,previousr1,previousr2,previousr3,previousleft,previousright;
   Button rb1,rb2,rb3,rb4,rb5,rb6,rb7;
	
	public void update(Graphics g) {
		if (img_bufff == null) {
			img_bufff = createImage(this.getSize().width, this.getSize().height);
			buffg = img_bufff.getGraphics();
		}
		buffg.setColor(getBackground());
		buffg.fillRect(0, 0, this.getSize().width, this.getSize().height);
		paint(buffg);
		g.drawImage(img_bufff, 0, 0, this);
	}
	
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(45, 535, 1300,150 );
		g.setColor(new Color(153, 255, 51));
		g.fillRect(46, 536, 1298, 148);
		g.setFont(new Font("ARIAL", Font.BOLD, 13));
		g.setColor(Color.black);
		g.drawString("TRAIN NUMBER",50, 550);
		g.drawString("ARRIVAL",50, 575);
		g.drawString("DEPARTURE",50, 600);
		g.drawString("DELAY",50, 625);
		g.drawString("SCHEDULED ARRIVAL",50, 650);
		g.drawString("SCHEDULED DEPARTURE",50, 675);
		
		
		
		for(int k=0;k<r;k++){
			g.setFont(new Font("ARIAL", Font.BOLD, 13));
			g.drawString(Integer.toString(tno[k].id),225+k*65, 550);
			g.drawLine(212+k*65, 535, 212+k*65, 683);
			g.setFont(new Font("Arial", Font.PLAIN, 13));
			g.drawString(milli_conv(tno[k].arrival),215+k*65, 575);
			g.drawString(milli_conv(tno[k].departure),215+k*65, 600);
			g.drawString(milli_conv(tn[k].delay),215+k*65, 625);
			g.drawString(milli_conv(tn[k].arrival_plt),215+k*65, 650);
			g.drawString(milli_conv(tn[k].departure),215+k*65, 675);
			
		}
		
		
//		for(int j=0;j<r;j++){
//			tn[j].paint(g);
//		}
		
		gap=0;
		gap3=0;
		gap4=0;
		gap2=0;
		gap5=0;
		g.setColor(Color.blue);
		for(int i=0;i<outer_linesl;i++){
			gap3=0;
			for(int j=0;j<15;j++){
				g.drawLine(120+gap3, 50+gap, 120+gap3, 74+gap);
				g.drawLine(122+gap3, 51+gap, 122+gap3, 74+gap);
				gap3+=8;
			}
			g.drawLine(120, 50+gap, 240, 50+gap);
			g.drawLine(120, 51+gap, 240, 51+gap);
			g.drawLine(120, 73+gap, 240, 73+gap);
			g.drawLine(120, 74+gap, 240, 74+gap);
			gap+=50;
		}
		
		
		for(int k=0;k<outer_linesr;k++){
			gap3=0;
			for(int l=0;l<15;l++){
				g.drawLine(1080+gap3, 50+gap5, 1080+gap3, 74+gap5);
				g.drawLine(1082+gap3, 51+gap5, 1082+gap3, 74+gap5);
				gap3+=8;
			}
			g.drawLine(1080, 50+gap5, 1200, 50+gap5);
			g.drawLine(1080, 51+gap5, 1200, 51+gap5);
			g.drawLine(1080, 73+gap5, 1200, 73+gap5);
			g.drawLine(1080, 74+gap5, 1200, 74+gap5);
			gap5+=50;
		}
		
		
	g.setColor(Color.black);
		
		for(int i=0;i<platforms;i++){
			gap4=0;
			for(int j=0;j<36;j++){
				g.drawLine(300+gap4, 52+gap2, 300+gap4, 70+gap2);
				g.drawLine(302+gap4, 52+gap2, 302+gap4, 70+gap2);
				gap4+=20;
			}
			g.drawLine(300, 50+gap2, 1000, 50+gap2);
			g.drawLine(300, 51+gap2, 1000, 51+gap2);
			g.drawLine(300, 53+gap2, 1000, 53+gap2);
			g.drawLine(300, 54+gap2, 1000, 54+gap2);
			g.drawLine(300, 70+gap2, 1000, 70+gap2);
			g.drawLine(300, 71+gap2, 1000, 71+gap2);
			g.drawLine(300, 73+gap2, 1000, 73+gap2);
			g.drawLine(300, 74+gap2, 1000, 74+gap2);
			gap2+=50;
		}
		
		for(int j=0;j<r;j++){
			tn[j].paint(g);
			//g.drawImage(train, (int)tn[j].trainx,(int)tn[j].trainy , 120, 24, this);
		}
		
		g.setColor(Color.BLACK);
		//tn.paint(g);
		this.add_train.setLocation(50,500);
		this.del_train.setLocation(150,500);
		this.update_platforms.setLocation(250,500);
		this.rb1.setLocation(450,500);
		this.rb2.setLocation(500,500);
		this.rb3.setLocation(550,500);
		this.rb4.setLocation(600,500);
		this.rb5.setLocation(650,500);
		this.rb6.setLocation(700,500);
		this.rb7.setLocation(750,500);
		this.previousleft.setLocation(5,15);
		this.previousl1.setBackground(Color.blue);
		this.previousl1.setForeground(Color.white);
		this.previousl2.setBackground(Color.blue);
		this.previousl2.setForeground(Color.white);
		this.previousl3.setBackground(Color.blue);
		this.previousl3.setForeground(Color.white);
		this.previousr1.setBackground(Color.blue);
		this.previousr1.setForeground(Color.white);
		this.previousr2.setBackground(Color.blue);
		this.previousr2.setForeground(Color.white);
		this.previousr3.setBackground(Color.blue);
		this.previousr3.setForeground(Color.white);
		this.previousleft.setBackground(Color.blue);
		this.previousleft.setForeground(Color.white);
		this.previousright.setBackground(Color.blue);
		this.previousright.setForeground(Color.white);
		this.previousleft.setFont(new Font("Arial", Font.BOLD, 12));
		this.previousright.setFont(new Font("Arial", Font.BOLD, 12));
		
		this.previousl1.setSize(80, 25);
		this.previousl2.setSize(80, 25);
		this.previousl3.setSize(80, 25);
		this.previousr1.setSize(80, 25);
		this.previousr2.setSize(80, 25);
		this.previousr3.setSize(80, 25);
		this.previousl1.setLocation(10,50);
		this.previousl2.setLocation(10,100);
		this.previousl3.setLocation(10,150);
		this.previousr1.setLocation(1240,50);
		this.previousr2.setLocation(1240,100);
		this.previousr3.setLocation(1240,150);
		this.previousright.setLocation(1220,15);
		
		g.setColor(Color.black);
		g.drawRect(1179, 469, 151, 51);
		g.setColor(Color.blue);
		g.fillRect(1180, 470, 150, 50);
	    g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		g.drawString(milli_conv(time), 1200, 500);
		
	}
	
	@Override
	public void run() {
         
		int i=0;
		
			try
		    {
		      String myDriver = "com.mysql.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost/db";
//		      com.mysql.jdbc.Driver
		      Class.forName(myDriver);
		      Connection conn = DriverManager.getConnection(myUrl, "root", "sai1");
		       st = conn.createStatement();
		      query = "SELECT * FROM platforms";
		      //System.out.println(query);
		      ResultSet rs1 = st.executeQuery(query);
		      while (rs1.next())
		      {
		    	  platforms=rs1.getInt("platforms");
		    	  outer_linesl=rs1.getInt("outer_left");
		    	  outer_linesr=rs1.getInt("outer_right");
		      }
		      
		      query = "SELECT * FROM trains";
		      //System.out.println(query);
		      ResultSet rs2 = st.executeQuery(query);
		      
		      while (rs2.next())
		      {
		    	  
		        id = rs2.getInt("id");
		        String arrivaltime = rs2.getString("arrival");
		        String departuretime = rs2.getString("departure");
		        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
		        formatter1.setTimeZone(TimeZone.getTimeZone("GMT"));
		        Date arrivaldate = formatter1.parse(arrivaltime);
		        Date departuredate = formatter1.parse(departuretime);
		        arrivalt=arrivaldate.getTime();   
		        departuret=departuredate.getTime();
		        train_dir=rs2.getInt("direction");
		       
		      tn[i].id=id;
		      tn[i].arrival=arrivalt;
		      tn[i].departure=departuret;
		      tn[i].arrival_plt=arrivalt;
		      tn[i].train_direct=train_dir;
		      tno[i].id=id;
		      tno[i].arrival=arrivalt;
		      tno[i].departure=departuret;
		      tno[i].arrival_plt=arrivalt;
		      tno[i].train_direct=train_dir;
		     //System.out.println(tn[i].arrival);
		       i=i+1;
		       
		      }
		     st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
		
			
			while(true){
//				System.out.println(tn[0].arrival);
//				System.out.println(tn[1].arrival);
				
		
				
				
				
				if(dir%2==0){
					checkouter_frpltl();
					checkouter_frpltr();	
				}else{
					checkouter_frpltr();
					checkouter_frpltl();
				}
//				
				
				//System.out.println(tn[5].departure);
			//	System.out.print(tn[6].plat_assgn);
				//System.out.println(tn[6].outr_assgn);
				//System.out.println(tn[8].departure);
				
				 
				// System.out.print(milli_conv(time);
				   for(int j=0;j<r;j++){
						
						if(tn[j].departure==time){
							train_departure(tn[j]);
						}
					}
				   
				
				for(int j=0;j<r;j++){
					if(tn[j].pb){
						tn[j].train_move(tn[j]);
					}
					if(tn[j].pbd){
						tn[j].train_out(tn[j]);
					}
					if(tn[j].pbfree){
						free_platform(tn[j]);	
					}
					
				}
				
                
				
				for(int j=0;j<r;j++){	
					//System.out.println(tn[j].arrival_plt);
					if(tn[j].arrival==time){
					
						train_arrival(tn[j]);
					}
				}
				if(time%15000==0){
				previousl1.setText(null);
				previousl2.setText(null);
				previousl3.setText(null);
				previousr1.setText(null);
				previousr2.setText(null);
				previousr3.setText(null);
				for(int k=0;k<r;k++){
					if(tn[k].train_direct==0){
						
						previous_stnl(tn[k]);
					}
					
					else{
						previous_stnr(tn[k]);
					}
				}
				
				}
				
				for(int k=0;k<r;k++){
					tn[k].delay=tn[k].arrival_plt-tno[k].arrival;
				}
				
			//	System.out.print(previousl1.getText());
             
			repaint();
			
			try {
			
//				tn.trainx+=0.5;
//				tn.trainy+=0.5;
				Thread.sleep(17);
				dir++;
				time+=time_inc;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void train_arrival(trains t){
      if(t.train_direct==0){
    	  check_outerl(t); 
      }
		if(t.train_direct==1){
			check_outerr(t);
		}
		
		
	}
	
	public void check_outerr(trains t){
		
		for(int k=0;k<outer_linesr-1;k++){
			
		if(outr[k][0]==0){
				outr[k][0]=t.arrival;
				outr[k][1]=t.id;
			  t.moveto_outer(t,k);
				t.outr_assgn=true;
				break;
			}

		}
		if(t.outr_assgn==false){
			t.arrival+=60000;
			t.arrival_plt+=60000;
			t.departure+=60000;
		}
		
	}
	
	public void check_outerl(trains t){
			
		for(int k=0;k<outer_linesl-1;k++){
			
		if(outl[k][0]==0 && outl[k][1]==0){
				outl[k][0]=t.arrival;
				outl[k][1]=t.id;
			  t.moveto_outer(t,k);
				t.outr_assgn=true;
				break;
			}

		}
		if(t.outr_assgn==false){
			t.arrival+=60000;
			t.arrival_plt+=60000;
			t.departure+=60000;
			
		}
		
	}
	
	public int minl(long[][] a){
		long min=0;
		int pos=-1;
		for(int i=0;i<outer_linesl;i++){
			if(a[i][0]>0){
				min=a[i][0];
			pos=i;
			//System.out.print(min);
			break;
			}
		}
		
		for(int i=0;i<outer_linesl;i++){
		 if(a[i][0]> 0 && a[i][0]<min){
				min=a[i][0];
				pos=i;
			}
		 //System.out.print(min);
		}
		return pos;
	}
	
//	public int minl(long[][] a){
//		long min=0;
//		int pos=-1;
//		for(int i=0;i<outer_linesl;i++){
//			if(a[i][0]>0){
//				min=a[i][0];
//			pos=i;
//			//System.out.print(min);
//			break;
//			}
//		}
//		
//		for(int i=0;i<outer_linesl;i++){
//		 if(a[i][0]> 0 && a[i][0]<min){
//				min=a[i][0];
//				pos=i;
//			}
//		 //System.out.print(min);
//		}
//		return pos;
//	}
	
	
	
	
	public int minr(long[][] a){
		long min=0;
		int pos=-1;
		for(int i=0;i<outer_linesr;i++){
			if(a[i][0]>0){
				min=a[i][0];
			pos=i;
			//System.out.print(min);
			break;
			}
		}
		
		for(int i=0;i<outer_linesr;i++){
		 if(a[i][0]> 0 && a[i][0]<min){
				min=a[i][0];
				pos=i;
			}
		 //System.out.print(min);
		}
		return pos;
	}
	
	public void checkouter_frpltl(){
		int p=minl(outl);
		//System.out.print(p);
		if(p==-1){
			return;
		}
		else if(p>=0){
			checkfree_pltl(p);
		}
	}
	
	public void checkouter_frpltr(){
		int p=minr(outr);
		//System.out.print(p);
		if(p==-1){
			return;
		}
		else if (p>=0){
			checkfree_pltr(p);
		}
	}
	
	public void checkfree_pltl(int p){
		for(int j=0;j<platforms;j++){
			if(plt[j][0]==0){
				
				plt[j][0]=outl[p][0];
				plt[j][1]=outl[p][1];
				outl[p][0]=0;
				outl[p][1]=0;
				for(int i=0; i<r;i++){
					if(tn[i].id==plt[j][1]){
						tn[i].moveto_plt(tn[i],j);
						tn[i].pb=true;
						tn[i].plat_assgn=true;
						
					}
				}
				
			}
		}
			for(int i=0; i<r;i++){
				if(tn[i].outr_assgn==true){
					if(tn[i].plat_assgn==false){
						if(tn[i].train_direct==0){
							tn[i].arrival_plt+=time_inc;
							tn[i].departure+=time_inc;
						}
							
					}
				}
			}

	}
	
	public void checkfree_pltr(int p) {

		for (int j = 0; j < platforms; j++) {
			if (plt[j][0] == 0) {

				plt[j][0] = outr[p][0];
				plt[j][1] = outr[p][1];
				outr[p][0] = 0;
				outr[p][1] = 0;
				for (int i = 0; i < r; i++) {
					if (tn[i].id == plt[j][1]) {
						tn[i].moveto_plt(tn[i], j);
						tn[i].pb = true;
						tn[i].plat_assgn = true;

					}
				}
			}
		}
		for (int i = 0; i < r; i++) {
			if (tn[i].outr_assgn == true) {
				if (tn[i].plat_assgn == false) {
					if (tn[i].train_direct == 1) {
						tn[i].arrival_plt += time_inc;
						tn[i].departure += time_inc;
					}
				}
			}
		}
	}
	 
	
public void free_platform(trains t){
		
		if(t.pbfree){
		for(int j=0;j<platforms;j++){
		if(plt[j][1]==t.id){
			plt[j][0]=0;
			plt[j][1]=0;
			
		  }
		}
		t.pbfree=false;
		}
		
	}
	
	
	public void train_departure(trains t){
	
		if(t.pb){
			t.pbd=true;
		}
			
	}
	
	public void previous_stnl(trains t){

		if(t.arrival-time<=300000 && t.arrival-time >0 ){
			 if(previousl1.getText()==null){
	             previousl1.setText("Train No."+(Integer.toString(t.id)));
	             //prev[0]=1;
	             
	         }
         else  if(previousl2.getText()==null){
             previousl2.setText("Train No."+(Integer.toString(t.id)));  
            // prev[0]=2;
         }
         else  if(previousl3.getText()==null){
             previousl3.setText("Train No."+(Integer.toString(t.id)));   
             //prev[0]=3;
         }
		}
			
	}
	
	public void previous_stnr(trains t){

		if(t.arrival-time<=300000 && t.arrival-time >0 ){
			 if(previousr1.getText()==null){
	             previousr1.setText("Train No."+(Integer.toString(t.id)));
	             //prev[0]=1;
	             
	         }
         else  if(previousr2.getText()==null){
             previousr2.setText("Train No."+(Integer.toString(t.id)));  
            // prev[0]=2;
         }
         else  if(previousr3.getText()==null){
             previousr3.setText("Train No."+(Integer.toString(t.id)));   
             //prev[0]=3;
         }
		}
			
	}
	
//	public String milli_conv(long time){
//	 String hms=	String.format("%02d:%02d:%02d", 
//			    TimeUnit.MILLISECONDS.toHours(time),
//			    TimeUnit.MILLISECONDS.toMinutes(time) - 
//			    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
//			    TimeUnit.MILLISECONDS.toSeconds(time) - 
//			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
//	//System.out.println(hms);
//	 return hms;
//	}
	public String milli_conv(long time){
		 String hms=	String.format("%02d:%02d", 
				    TimeUnit.MILLISECONDS.toHours(time),
				    TimeUnit.MILLISECONDS.toMinutes(time) - 
				    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)));
				  //  TimeUnit.MILLISECONDS.toSeconds(time) - 
				   // TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
		//System.out.println(hms);
		 return hms;
		}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==add_train){
			new form_entertrain().start();			
		}
		 if(e.getSource()==del_train){
			new form_deltrain().start();
		}
		 if(e.getSource()==update_platforms){
			 new form_platforms().start();
		 }
		 if(e.getSource()==rb1){
			 time_inc=20;
		 }
		 if(e.getSource()==rb2){
			 time_inc=250;
		 }
		 if(e.getSource()==rb3){
			 time_inc=500;
		 }
		 if(e.getSource()==rb4){
			 time_inc=1000;
		 }
		 if(e.getSource()==rb5){
			 time_inc=2000;
		 }
		 if(e.getSource()==rb6){
			 time_inc=5000;
		 }
		 if(e.getSource()==rb7){
			 time_inc=10000;
		 }
		
	}	
}
