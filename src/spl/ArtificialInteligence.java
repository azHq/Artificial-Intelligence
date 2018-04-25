package spl;



import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;


public class ArtificialInteligence extends JPanel implements MouseListener,ActionListener {
	
	
	private int[][] arr= new int[37][3];
	
	private int[][] position=new int[37][3];
	
	public Queue<Integer> queue=new LinkedList<Integer>();
	
	public int tmp1=0,tmp2=0,tmp3=0,tmp4=0;
	
	public boolean playGame=true;
	
	public boolean ploy1=true,ploy2=true;
	
	private int x=940,y=485;  //940,485,
	private Timer timer;
	private int delay=6;
	private int f=1,g,h=1,a;
	private String team1="azaz",team2="bablu";
	private int totalpawn1=16,totalpawn2=16;
	
	
	public int temp,sourceX,sourceY,destinationX,destinationY,AX,AY,BX,BY;
	
	
	public int[][] path=new int[37][8];
	
	public int[][] bestWay=new int[8][2];
	int[][] firstTwoMax=new int[2][2];
	
	int mouseClickCount=0;
	
	private int mouseTemp;

	
	
	
	ArrayList<ArrayList<Integer> > multiLineDraw=new  ArrayList<ArrayList<Integer>>();
	
	
	public int height;
	public int width;
	
	public int eachRoomHeight,eachRoomWidth;
	
	private int A=-100,B=-100;
	private int counter1=0,counter2=0,counter3=0;
	
	
	
	private boolean mouse,team1Move=true,team2Move=true;
	
	private boolean Atflag=false,drawLine=false,drawLine2=false;
	
	boolean click=false;
	
	public int apprehendSource,apprehendDestination;
	
	JFrame mainFrame;
	
	int hours,minutes=0,seconds=0;
	long time=0;
	String timerCount;
	
	Sound sound;
	
	
	
