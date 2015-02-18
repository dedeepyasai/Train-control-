
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
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


public class trains {

	 public double trainx=1600;
	 public double trainy=1600;
	
	public int id;
	public long arrival;
	public long departure;
	public boolean pb=false;
	public boolean pbd=false;
	public boolean pbfree=false;
	public long arrival_plt;
	public boolean plat_assgn=false;
	public boolean outr_assgn=false;
	public long time_diff;
	public long delay;
	public int train_direct;
	main m;
	public double train_speed=500*0.005;
	
	
	
	
	
	
	public trains( int a) {
		id=a;
		// TODO Auto-generated constructor stub
	}
	
	
	public void train_out(trains t){
		if(t.train_direct==0){
			if(t.trainx<880){
				t.trainx+=train_speed;
				
			}
			
			if(t.trainx>=880 && t.trainx<1400){
				//t.trainx=1080;
				//int tp = m.get_outerr();
				//System.out.print(tp);
				//t.trainy=50+(m.outer_linesr*50);
				train_getout(t);
				t.pbfree=true;
			}
		}
		if(t.train_direct==1){
			if(t.trainx>300){
				t.trainx-=train_speed;
			}
			
			if(t.trainx<=300 && t.trainx>0){
				//t.trainx=120;
				//t.trainy=50+(m.outer_linesl*50);
				train_getout(t);
				t.pbfree=true;
				
			}
		}
		
	}
	
	public void train_getout(trains t){
		t.trainx=-500;
		t.trainy=-550;
		//t.pbfree=true;
	}
	
	
	
	
	public void moveto_outer(trains t, int a){
		if(t.train_direct==0){
			t.trainx=120;
			t.trainy=50+(a*50);
		}
		if(t.train_direct==1){
			t.trainx=1080;
			t.trainy=50+(a*50);
		}
		
	}
	
	
	public void moveto_plt(trains t, int j){
		if(t.train_direct==0){
			t.trainx=300;
			t.trainy=50+(j*50);
			//train_move(t);
		}
		
		if(t.train_direct==1){
			t.trainx=880;
			t.trainy=50+(j*50);
		}
		
	}
	public void train_move(trains t){
		if(t.train_direct==0){
			if(t.trainx<650){
				t.trainx+=train_speed;
			}
		}
		if(t.train_direct==1){
			if(t.trainx>650){
				t.trainx-=train_speed;
			}
		}
		
	}
	
	public void paint(Graphics g){
		//g.drawImage(m.train, (int)trainx,(int)trainy , (int)trainx+120, (int)trainy+24, (ImageObserver) this);
		g.setColor(Color.ORANGE);
	   g.fillRect((int)trainx,(int)trainy, 120, 24);
	  g.setColor(Color.yellow);
	 g.drawRect((int)trainx, (int)trainy, 120, 24);
	  g.drawLine((int)trainx+30,(int)trainy ,(int)trainx+30 , (int)trainy +24);
	  g.drawLine((int)trainx+32,(int)trainy ,(int)trainx+32 , (int)trainy +24);
	  g.drawLine((int)trainx+60,(int)trainy ,(int)trainx+60 , (int)trainy +24);
	  g.drawLine((int)trainx+62,(int)trainy ,(int)trainx+62 , (int)trainy +24);
	  g.drawLine((int)trainx+90,(int)trainy ,(int)trainx+90 , (int)trainy +24);
	  g.drawLine((int)trainx+92,(int)trainy ,(int)trainx+92 , (int)trainy +24);
	  g.setColor(Color.black);
	 g.drawString("Train no."+(Integer.toString(id)), (int)trainx, (int)trainy);
	 
	 
	 // g.fillOval((int)trainx,(int)trainy, 20,20);
	}
	
	
	
	
	
}
