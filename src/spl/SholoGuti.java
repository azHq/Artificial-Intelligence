package spl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
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
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class SholoGuti extends JPanel implements MouseListener,ActionListener {
	
	private int[][] arr= new int[37][3];
	
	private int[][] position=new int[37][3];
	private int x=940,y=485;  //940,485,
	private Timer timer;
	private int delay=16;
	private int f=1,g,h=1,a;
	private String team1="azaz",team2="bablu";
	private int totalpawn1=16,totalpawn2=16;
	
	public boolean Atflag=false;
	
	public boolean playGame=true;
	
	public int temp;
	
	
	int[][] path=new int[37][8];
	
	int[][] bestWay=new int[8][2];
	int[][] firstTwoMax=new int[2][2];
	
	private boolean mouse,team1Move=true,team2Move=true;
	int mouseClickCount=0;
	
	private int mouseTemp;

	
	

	ArrayList<Integer> arraylist=new  ArrayList<Integer>();
	
	
	public int height;
	public int width;
	
	public int eachRoomHeight,eachRoomWidth;
	
	private int A=80,B=245;
	
	boolean click=false;
	
	public SholoGuti(JFrame frame) {
		
		
		
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
				azaz az=new azaz();
				
			}
			
		});
		
		
		
		UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 18));
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font( "Arial", Font.BOLD, 18)));       
		UIManager.put("OptionPane.minimumSize",new Dimension(500,300));
		
		team1=JOptionPane.showInputDialog("Enter First Team Name:");
		team2=JOptionPane.showInputDialog("Enter Second Team Name:");
		
		
		if(team1.length()==0) {
			
			team1="Abul";
			
		}
		
		if(team2.length()==0) {
			
			team2="Kabul";
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
					
					if(count>=0&&count<=15) position[count][2]=1;
					else if(count>=21&&count<=36) position[count][2]=2;
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
		
		
		
	if(totalpawn1<=0) {
			
			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif",Font.BOLD,45));
			g.drawString(team2+" Won The Match by",(width/2)-200,(height/2));
			
			g.drawString(totalpawn2+" Pawn",(width/2)-100,(height/2)+50);
			
			playGame=false;
			
		}
		
		if(totalpawn2<=0) {
			
			g.setColor(Color.YELLOW);
			g.setFont(new Font("serif",Font.BOLD,45));
			g.drawString(team2+" Won The Match by",(width/2)-200,(height/2));
			
			g.drawString(totalpawn1+" Pawn",(width/2)-100,(height/2)+50);
			
			playGame=false;
			
		}
		
		
		
		
		
		
		
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
						
						A=arr[mouseTemp][0];
						B=arr[mouseTemp][1];
						mouse=true;
						
						
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
										
										
									
									}
									
	
									
									
									
									
									
									arr[mouseTemp][2]=0;
									
									//mouse1=true;
									team1Move=false;
									team2Move=true;
									
									mouse=false;
									
									
							}
							
							
							if((x2>=(arr[path[mouseTemp][i]][0]-40)&&x2<=arr[path[mouseTemp][i]][0]+40)&&((y2>=(arr[path[mouseTemp][i]][1]-40)&&y2<=arr[path[mouseTemp][i]][1]+40))
								&&(arr[path[mouseTemp][i]][2]==0&&arr[mouseTemp][2]==2)&&team2Move==true) {
								
								
									
									if(arr[mouseTemp][2]==2) {
									
										arr[path[mouseTemp][i]][2]=2;
										A=arr[path[mouseTemp][i]][0];
										B=arr[path[mouseTemp][i]][1];
										
								
									}
									
									arr[mouseTemp][2]=0;
									
									//mouse1=true;
									team1Move=true;
									team2Move=false;
									mouse=false;
								
									
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
											
										}
										
										arr[mouseTemp][2]=0;
										arr[next][2]=0;
										
										team1Move=false;
										team2Move=true;
										mouse=false;
										
										
									}
									
									
									
									 if((x2>=(arr[path[next][i]][0]-40)&&x2<=arr[path[next][i]][0]+40)&&((y2>=(arr[path[next][i]][1]-40)&&y2<=arr[path[next][i]][1]+40))
										&&((arr[next][2]==1&&arr[mouseTemp][2]==2&&arr[path[next][i]][2]==0))) {
											
											//System.out.println("mouseTemp:"+mouseTemp+"  "+"next:"+next+" "+"next2:"+path[next][i]);
											
											if(arr[mouseTemp][2]==2) {
												totalpawn1--;
												arr[path[next][i]][2]=2;
												
												A=arr[path[next][i]][0];
												B=arr[path[next][i]][1];
										
											}
											arr[mouseTemp][2]=0;
											arr[next][2]=0;
											
											team2Move=false;
											team1Move=true;
										
											mouse=false;
											

											
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
		
		
		if(playGame==true) repaint();
		
	}


}