	public ArtificialInteligence(JFrame frame) {
		
		
		this.mainFrame=frame;
		
		sound=new Sound();
		
		
		MenuBar mn=new MenuBar();
		Menu menu=new Menu("Menu");
		menu.setFont(new Font("thoma",Font.BOLD,16));
		mn.setFont(new Font("thoma",Font.BOLD,16));

		
		MenuItem item1=new MenuItem("New Game");
		MenuItem item2=new MenuItem("Highest Score");
		MenuItem item3=new MenuItem("Exit");
		
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		
		mn.add(menu);
		
		frame.setMenuBar(mn);
		
		item3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				//azaz az=new azaz(mainFrame);
				
			}
			
		});
		
		item2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				BufferedReader bf;
				BufferedWriter bw;
				
				try {
					
					String s;
					bf =new BufferedReader( new FileReader(new File("score.txt")));
					if( (s=bf.readLine())!=null) {
						
						

				        String[] str=s.split(","); 
				        int time=Integer.parseInt(str[2]);
				        System.out.println(time);
				        int hours=(int)((time/1000)/3600);
				        int  minutes=(int)((time/1000)/60);
				        int  seconds=(int)((time/1000)%60);
				         
				        
				        
						
				       String  timerCount=String.format("%02d:%02d:%02d\n",hours,minutes,seconds);
				       String show=str[0]+"            "+str[1]+"             "+timerCount;
				        
						JFrame frame1 = new JFrame();
						frame1.setBounds(600, 200, 500,380);
						frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame1.setResizable(false);
						frame1.getContentPane().setLayout(null);
						frame1.getContentPane().setBackground(Color.green);
						
						JLabel label1=new JLabel("Highest Score");
						label1.setBounds(180, 0,400,80);
						label1.setFont(new Font("serif",Font.BOLD,26));
						
						
						JLabel label=new JLabel(show);
						label.setBounds(100, 70,400,180);
						label.setFont(new Font("serif",Font.BOLD,26));
						
						JLabel label2=new JLabel("Name         Beads          Time");
						label2.setBounds(95, 20,400,180);
						label2.setFont(new Font("serif",Font.BOLD,28));
						
						frame1.add(label2);
						frame1.add(label1);
						
						Button but=new Button("OK");
						but.setForeground(Color.black);
						but.setFont(new Font("serif",Font.BOLD,18));
						but.setBackground(Color.red);
						but.setBounds(300,280,120,40);
						
						frame1.getContentPane().add(label);
						frame1.add(but);
						
						frame1.setVisible(true);
						
						but.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								
								
								frame1.setVisible(false);
							}
						});
						
						
					}
					
					bf.close();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				//azaz az=new azaz(mainFrame);
				
			}
			
		});
		
		
		
		
		
		UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 18));
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font( "Arial", Font.BOLD, 18)));       
		UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
		
		team1=JOptionPane.showInputDialog("Please Enter Your Name:");
		team2="AI";
		
		if(team1.length()==0) {
			
			team1="Azaz";
			
		}
		
		
		
		
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		width = gd.getDisplayMode().getWidth();
		height = gd.getDisplayMode().getHeight();
		
		eachRoomHeight=height/6;
		eachRoomWidth=width/10+30;
		
		int posX,posY,count=0;
		
		for(int i=1;i<=9;i++) {
			for(int j=1;j<=5;j++) {
				
				posX=eachRoomWidth*(i-1);
				posY=eachRoomHeight*j;
				
				if((i==1&&j!=2&&j!=4)||(i==2&&j!=1&&j!=5)||(i==8&&j!=1&&j!=5)||(i==9&&j!=2&&j!=4)||(i>=3&&i<=7)) {
					
					position[count][0]=posX+80;
					position[count][1]=posY;
					
					if(count>=0&&count<=15) position[count][2]=2;
					else if(count>=21&&count<=36) position[count][2]=1;
					else position[count][2]=0;
					
					
					
					//System.out.println("positionX:"+posX+"   positionY:"+posY+"   count:"+count);
					
					count++;
					
				}
				
				
			}
			
		}
		
		//left
		position[0][0]+=50;
		position[0][1]+=50;
		
		position[1][0]+=50;
		
		
		position[2][0]+=50;
		position[2][1]-=50;
		
		
		
		//right
		position[34][0]-=50;
		position[34][1]+=50;
		
		position[35][0]-=50;
		//position[1][1]-=50;
		
		position[36][0]-=50;
		position[36][1]-=50;
		
		for(int i=0;i<37;i++) {
			
			arr[i][0]=position[i][0];
			arr[i][1]=position[i][1];
			arr[i][2]=position[i][2];
			
			
		}
		
		
		
		
		
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<37;i++) {
					
					arr[i][0]=position[i][0];
					arr[i][1]=position[i][1];
					arr[i][2]=position[i][2];
					
					totalpawn1=16;
					totalpawn2=16;
					click=false;
					playGame=true;
					time=0;
					
					
					
					
					
				}
			
			}
			
		});
		
		
		
		
		
		
		
		
	
		
		
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setPreferredSize(new Dimension(1900,1000));
		
		timer=new Timer(delay,this);
		timer.start();
		
		Scanner scr=new Scanner(System.in);
		
		//team1=scr.nextLine();
		//team2=scr.nextLine();
		
		//artificial intelligence
		
		
		
		for(int i=0;i<37;i++) {
			
			for(int j=0;j<8;j++) {
				
				path[i][j]=-1;
				
			}
		}
		
		//0
		temp=0;
		
		path[temp][5]=3;
		path[temp][6]=1;
		
		

		
		
		//1
		temp=1;
		path[temp][2]=0;
		path[temp][4]=4;
		path[temp][6]=2;
		
		
		
		
		//2
		temp=2;
		path[temp][2]=1;
		path[temp][3]=5;
		
		//3
		temp=3;
		path[temp][1]=0;
		path[temp][6]=4;
		path[temp][5]=8;
		
		
		
		//4
		temp=4;
		path[temp][0]=1;
		path[temp][2]=3;
		path[temp][4]=8;
		path[temp][6]=5;
		
		
		
		
		//5
		temp=5;
		path[temp][7]=2;
		path[temp][2]=4;
		path[temp][3]=8;
		
		
		
		
		
		//6
		temp=6;
		path[temp][6]=7;
		path[temp][4]=11;
		path[temp][5]=12;
		
		
		
		
		//7
		temp=7;
		path[temp][2]=6;
		path[temp][6]=8;
		path[temp][4]=12;
		
		
		
		
		
		//8
		temp=8;
		path[temp][0]=4;
		path[temp][1]=3;
		path[temp][2]=7;
		path[temp][3]=12;
		path[temp][4]=13;
		path[temp][5]=14;
		path[temp][6]=9;
		path[temp][7]=5;
		
		
		
		//9
		temp=9;
		path[temp][2]=8;
		path[temp][6]=10;
		path[temp][4]=14;
		
		
		
		

		//10
		temp=10;
		path[temp][2]=9;
		path[temp][3]=14;
		path[temp][4]=15;
	
		
		
		
		//11
		temp=11;
		path[temp][0]=6;
		path[temp][6]=12;
		path[temp][4]=16;
		
		
		
		
		
		//12
		temp=12;
		path[temp][0]=7;
		path[temp][1]=6;
		path[temp][2]=11;
		path[temp][3]=16;
		path[temp][4]=17;
		path[temp][5]=18;
		path[temp][6]=13;
		path[temp][7]=8;
		
		
		
		//complete
		
		
		
		//13
		temp=13;
		path[temp][0]=8;
		path[temp][2]=12;
		path[temp][6]=14;
		path[temp][4]=18;
		
		
		
		
		//14
		temp=14;
		path[temp][0]=9;
		path[temp][1]=8;
		path[temp][2]=13;
		path[temp][3]=18;
		path[temp][4]=19;
		path[temp][5]=20;
		path[temp][6]=15;
		path[temp][7]=10;
		
		
		
		
		//15
		temp=15;
		path[temp][0]=10;
		path[temp][2]=14;
		path[temp][4]=20;
		
		
		//16
		temp=16;
		path[temp][0]=11;
		path[temp][7]=12;
		path[temp][6]=17;
		path[temp][4]=21;
		path[temp][5]=22;
	
		
		
		
		//17
		temp=17;
		path[temp][0]=12;
		path[temp][2]=16;
		path[temp][6]=18;
		path[temp][4]=22;
		
		
		
		
		//18
		temp=18;
		path[temp][0]=13;
		path[temp][1]=12;
		path[temp][2]=17;
		path[temp][3]=22;
		path[temp][4]=23;
		path[temp][5]=24;
		path[temp][6]=19;
		path[temp][7]=14;

		
		
		
		//19
		temp=19;
		path[temp][0]=14;
		path[temp][2]=18;
		path[temp][6]=20;
		path[temp][4]=24;
		
		
		
		
		//20
		temp=20;
		path[temp][0]=15;
		path[temp][1]=14;
		path[temp][2]=19;
		path[temp][3]=24;
		path[temp][4]=25;
	
		
		
	
		
		//21
		temp=21;
		path[temp][0]=16;
		path[temp][6]=22;
		path[temp][4]=26;
		
		
		
		
		//22
		temp=22;
		path[temp][0]=17;
		path[temp][1]=16;
		path[temp][2]=21;
		path[temp][3]=26;
		path[temp][4]=27;
		path[temp][5]=28;
		path[temp][6]=23;
		path[temp][7]=18;
		
		
		//23
		temp=23;
		path[temp][0]=18;
		path[temp][2]=22;
		path[temp][6]=24;
		path[temp][4]=28;
		
		
		
		
		//24
		temp=24;
		path[temp][0]=19;
		path[temp][1]=18;
		path[temp][2]=23;
		path[temp][3]=28;
		path[temp][4]=29;
		path[temp][5]=30;
		path[temp][6]=25;
		path[temp][7]=20;
		
		
		//25
		temp=25;
		path[temp][0]=20;
		path[temp][2]=24;
		path[temp][4]=30;
	
		
		
		
		//26
		temp=26;
		path[temp][0]=21;
		path[temp][7]=22;
		path[temp][6]=27;
	
		
		
		
		//27
		temp=27;
		path[temp][0]=22;
		path[temp][2]=26;
		path[temp][6]=28;
		
		
		
		
		//28
		temp=28;
		path[temp][0]=23;
		path[temp][1]=22;
		path[temp][2]=27;
		path[temp][3]=31;
		path[temp][4]=32;
		path[temp][5]=33;
		path[temp][6]=29;
		path[temp][7]=24;

		
		
		
		//29
		temp=29;
		path[temp][0]=24;
		path[temp][2]=28;
		path[temp][6]=30;
	
		
		
		
		//30
		temp=30;
		path[temp][0]=25;
		path[temp][1]=24;
		path[temp][2]=29;
		
		
		
	
		//31
		temp=31;
		path[temp][7]=28;
		path[temp][6]=32;
		path[temp][3]=34;
		
		
		
		
		//32
		temp=32;
		path[temp][0]=28;
		path[temp][2]=31;
		path[temp][6]=33;
		path[temp][4]=35;
		
		
		
		
		//33
		temp=33;
		path[temp][1]=28;
		path[temp][2]=32;
		path[temp][5]=36;
		
		
		
		
		//34
		temp=34;
		path[temp][7]=31;
		path[temp][6]=35;
		
		
		
		
		//35
		temp=35;
		path[temp][0]=32;
		path[temp][2]=34;
		path[temp][6]=36;
		
		
		
		
		//36
		temp=36;
		path[temp][1]=33;
		path[temp][2]=35;
		
		Random rand=new Random();
		
		for(int i=0;i<37;i++) {
			
			queue.add(rand.nextInt(36));
			
		}
		
		for(int i=0;i<37;i++) {
			
			queue.add(i);
			
		}
		
		

		
		
		
		
		
		
		
		
	}
	
	
	protected void paintComponent(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0,0,2000,1050);
		
		
		
		
		
		
		
		g.setColor(Color.green);
		g.fillRect(0,0,width,10);
		
		g.setColor(Color.green);
		g.fillRect(0,height-40,width,10);
		
		g.setColor(Color.green);
		g.fillRect(width-10,0,10,height);
		
		g.setColor(Color.green);
		g.fillRect(0,0,10,height);
		
		
		
		//Graphics2D g=(Graphics2D) g;
		((Graphics2D) g).setStroke(new BasicStroke(3));
		
		g.setColor(Color.red);
		for(int i=6;i<=10;i++) {
			
			g.drawLine(position[i][0],position[i][1],position[i+20][0],position[i+20][1]);
			
		}
		for(int i=6;i<=30;i=i+5) {
			
			g.drawLine(position[i][0],position[i][1],position[i+4][0],position[i+4][1]);
			
		}
		
		//middle
		g.drawLine(position[1][0],position[1][1],position[35][0],position[35][1]);
		
		//slope
		g.drawLine(position[6][0],position[6][1],position[30][0],position[30][1]);
		
		g.drawLine(position[10][0],position[10][1],position[26][0],position[26][1]);
		
		//extended slope
		
		g.drawLine(position[16][0],position[16][1],position[2][0],position[2][1]);
		g.drawLine(position[20][0],position[20][1],position[0][0],position[0][1]);
		
		g.drawLine(position[16][0],position[16][1],position[36][0],position[36][1]);
		g.drawLine(position[20][0],position[20][1],position[34][0],position[34][1]);
		
		
		//extra line
		
		g.drawLine(position[0][0],position[0][1],position[2][0],position[2][1]);
		g.drawLine(position[3][0],position[3][1],position[5][0],position[5][1]);
		
		g.drawLine(position[34][0],position[34][1],position[36][0],position[36][1]);
		g.drawLine(position[31][0],position[31][1],position[33][0],position[33][1]);
		
		
		if(drawLine==true) {
			
			for(ArrayList<Integer> arr:multiLineDraw) {
				
				//System.out.println("draw: "+arr);
			
				Graphics2D g2d=(Graphics2D) g;
				g2d.setStroke(new BasicStroke(6));
				g2d.setColor(Color.blue);
				g2d.drawLine(arr.get(0),arr.get(1),arr.get(2),arr.get(3));
				
				
			}
			
			
			
			
			
		}
		
		if(drawLine2==true) {
			
			Graphics2D g2d=(Graphics2D) g;
			g2d.setStroke(new BasicStroke(6));
			g2d.setColor(Color.blue);
			g2d.drawLine(AX,AY,BX,BY);
			
			
			
		}
		
		
		
		
		
		
		
		
		
			g.setColor(Color.blue);
			
			for(int i=0;i<37;i++) {
				
					if(arr[i][2]>0) {
						if(arr[i][2]==1) g.setColor(Color.blue);
						else g.setColor(Color.yellow);
					
						
						g.fillOval(arr[i][0]-18,arr[i][1]-18,40,40);
						
						
					}
				
			}
		
		
		
		g.setColor(Color.blue);
		g.setFont(new Font("serif",Font.BOLD,30));
		g.drawString(team1,55,70);
		g.setFont(new Font("serif",Font.BOLD,30));
		g.drawString("TOTAL PAWN",45,110);
		g.drawString(team1,arr[11][0]+30,50);
		
		g.setColor(Color.red);
		g.drawString(": "+totalpawn1,250,110);
		
	
		
		g.setColor(Color.green);
		g.drawString("VS",arr[16][0]-50,50);
		
		g.setColor(Color.yellow);
		g.setFont(new Font("serif",Font.BOLD,30));
		g.drawString(team2,arr[31][0],70);
		g.setFont(new Font("serif",Font.BOLD,30));
		g.drawString("TOTAL PAWN",arr[31][0],110);
		g.drawString(team2,arr[16][0]+30,50);
		
		g.setColor(Color.red);
		g.drawString(" : "+totalpawn2,arr[31][0]+200,110);
		
		
		
		if(team2Move==false) g.fillOval(arr[11][0]+40,60,25,25);
		
		if(team1Move==false) g.fillOval(arr[16][0]+40,60,25,25);
		
		
		if(click==true) {
			Graphics2D g2d=(Graphics2D) g;
			g2d.setStroke(new BasicStroke(6));
			g.drawOval(A-18,B-18,40,40);
		}
		
		
		
		
		
		
		//game stop
		
		if(totalpawn1<=0) {
			
			
			
			BufferedReader bf;
			BufferedWriter bw;
			
			
			
			try {
				
				bf =new BufferedReader( new FileReader(new File("score.txt")));
				
				
				String str;
				str=bf.readLine();
				
				if( str==null) {
					
					
					System.out.println("nulllllllllllll");
					
						bw=new BufferedWriter(new FileWriter(new File("score.txt")));
						bw.write(team1+","+totalpawn1+","+time);
						bw.close();
						
					
						
					
				}
				else {
					
					
					String[] s=str.split(",");
					int x=Integer.parseInt(s[2]);
					
					System.out.println(team1+" present "+ time);
					
					if(x>time){
						bw=new BufferedWriter(new FileWriter(new File("score.txt")));
						bw.write(team1+","+totalpawn1+","+time);
						bw.close();
						
					}
						
						
						
					
				}
				
				
				
				
				
				bf.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//mainFrame.setVisible(false);
			
			JFrame frame1 = new JFrame();
			frame1.setBounds(600, 200, 500,380);
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setResizable(false);
			frame1.setVisible(true);
			
			
			Message message = null;
			try {
				message = new Message(mainFrame,frame1,"Sry try again!",totalpawn2,minutes,seconds);
			} catch (IOException l) {
				// TODO Auto-generated catch block
				l.printStackTrace();
			}
			
			frame1.add(message);
			
			
			
			
			
			playGame=false;
			
		}
		
		
		
		if(totalpawn2<=0) {
			
			
			
			
			
			
			BufferedReader bf;
			BufferedWriter bw;
			
			
			
			
				

				try {
					
					bf =new BufferedReader( new FileReader(new File("score.txt")));
					
					
					String str;
					str=bf.readLine();
					
					if( str==null) {
						
						
						System.out.println("nulllllllllllll");
						
							bw=new BufferedWriter(new FileWriter(new File("score.txt")));
							bw.write(team1+","+totalpawn1+","+time);
							bw.close();
							
						
							
						
					}
					else {
						
						
						String[] s=str.split(",");
						int x=Integer.parseInt(s[2]);
						
						System.out.println(team1+" present "+ time);
						
						if(x>time){
							bw=new BufferedWriter(new FileWriter(new File("score.txt")));
							bw.write(team1+","+totalpawn1+","+time);
							bw.close();
							
						}
							
							
							
						
					}
					
					
					
					
					
					bf.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
			//mainFrame.setVisible(false);
			
			JFrame frame1 = new JFrame();
			frame1.setBounds(600, 200, 500,380);
			frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setResizable(false);
			frame1.setVisible(true);
			
			
			Message message = null;
			try {
				message = new Message(mainFrame,frame1,team1,totalpawn1,minutes,seconds);
			} catch (IOException l) {
				// TODO Auto-generated catch block
				l.printStackTrace();
			}
			
			frame1.add(message);
			
			
			
			playGame=false;
			
		}
		
		
		
		
		
		//timer
		
		 time+=7;
		 hours=(int)((time/1000)/3600);
         minutes=(int)((time/1000)/60);
         seconds=(int)((time/1000)%60);

        timerCount=String.format("%02d:%02d:%02d\n",hours,minutes,seconds);
        
        g.setColor(Color.red);
        g.setFont(new Font("serif",Font.BOLD,45));
        g.drawString(timerCount,1250,70);
		
		
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		click=true;
		
		
		
		//select
		if(SwingUtilities.isLeftMouseButton(e)) {
			
				int check;
				int x1=e.getX();
				int y1=e.getY();
				for(int i=0;i<37;i++) {
					
					if((x1>=(arr[i][0]-40)&&x1<=arr[i][0]+40)&&((y1>=(arr[i][1]-40)&&y1<=arr[i][1]+40))&&arr[i][2]==1) {
						
						
						mouseTemp=i;
						
						
						
						/*team2Move=true;
						team1Move=false;
						
						for(int j=0;j<8;j++) {
						
								check=path[mouseTemp][j];
								if(check!=-1&&path[check][j]!=-1) {
								
										if((arr[check][2]==2&&arr[mouseTemp][2]==1&&arr[path[check][j]][2]==0)) {
									
											team1Move=true;
										}
								}
						}*/
								
						A=arr[mouseTemp][0];
						B=arr[mouseTemp][1];
						mouse=true;
						System.out.println(mouse);
						
						  sound.makeSound();
						
					}
					if((x1>=(arr[i][0]-40)&&x1<=arr[i][0]+40)&&((y1>=(arr[i][1]-40)&&y1<=arr[i][1]+40))&&arr[i][2]==2) {
						
						mouseTemp=i;
						
						
						/*team1Move=true;
						team2Move=false;
						
						for(int j=0;j<8;j++) {
								
								check=path[mouseTemp][j];
								if(check!=-1&&path[check][j]!=-1) {
								
										if((arr[check][2]==1&&arr[mouseTemp][2]==2&&arr[path[check][j]][2]==0)) {
										
											team2Move=true;
										}
								}
						}*/
						
						//A=arr[mouseTemp][0];
						//B=arr[mouseTemp][1];
						mouse=true;
						System.out.println(mouse);
						
						
						  
						
					}
					
					
					
				}
			
			
			
		}
		
		
		//move
		
		if(SwingUtilities.isLeftMouseButton(e)&&mouse==true) {
				
			
				
				int x2=e.getX();
				int y2=e.getY();
				
				for(int i=0;i<8;i++) {
					
					
					if(path[mouseTemp][i]!=-1) {
						
							if((x2>=(arr[path[mouseTemp][i]][0]-40)&&x2<=arr[path[mouseTemp][i]][0]+40)&&((y2>=(arr[path[mouseTemp][i]][1]-40)&&y2<=arr[path[mouseTemp][i]][1]+40))
								&&(arr[path[mouseTemp][i]][2]==0&&arr[mouseTemp][2]==1)&&team1Move==true) {
								
								
									if(arr[mouseTemp][2]==1) {
										
										arr[path[mouseTemp][i]][2]=1;
										A=arr[path[mouseTemp][i]][0];
										B=arr[path[mouseTemp][i]][1];
										
										
										
										Atflag=true;
										
									}
									
									
									arr[mouseTemp][2]=0;
									
									//mouse1=true;
									team1Move=false;
									team2Move=true;
									
									mouse=false;
									
									
									Atflag=true;
									
									  sound.makeSound();
									
							}
							
							
							if((x2>=(arr[path[mouseTemp][i]][0]-40)&&x2<=arr[path[mouseTemp][i]][0]+40)&&((y2>=(arr[path[mouseTemp][i]][1]-40)&&y2<=arr[path[mouseTemp][i]][1]+40))
								&&(arr[path[mouseTemp][i]][2]==0&&arr[mouseTemp][2]==2)&&team2Move==true) {
								
								
									
									if(arr[mouseTemp][2]==2) {
										
										Atflag=true;
										
										arr[path[mouseTemp][i]][2]=2;
										//A=arr[path[mouseTemp][i]][0];
										//B=arr[path[mouseTemp][i]][1];
										
								
									}
									
									arr[mouseTemp][2]=0;
									
									//mouse1=true;
									team1Move=true;
									team2Move=false;
									mouse=false;
									System.out.println(mouse);
									
									 
									
							}
							
							
							
							
							
							
							int next=path[mouseTemp][i];
							if(path[next][i]!=-1) {
									
									if((x2>=(arr[path[next][i]][0]-40)&&x2<=arr[path[next][i]][0]+40)&&((y2>=(arr[path[next][i]][1]-40)&&y2<=arr[path[next][i]][1]+40))
										&&((arr[next][2]==2&&arr[mouseTemp][2]==1&&arr[path[next][i]][2]==0))) {
										
										//System.out.println("mouseTemp:"+mouseTemp+"  "+"next:"+next+" "+"next2:"+path[next][i]);
										if(arr[mouseTemp][2]==1) {
											totalpawn2--;
											arr[path[next][i]][2]=1;
											A=arr[path[next][i]][0];
											B=arr[path[next][i]][1];
											
											int tmp=path[next][i];
											
											Atflag=true;
											
											for(int t=0;t<8;t++) {
												
												if(path[tmp][t]!=-1) {
													int tmp2=path[tmp][t];
													if(path[tmp2][t]!=-1) {
														if(arr[path[tmp][t]][2]==2&&arr[tmp][2]==1&&arr[path[tmp2][t]][2]==0) {
															
															Atflag=false;
															
														}
													}
												}
												
												
											}
											
											
											
										}
										
										arr[mouseTemp][2]=0;
										arr[next][2]=0;
										
										team1Move=false;
										team2Move=true;
										mouse=false;
										System.out.println("hello azaz");
										
										
										  sound.makeSound();

										
									}
									
									
									
									 if((x2>=(arr[path[next][i]][0]-40)&&x2<=arr[path[next][i]][0]+40)&&((y2>=(arr[path[next][i]][1]-40)&&y2<=arr[path[next][i]][1]+40))
										&&((arr[next][2]==1&&arr[mouseTemp][2]==2&&arr[path[next][i]][2]==0))) {
											
											//System.out.println("mouseTemp:"+mouseTemp+"  "+"next:"+next+" "+"next2:"+path[next][i]);
											
											if(arr[mouseTemp][2]==2) {
												totalpawn1--;
												arr[path[next][i]][2]=2;
												
												//A=arr[path[next][i]][0];
												//B=arr[path[next][i]][1];
												
												Atflag=true;
										
											}
											arr[mouseTemp][2]=0;
											arr[next][2]=0;
											
											team2Move=false;
											team1Move=true;
										
											mouse=false;
											System.out.println("hello azaz");
											
											  

											
									}
								
									
									
									
									
									
							}
							
							
					}
					
						
			}
				
		}
		
		
		
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//repaint();
		
		if(Atflag==true) {
			
			ploy1=true;
			ploy2=true;
			
			
			
			
			int count1=0,count2=0,count3=0,count4=0,count5=0,count6=0,count7=0,count8=0;
			
			
			ArrayList<Integer> bestWay[]=new ArrayList[37];
			int[] counter=new int[37];
			
			int[] checkCounter=new int[37];
			
			
			
			
			
			for(int t=0;t<37;t++) {
				
				bestWay[t]=new ArrayList<Integer>();
				
			}
			
			
			if(ploy1==true) {
					for(int i=0;i<37;i++) {
						
						
						if(arr[i][2]==2) {
						
							
						for(int j=0;j<8;j++) {
							
							int[][] Altarr=new int[37][3];
							
							int s=1,temp=0,tempX1=0,tempX2=0,tempX3,lastPosition=-1;
							
							for(int m=0;m<Altarr.length;m++) {
								
								for(int n=0;n<Altarr[0].length;n++) {
									
									Altarr[m][n]=arr[m][n];
									
									
								}
								
							}
							
							tempX1=i;
							
							temp=path[i][j];
							tempX2=temp;
							
							
							if(temp!=-1&&path[temp][j]!=-1) {
							if(path[i][j]!=-1&&Altarr[path[i][j]][2]==1&&Altarr[path[temp][j]][2]==0) {
								
								
								
								 
								 BestWay bestway=new BestWay(tempX1,counter,bestWay,checkCounter);
								
								 bestway.findBestWay(tempX1,Altarr,path); 
								 
								 break;
								 
								 
							}
							
							}
							
						}
								 
								 
						}
					}
					
						
						for(int t=0;t<37;t++) {
					
							System.out.print(checkCounter[t]);
						}
						
						
						 System.out.println(" eat up  eat up up eat  counter in AI:");
						
								int max=-100,index=0;
								 for(int t=0;t<37;t++) {
								
									 System.out.print(counter[t]);
									 
									
									 
									 if(checkCounter[t]>=max) {
										 
										 max=checkCounter[t];
										 index=t;
									 }
								
								 }
								
								 
								 System.out.println();
								 
								 if(max>=0) {
									 	
									 
									 	max=counter[index];
										 drawLine=true;
										 if(counter1<=250) {
											 
											 counter1++;
											 
											 System.out.println("best way array size:"+bestWay[index].size());
											 for(int t=0;t<bestWay[index].size();t+=3) {
											
												
												ArrayList<Integer> multiLineXY=new  ArrayList<Integer>();
												int sour=bestWay[index].get(t);
												int dest=bestWay[index].get(t+2);
												
												multiLineXY.add(arr[sour][0]);
												multiLineXY.add(arr[sour][1]);
												
												
												multiLineXY.add(arr[dest][0]);
												multiLineXY.add(arr[dest][1]);
												
												
												multiLineDraw.add(multiLineXY);
												
												ploy1=true;
												ploy2=false;
												
												
												
											}
											
											
										}
										 
										if(counter1>250) {
											
											counter1=0;
												
											 for(int t=0;t<bestWay[index].size();t+=3) {
													
													
												int sour=bestWay[index].get(t);
												int mid=bestWay[index].get(t+1);
												int dest=bestWay[index].get(t+2);
												
												arr[sour][2]=0;
												arr[mid][2]=0;
												arr[dest][2]=2;
												
												
			
												
												team1Move=true;
												team2Move=false;
												mouse=false;
												
												
												
												Atflag=false;
												drawLine=false;
												
												
												ploy2=false;
												
												A=-100;
												B=-100;
												
												
												
												
												totalpawn1--;
												
												multiLineDraw.removeAll(multiLineDraw);
												
												
												  sound.makeSound();
												
												
											 }		
											 
											 System.out.println("bestWay"+bestWay[index]);
											
												
											
									}
								 }
								 else {
									 
									 if(!multiLineDraw.isEmpty()) {
										 multiLineDraw.removeAll(multiLineDraw);
										 
										 
										 	team1Move=true;
											team2Move=false;
											mouse=false;
											
											
											
											ploy1=false;
											ploy2=true;
											drawLine=false;
											
										 
									 }
									 
								 }
								 
								
								//counter1++;
								//if(counter1>251) counter1=0;
						
						
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								/*System.out.println("new new new "+i);
									
								Atflag=false;
								
								
								
								
								Altarr[tempX1][2]=0;
								Altarr[tempX2][2]=0;
								Altarr[path[temp][j]][2]=2;
								
								temp=path[temp][j];
								tempX1=temp;
								
								lastPosition=tempX1;
								tempX1=i;*/
										
									
									/*if(j==0) {
										
										
										System.out.println("j-->0laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2);
										while(s>0){
											
											
											
											
										
											boolean flag=true;
											for(int k=0;k<8;k++) {
											
												
												temp=tempX1;
											
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
													
														Altarr[tempX1][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
														tempX1=tempX3;
													System.out.println("j-->0 laksksjdjdjjdjfkff tempX2 "+tempX2+ "  tempX3  "+tempX3+" count1:   "+count1);
														count1++;
														
														lastPosition=tempX1;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello--->0:   "+count1);
												bestWay[0][0]=count1;
												bestWay[0][1]=lastPosition;
												
												break;
												
											}
											
											
											
										}
										
									
										
										
										
									}
									else if(j==1) {
										
										
										System.out.println("j-->1  laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2+"  temp "+temp);
										while(s>0){
											
											
											
											
											boolean flag=true;	
											for(int k=0;k<8;k++) {
											
												temp=tempX1;
												
											
												
												if(path[temp][k]!=-1) {
													
													//System.out.println(temp+"   "+path[temp][k]);
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
													
														Altarr[tempX1][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
														tempX1=tempX3;
														
														lastPosition=tempX1;
														
													System.out.println("laksksjdjdjjdjfkff tempX1 "+tempX2+ "  tempX2  "+tempX3+"  temp "+temp+" count2: "+count2);
														count2++;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello  --->1:   "+count2);
												bestWay[1][0]=count2;
												bestWay[1][1]=lastPosition;
												
												break;
												
											}
											
											
											
										}
										
										
									}
									
									else if(j==2) {
										
										System.out.println("j-->2 laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2+"  temp "+temp);
										while(s>0){
											
											
											
											
											boolean flag=true;	
											for(int k=0;k<8;k++) {
											
												temp=tempX1;
												
											
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
													
														Altarr[tempX1][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
														tempX1=tempX3;
													System.out.println("j-->2 laksksjdjdjjdjfkff tempX1 "+tempX2+ "  tempX2  "+tempX3+"  temp "+temp+" count3:  "+count3);
														count3++;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello -->2:   "+count3);
												bestWay[2][0]=count3;
												
												break;
												
											}
											
											
											
										}
										
		}
									
									
									
									else if(j==3) {
										
										System.out.println("j-->3 laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2+"  temp "+temp);
										while(s>0){
											
											
											
											
											boolean flag=true;	
											for(int k=0;k<8;k++) {
											
												temp=tempX1;
												
											
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
													
														Altarr[tempX1][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
														tempX1=tempX3;
													System.out.println("j-->3 laksksjdjdjjdjfkff tempX1 "+tempX2+ "  tempX2  "+tempX3+"  temp "+temp+" count4:   "+count4);
														count4++;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello -->3   "+count4);
												bestWay[3][0]=count4;
												
												break;
												
											}
											
											
											
										}
										
		
										
									}
									else if(j==4) {
										
										System.out.println("j-->4 laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2+"  temp "+temp);
										while(s>0){
											
											
											
											
											boolean flag=true;	
											for(int k=0;k<8;k++) {
											
												temp=tempX1;
												
											
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
													
														Altarr[temp][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
													System.out.println("laksksjdjdjjdjfkff tempX1 "+tempX2+ "  tempX2  "+tempX3+"  temp "+temp+" count5:  "+count5);
														count5++;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello -->4   "+count5);
												
												bestWay[4][0]=count5;
												
												break;
												
											}
											
											
											
										}
										
										
									}
									else if(j==5) {
										
										
										System.out.println("j-->5  laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2+"  temp "+temp);
										while(s>0){
											
											
											
											
										
											boolean flag=true;
											for(int k=0;k<8;k++) {
											
												
											
												temp=tempX1;
												
												if(path[temp][k]!=-1) {
													
													
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														System.out.println(tempX1+"   "+Altarr[tempX2][2]);
														
														Altarr[tempX1][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
														tempX1=tempX3;
													System.out.println("laksksjdjdjjdjfkff tempX1 "+tempX2+"    " +Altarr[tempX2][2]+"   "+ k+ "  tempX2  "+tempX3+"  temp "+temp+" count6:   "+count6);
														count6++;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello --->5 "+count6);
												bestWay[5][0]=count6;
												
												break;
												
											}
											
											
											
										}
										
										
									}
									else if(j==6) {
										
										
										System.out.println("j-->6  laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2+"  temp "+temp);
										while(s>0){
											
											
											
											
										
											boolean flag=true;	
											for(int k=0;k<8;k++) {
												
												temp=tempX1;
												
											
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
													
														Altarr[temp][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
														tempX1=tempX3;
													System.out.println("j-->6  laksksjdjdjjdjfkff tempX1 "+tempX2+ "  tempX2  "+tempX3+"  temp "+temp+" count7:  "+count7);
														count7++;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello -->6  "+count7);
												bestWay[6][0]=count7;
												
												break;
												
											}
											
											
											
										}
										
										
									}
									else if(j==7) {
										
										
										
										System.out.println("j-->7  laksksjdjdjjdjfkff tempX1 "+tempX1+ "  tempX2  "+tempX2+"  temp "+temp);
										while(s>0){
											
											
											
											
										
											boolean flag=true;	
											for(int k=0;k<8;k++) {
											
												temp=tempX1;
											
												
												if(path[temp][k]!=-1) {
													
													
													System.out.println(temp+"   "+path[temp][k]);
													temp=path[temp][k];
													tempX2=temp;
													
												
												if(path[temp][k]!=-1) {
													
													temp=path[temp][k];
													tempX3=temp;
													
												
												
												if(tempX2!=-1&&tempX3!=-1) {
												
													if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
													
														Altarr[tempX1][2]=0;
														Altarr[tempX2][2]=0;
														Altarr[tempX3][2]=2;
														
														temp=tempX3;
														tempX1=tempX3;
													System.out.println("j-->7  laksksjdjdjjdjfkff tempX1 "+tempX2+ "  tempX2  "+tempX3+"  temp "+temp+" count8:   "+count8);
														count8++;
														
														flag=false;
														
													}
													
												}
		
												
												}
												}
												
											}
											
											if(flag==true) {
												
												
												System.out.println("hello     hello -->7  "+count8);
												for(int l=0;l<8;l++) {
														
														if(l<4) {
															
															if(path[tempX1][l]!=-1&&path[tempX1][l+4]!=-1) {
																
																if(Altarr[path[tempX1][l]][2]==1&&Altarr[path[tempX1][l+4]][2]==0||Altarr[path[tempX1][l+4]][2]==1&&Altarr[path[tempX1][l]][2]==0) {
																	
																		
																		
																	
																}
															
															
															
															
															}
														
														
														
														
														}
													
													
												}
												
												
												bestWay[7][0]=count8;
												
												break;
												
											}
											
											
											
										}
										
										
									}*/
									
							/*	counter1++;
								if(counter1>251) counter1=0;
															
									
									
									
									
								}
								
								
								
								
								
							}
							
							
							
						}
						}
					}*/
						
					
					
					
					//eat up guti
					
				/*	int[][] Altarr=new int[37][3];
					
					for(int m=0;m<Altarr.length;m++) {
						
						for(int n=0;n<Altarr[0].length;n++) {
							
							Altarr[m][n]=arr[m][n];
							
							
						}
						
					}
					
					
					
					
						for(int i=0;i<37;i++) {
							
							
							if(arr[i][2]==2) {
							
							for(int j=0;j<8;j++) {
								
								int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
								
								tempX1=i;
								
								temp=path[i][j];
								tempX2=temp;
								
								
								
								
								
								
								
								if(temp!=-1&&path[temp][j]!=-1) {
								if(path[i][j]!=-1&&Altarr[path[i][j]][2]==1&&Altarr[path[temp][j]][2]==0) {
									
									Altarr[tempX1][2]=0;
									Altarr[tempX2][2]=0;
									Altarr[path[temp][j]][2]=2;
									
										
									
									
									ArrayList<Integer> multiLineXY=new  ArrayList<Integer>();
									
									if(counter1==0) {
										
										
										
										
										multiLineXY.add(arr[tempX1][0]);
										multiLineXY.add(arr[tempX1][1]);
										
										
										multiLineXY.add(arr[path[temp][j]][0]);
										multiLineXY.add(arr[path[temp][j]][1]);
										
										
										multiLineDraw.add(multiLineXY);
										
										
										
									}
									
									drawLine=true;
									
									repaint();
									
									
									
									
									ploy1=false;
									
								
									
									
									if(counter1>250) {
											
								
											arr[tempX1][2]=0;
											arr[tempX2][2]=0;
											arr[path[temp][j]][2]=2;
											
											
		
											
											team1Move=true;
											team2Move=false;
											mouse=false;
											
											
											
											Atflag=false;
											drawLine=false;
											
											multiLineXY.removeAll(multiLineXY);
											multiLineDraw.removeAll(multiLineDraw);
											
											
											
										
									}	
								
									
									temp=path[temp][j];
									tempX1=temp;
										
										if(j==0) {
											
											
											
											while(s>0){
												
												
												
												
												
											
												boolean flag=true;
												for(int k=0;k<8;k++) {
												
													
													temp=tempX1;
												
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
													
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
																
																
																
																
																
																
																
															}
															
															
															
															
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															
															
															
															temp=tempX3;
															tempX1=tempX3;
													
															
															
															
															
															flag=false;
															
															
														}
														
													}
		
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
													
													
													break;
													
												}
												
												
												
											}
											
										
											
											
											
										}
										else if(j==1) {
											
											
											
											while(s>0){
												
												
												
												
												boolean flag=true;	
												for(int k=0;k<8;k++) {
												
													temp=tempX1;
													
												
													
													if(path[temp][k]!=-1) {
														
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
													
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
															
															}
															
															repaint();
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															
															temp=tempX3;
															tempX1=tempX3;
														
															
															
															flag=false;
															
														}
														
													}
		
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
													
													
													break;
													
												}
												
												
												
											}
											
											
										}
										
										else if(j==2) {
											
											
											while(s>0){
												
												
												
												
												boolean flag=true;	
												for(int k=0;k<8;k++) {
												
													temp=tempX1;
													
												
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
													
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
															
															}
															
															
															
															repaint();
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															
															temp=tempX3;
															tempX1=tempX3;
														
															
															
															flag=false;
															
														}
														
													}
		
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
													
													
													break;
													
												}
												
												
												
											}
											
			}
										
										
										
										else if(j==3) {
											
											
											while(s>0){
												
												
												
												
												boolean flag=true;	
												for(int k=0;k<8;k++) {
												
													temp=tempX1;
													
												
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
													
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
															
															}
															
															
															
															repaint();
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															
															temp=tempX3;
															tempX1=tempX3;
														
															
															
															flag=false;
															
														}
														
													}
		
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
													
													
													break;
													
												}
												
												
												
											}
											
		
											
										}
										else if(j==4) {
											
											
											while(s>0){
												
												
												
												
												boolean flag=true;	
												for(int k=0;k<8;k++) {
												
													temp=tempX1;
													
												
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
													
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
															
															}
															
													
															
															repaint();
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															
															
															temp=tempX3;
														
															
															
															flag=false;
															
														}
														
													}
		
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
													
													
													
													break;
													
												}
												
												
												
											}
											
											
										}
										else if(j==5) {
											
											
											
											while(s>0){
												
												
												
												
											
												boolean flag=true;
												for(int k=0;k<8;k++) {
												
													
												
													temp=tempX1;
													
													if(path[temp][k]!=-1) {
														
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
													
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
															
															}
															
															
															
															repaint();
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															temp=tempX3;
															tempX1=tempX3;
														
															
															
															flag=false;
															
														}
														
													}
		
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
												
													
													break;
													
												}
												
												
												
											}
											
											
										}
										else if(j==6) {
											
											
											
											while(s>0){
												
												
												
												
											
												boolean flag=true;	
												for(int k=0;k<8;k++) {
													
													temp=tempX1;
													
												
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
														
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
															
															}
															
														
															
															repaint();
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															
															temp=tempX3;
															tempX1=tempX3;
													
															
															
															flag=false;
															
														}
														
													}
		
													
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
													
													
													break;
													
												}
												
												
												
											}
											
											
										}
										else if(j==7) {
											
											
											
											
											while(s>0){
												
												
												
												
											
												boolean flag=true;	
												for(int k=0;k<8;k++) {
												
													temp=tempX1;
												
													
													if(path[temp][k]!=-1) {
														
														
														
														temp=path[temp][k];
														tempX2=temp;
														
													
													if(path[temp][k]!=-1) {
														
														temp=path[temp][k];
														tempX3=temp;
														
													
													
													if(tempX2!=-1&&tempX3!=-1) {
													
														if(Altarr[tempX2][2]==1&&Altarr[tempX3][2]==0) {
														
															
															Altarr[tempX1][2]=0;
															Altarr[tempX2][2]=0;
															Altarr[tempX3][2]=2;
															
															
															ArrayList<Integer> multiLineXY1=new  ArrayList<Integer>();
															
															if(counter1==0) {
																
																multiLineXY1.add(arr[tempX1][0]);
																multiLineXY1.add(arr[tempX1][1]);
																
																
																multiLineXY1.add(arr[tempX3][0]);
																multiLineXY1.add(arr[tempX3][1]);
																
																multiLineDraw.add(multiLineXY1);
															
															}
															
														
															
															repaint();
															
															if(counter1>250) {
																
																arr[tempX1][2]=0;
																arr[tempX2][2]=0;
																arr[tempX3][2]=2;
																
																
																team1Move=true;
																team2Move=false;
																mouse=false;
																
																
																
																Atflag=false;
																drawLine=false;
																
																multiLineXY1.removeAll(multiLineXY1);
																multiLineDraw.removeAll(multiLineDraw);
																
																
															}
															temp=tempX3;
															tempX1=tempX3;
														
															
															
															flag=false;
															
														}
														
													}
		
													
													}
													}
													
												}
												
												if(flag==true) {
													
													
													
													
													
													break;
													
												}
												
												
												
											}
											
										}
										
										
										counter1++;
										if(counter1>251) counter1=0;
										
										
										
										
																	
										
										
										
										
									}
									
									
									
									
									
								}
								
								
								
							}
							}
						}*/
						
						
						
						
			}		
						
						
						
						
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			if(ploy2==true) {	
				
				
				System.out.println(counter2+" end sour"+apprehendSource+"dest"+apprehendDestination);
				
				
				
			
				boolean saveflag=true,safePlaceFlag=true,hotspotPointflag=true,otherflag=true;
				
				
				//making object
				BestPawn bestpawn=new BestPawn(arr,path);
				
				MakeFool mf=new MakeFool(arr,path);
				
				Apprehend aprrehend=new Apprehend(arr,path);
				
				
				//SafePosition safeposition=new SafePosition(arr,path);
				
				
				if(bestpawn.saveGuti(counter2)) {
					
					
					counter2++;
					
					drawLine2=true;
					if(counter2<150) {
						
						AX=bestpawn.AX;
						AY=bestpawn.AY;
						BX=bestpawn.BX;
						BY=bestpawn.BY;
					}
					
					if(counter2>151) {
						
						counter2=0;
						
						Atflag=false;
						ploy1=false;
						
						team1Move=true;
						team2Move=false;
						mouse=false;
						
						drawLine2=false;
						
			
						
						System.out.println("helloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooAZAZ");
					}
					
					
					
					
					
				}
				
				/*if(safePlaceFlag==true) {
					
								
							
									
						boolean flagForNextCheck=true;		
									
						B:for(int i=0;i<74;i++) {
										
											
										
										
										int element=queue.poll();
										if(arr[element][2]==2) {
										
										for(int j=0;j<8;j++) {
											
											int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
											
											tempX1=element;
											
											temp=path[element][j];
											tempX2=temp;
											
											
											if(temp!=-1&&path[temp][j]==-1) {
											if(path[element][j]!=-1&&arr[path[element][j]][2]==0) {
												
												System.out.println("element"+element);
												if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {
												
														System.out.println("88888"+counter2);
														
														counter2++;
														
														drawLine2=true;
														
														if(counter2==1) {
															
															AX=arr[tempX1][0];
															AY=arr[tempX1][1];
															BX=arr[tempX2][0];
															BY=arr[tempX2][1];
															
															tmp1=tempX1;
															tmp2=tempX2;
															
															
															
															System.out.println(tmp1+" "+tmp2+"azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
															
														}
														if(counter2>151) {
															
															
															counter2=0;
															
															Atflag=false;
															ploy1=false;
														
															team1Move=true;
															team2Move=false;
															mouse=false;
															
															drawLine2=false;
														
															arr[tmp1][2]=0;
															arr[tmp2][2]=2;
														
															System.out.println(tmp1+" "+"azzzzzzzzzzzzzzzzzzzzz2");
														}
														
														hotspotPointflag=false;
														otherflag=false;
														
														flagForNextCheck=false;
														
														
														queue.add(element);
														
														
												
												
														break B;
												}
												
											}
											}
											
										}
										
										}
										queue.add(element);
										
										
										
									
										
							}
								
									
											
								
								
								
								
								
								
								
								
						if(flagForNextCheck==true) {	
								
								B:for(int i=0;i<74;i++) {
									
								
									
									
									
									int element=queue.poll();
									if(arr[element][2]==2) {
									
									for(int j=0;j<8;j++) {
										
										int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
										
										tempX1=element;
										
										temp=path[element][j];
										tempX2=temp;
										
										
										
										if(temp!=-1&&path[temp][j]!=-1) {
										if(path[element][j]!=-1&&arr[path[element][j]][2]==0&&arr[path[temp][j]][2]!=1) {
											
											
											System.out.println("mmmmmmmmmmm"+element);
											if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {
													
												
													
													counter2++;
													
													drawLine2=true;
													if(counter2==1) {
														
														AX=arr[tempX1][0];
														AY=arr[tempX1][1];
														BX=arr[tempX2][0];
														BY=arr[tempX2][1];
														
														tmp1=tempX1;
														tmp2=tempX2;
														
														
														System.out.println(tmp1+" "+tmp2+"subbbbbbbbbbbbooooooooooooooooooooooooooooooooooooooo");
														
													}
													if(counter2>151) {
														
														
														counter2=0;
														
														Atflag=false;
														ploy1=false;
													
														team1Move=true;
														team2Move=false;
														mouse=false;
														
														drawLine2=false;
													
														arr[tmp1][2]=0;
														arr[tmp2][2]=2;
													
														System.out.println(tmp1+" "+tmp2+"suboooooooooooooooo2");
													}
													
													
													
													hotspotPointflag=false;
													otherflag=false;
													
													
													
													
													
													System.out.println("2222222");
													
													
													queue.add(element);
													
													break B;
													
											}
											
										}
											
										}
										
										
									}
									
									}
									queue.add(element);
								
								
									
								
								}
						}	
							
						System.out.println("flagForNextCheck"+flagForNextCheck+counter2);
								
								
								
								
							
						
				}*/
				
			
				
				
				
				
				
				else if((totalpawn2-totalpawn1)>=2&&aprrehend.toCatch(counter3)) {
					
					
					drawLine2=true;
					
					counter3++;
					
					System.out.println(counter2 +"       AI sour"+apprehendSource+"AI dest"+apprehendDestination);
					
					if(counter3==1) {
						
						AX=arr[aprrehend.source][0];
						AY=arr[aprrehend.source][1];
						BX=arr[aprrehend.destination][0];
						BY=arr[aprrehend.destination][1];
						
						apprehendSource=aprrehend.source;
						apprehendDestination=aprrehend.destination;
						
						
						
						
					}
					
					if(counter3>150) {
						
						
						
						System.out.println(" end sour"+apprehendSource+"dest"+apprehendDestination);
						
						arr[apprehendSource][2]=0;
						arr[apprehendDestination][2]=2;
						
						
						
						counter3=0;
						
						Atflag=false;
						ploy1=false;
						
						team1Move=true;
						team2Move=false;
						mouse=false;
						
						drawLine2=false;
						
					
						
						System.out.println("ApprehendAZAZ");
						
						  sound.makeSound();
						
					}
					
					
				}
				
				
				
				else if(bestpawn.checkHotspotPosition(counter2)) {
					
					
					System.out.println("hotspotshhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
					System.out.println(" end sour"+apprehendSource+"dest"+apprehendDestination);
					
					drawLine2=true;
					
					counter2++;
					
					if(counter2<150) {
						
						AX=bestpawn.AX;
						AY=bestpawn.AY;
						BX=bestpawn.BX;
						BY=bestpawn.BY;
					}
					
					if(counter2>151) {
						
						counter2=0;
						
						Atflag=false;
						ploy1=false;
						
						team1Move=true;
						team2Move=false;
						mouse=false;
						
						drawLine2=false;
						
					
						
						System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiAZAZ");
						
						  sound.makeSound();
						
					}
					
					
					otherflag=false;
			
			
			
				}
				
				
				
				
				else if(mf.makeFool()) {
					
					drawLine2=true;
					
					counter2++;
					
					if(counter2<150) {
						
						AX=arr[mf.source][0];
						AY=arr[mf.source][1];
						BX=arr[mf.destination][0];
						BY=arr[mf.destination][1];
					}
					
					if(counter2>151) {
						
						
						arr[mf.source][2]=0;
						arr[mf.destination][2]=2;
						
						
						
						counter2=0;
						
						Atflag=false;
						ploy1=false;
						
						team1Move=true;
						team2Move=false;
						mouse=false;
						
						drawLine2=false;
						
					
						
						System.out.println("make fool  hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiAZAZ");
						
						  sound.makeSound();
						
					}
					
				}
				
				
				
				
				
				
				
				
				
				
		
					
				
				
				
				
				else {
					
					
					System.out.println("othersssss");
					
					int o;
					
					boolean flagForNextCheck=true,flagForNextCheck2=true,flagForNextCheck3=true;
					
					B:for(int i=0;i<74;i++) {
						
						
					
					
					
							int element=queue.poll();
							if(arr[element][2]==2) {
								
								if((element==16&&(arr[12][2]==1||arr[22][2]==1))||(element==20&&(arr[14][2]==1||arr[24][2]==1))||(element==6&&(arr[7][2]==1||arr[11][2]==1||arr[12][2]==1))||(element==26&&(arr[21][2]==1||arr[27][2]==1||arr[22][2]==1))
										||(element==30&&(arr[29][2]==1||arr[25][2]==1||arr[24][2]==1))||(element==10&&(arr[9][2]==1||arr[14][2]==1||arr[15][2]==1))||((element==10||element==30)&&(arr[20][2]==1))||((element==6||element==26)&&(arr[16][2]==1))
										||(element==8&&(arr[6][2]==1||arr[10][2]==1))||(element==18&&(arr[26][2]==1||arr[30][2]==1))||(element==16&&(arr[10][2]==1||arr[30][2]==1))||(element==20&&(arr[6][2]==1||arr[26][2]==1))) {
									
									
									System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd1111111111111111111111111");
									queue.add(element);
									continue;
								}
								
									
								
							
							for(int j=0;j<8;j++) {
								
								int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
								
								tempX1=element;
								
								temp=path[element][j];
								tempX2=temp;
								
								
								if(temp!=-1&&path[temp][j]!=-1) {
								if(path[element][j]!=-1&&arr[path[element][j]][2]==0&&arr[path[temp][j]][2]!=1) {
									
									if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {
											
											
											counter2++;
											
											drawLine2=true;
											if(counter2==1) {
												
												AX=arr[tempX1][0];
												AY=arr[tempX1][1];
												BX=arr[tempX2][0];
												BY=arr[tempX2][1];
												
												tmp1=tempX1;
												tmp2=tempX2;
												
												
												System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww1");
												
											}
											if(counter2>150) {
												
												
												counter2=0;
												
												Atflag=false;
												ploy1=false;
											
												team1Move=true;
												team2Move=false;
												mouse=false;
												
												drawLine2=false;
											
												arr[tmp1][2]=0;
												arr[tmp2][2]=2;
												
											
											
												System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwww2");
												
												  sound.makeSound();
												
											}
											
											flagForNextCheck=false;
											
											System.out.println("2222222");
											
											
											queue.add(element);
											
											break B;
											
									}
									
									
									
								}
								}
								
							}
							
						}
						queue.add(element);
							
							
						
							
					}
						
				
				
			System.out.println("counter"+counter2+"  "+flagForNextCheck);
			
			
			
				
			if(flagForNextCheck==true) {
				
		
				
				B:for(int i=0;i<74;i++) {
					
						
					
					
					int element=queue.poll();
					if(arr[element][2]==2) {
						
						if((element==16&&(arr[12][2]==1||arr[22][2]==1))||(element==20&&(arr[14][2]==1||arr[24][2]==1))||(element==6&&(arr[7][2]==1||arr[11][2]==1||arr[12][2]==1))||(element==26&&(arr[21][2]==1||arr[27][2]==1||arr[22][2]==1))
								||(element==30&&(arr[29][2]==1||arr[25][2]==1||arr[24][2]==1))||(element==10&&(arr[9][2]==1||arr[14][2]==1||arr[15][2]==1))||((element==10||element==30)&&(arr[20][2]==1))||((element==6||element==26)&&(arr[16][2]==1))
								||(element==8&&(arr[6][2]==1||arr[10][2]==1))||(element==18&&(arr[26][2]==1||arr[30][2]==1))||(element==16&&(arr[10][2]==1||arr[30][2]==1))||(element==20&&(arr[6][2]==1||arr[26][2]==1))) {
							
							
							System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd222222222222222222222");
							queue.add(element);
							continue;
						}
					
					for(int j=0;j<8;j++) {
						
						int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
						
						tempX1=element;
						
						temp=path[element][j];
						tempX2=temp;
						
						if(temp!=-1&&path[temp][j]==-1) {
							if(path[element][j]!=-1&&arr[path[element][j]][2]==0) {
								
								if(!bestpawn.checkDangerPosition(temp)&&!bestpawn.checkDangerTomove(tempX1)) {
								
						
						
						
						
									System.out.println("444444");
											
											counter2++;
											
											drawLine2=true;
											
											if(counter2==1) {
												
												AX=arr[tempX1][0];
												AY=arr[tempX1][1];
												BX=arr[tempX2][0];
												BY=arr[tempX2][1];
												
												tmp1=tempX1;
												tmp2=tempX2;
												
												
												
												System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww3");
												
											}
											if(counter2>150) {
												
												
												counter2=0;
												
												Atflag=false;
												ploy1=false;
											
												team1Move=true;
												team2Move=false;
												mouse=false;
											
												
												drawLine2=false;
											
												arr[tmp1][2]=0;
												arr[tmp2][2]=2;
												
												
											
												System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwww4");
												  sound.makeSound();
												
											}
											
									
									
											queue.add(element);
											
											flagForNextCheck2=false;
									
									
											break B;
							}
							
						}
						}
						
					}
					
					}
					queue.add(element);
					
					
					
				
					
				}
			
				
						
			}
			
			
			if(flagForNextCheck2==true&&flagForNextCheck==true) {
				
			
				
				
				B:for(int i=0;i<74;i++) {
					
						
					
					
					int element=queue.poll();
					if(arr[element][2]==2) {
						
						if((element==16&&(arr[12][2]==1||arr[22][2]==1))||(element==20&&(arr[14][2]==1||arr[24][2]==1))||(element==6&&(arr[7][2]==1||arr[11][2]==1||arr[12][2]==1))||(element==26&&(arr[21][2]==1||arr[27][2]==1||arr[22][2]==1))
								||(element==30&&(arr[29][2]==1||arr[25][2]==1||arr[24][2]==1))||(element==10&&(arr[9][2]==1||arr[14][2]==1||arr[15][2]==1))||((element==10||element==30)&&(arr[20][2]==1))||((element==6||element==26)&&(arr[16][2]==1))
								||(element==8&&(arr[6][2]==1||arr[10][2]==1))||(element==18&&(arr[26][2]==1||arr[30][2]==1))||(element==16&&(arr[10][2]==1||arr[30][2]==1))||(element==20&&(arr[6][2]==1||arr[26][2]==1))) {
							
							
							System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzd33333333333");
							queue.add(element);
							continue;
						}
					
					for(int j=0;j<8;j++) {
						
						int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
						
						tempX1=element;
						
						temp=path[element][j];
						tempX2=temp;
						
						
						if(temp!=-1&&path[temp][j]!=-1) {
							if(path[element][j]!=-1&&arr[path[element][j]][2]==0&&arr[path[temp][j]][2]!=1) {
								
								if(!bestpawn.checkDangerTomove(element)) {
								
							
									System.out.println("666666");
									
									counter2++;
									
									drawLine2=true;
									
									if(counter2==1) {
										
										AX=arr[tempX1][0];
										AY=arr[tempX1][1];
										BX=arr[tempX2][0];
										BY=arr[tempX2][1];
										
										tmp1=tempX1;
										tmp2=tempX2;
										
										
										
										System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww5");
										
									}
									if(counter2>150) {
										
										
										counter2=0;
										
										Atflag=false;
										ploy1=false;
									
										team1Move=true;
										team2Move=false;
										mouse=false;
										
										drawLine2=false;
									
										arr[tmp1][2]=0;
										arr[tmp2][2]=2;
										
									
									
										System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwww6");
										
										  sound.makeSound();
										
									}
									
							
							
									queue.add(element);
									
									flagForNextCheck3=false;
							
							
									break B;
							}
							
						}
						}
						
					}
					
					}
					queue.add(element);
					
					
					
				
					
				}
			
				
						
			}
					
			
			
			if(flagForNextCheck2==true&&flagForNextCheck==true&&flagForNextCheck3==true) {
				
				
				
				B:for(int i=0;i<74;i++) {
					
						
					
					
					int element=queue.poll();
					if(arr[element][2]==2) {
					
					for(int j=0;j<8;j++) {
						
						int s=1,temp=0,tempX1=0,tempX2=0,tempX3;
						
						tempX1=element;
						
						temp=path[element][j];
						tempX2=temp;
						
						
						if(temp!=-1&&path[temp][j]!=-1) {
						if(path[element][j]!=-1&&arr[path[element][j]][2]==0) {
							
							
							System.out.println("other"+"88888");
									
									counter2++;
									
									drawLine2=true;
									
									if(counter2==1) {
										
										AX=arr[tempX1][0];
										AY=arr[tempX1][1];
										BX=arr[tempX2][0];
										BY=arr[tempX2][1];
										
										tmp1=tempX1;
										tmp2=tempX2;
										
										
										
										System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww7");
										
									}
									if(counter2>150) {
										
										
										counter2=0;
										
										Atflag=false;
										ploy1=false;
									
										team1Move=true;
										team2Move=false;
										mouse=false;
										
										drawLine2=false;
									
										arr[tmp1][2]=0;
										arr[tmp2][2]=2;
										
									
										System.out.println(tmp1+" "+tmp2+"wowwwwwwwwwwwwwwwwwwwwwww8");
										
										
										  sound.makeSound();
										
									}
									
							
							
									queue.add(element);
									
									
							
							
									break B;
							
						}
						}
						
					}
					
					}
					queue.add(element);
					
					
					
				
					
				}
			
				
						
			}
					
			
			
			
			
			
			
			
			
			
			
			
					
					
					
			
				
				
				
			}	
				
			
				
				
				
				
				
				
			}	
			
				
			
			
		
		}
		
			
			
		if(playGame==true) repaint();


			
		
	
	}
}


