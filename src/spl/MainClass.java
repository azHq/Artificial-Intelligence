package spl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JMenu;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Font;

public class MainClass {

		private JFrame frame;
		private JLabel background_1;
		private JLabel background_2;
		private JPanel jp_1;
		
		public static JFrame frame1;
	
		public MainClass(JFrame frame1) {
			
			this.frame1=frame1;
			initialize();
			
			
		}
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
						frame1=new JFrame("MD.EZAZUL HAQUE");
						MainClass window = new MainClass(frame1);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	
		
	
		
		public void initialize() {
			
			JPanel jp=null;
			JLabel background=null;
			BufferedImage img;
			
			frame = new JFrame();
			frame.setBounds(100, 100, 384, 521);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);
			
			//main game board
			
			
			try
			{
				
			 img = ImageIO.read(getClass().getResourceAsStream("/images4.jpg")); 
			 jp_1=new JPanel() {
				 
				 	@Override
				    public void paintComponent(Graphics g){
				       
				        g.drawImage(img, 0, 0,468,600, this);
				        
				    }
			 };
			 
			
			 
			
			 
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		
			frame.getContentPane().add(jp_1);
			
			JButton btnNewButton_1 = new JButton("Two Player");
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_1.setBackground(new Color(153, 255, 51));
			btnNewButton_1.setBounds(105, 160, 121, 32);
			btnNewButton_1.addActionListener(new ActionListener() {
				
					public void actionPerformed(ActionEvent e) {
					
							frame.setVisible(false);
							
							
							SholoGuti sl=new SholoGuti(frame1);
							frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
							int width = gd.getDisplayMode().getWidth();
							int height = gd.getDisplayMode().getHeight();
							
							frame1.setSize(width,height);
							frame1.getContentPane().add(sl);
							frame1.setResizable(false);
							frame1.setVisible(true);
					
					
					}
					
			});
			jp_1.setLayout(null);
			jp_1.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Statistics");
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_2.setBackground(new Color(0, 255, 0));
			btnNewButton_2.setBounds(115, 205, 100, 32);
			jp_1.add(btnNewButton_2);
			
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
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
					
					
					
					
					
					
				}
			});
			
			
			JButton btnNewButton_3 = new JButton("Help");
			btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_3.setBackground(new Color(51, 255, 51));
			btnNewButton_3.setBounds(125, 250, 78, 32);
			jp_1.add(btnNewButton_3);
			
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 24));
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font( "Arial", Font.BOLD, 24)));       
					UIManager.put("OptionPane.minimumSize",new Dimension(500,500));
					
					JOptionPane.showMessageDialog(null,"This game is played between 2 person.Each having 16 Pawns. These pawns can move one step forward on the valid positions of the cort. \nIf a player can cross a pawn of the other side then the player will achieve 1 point. In this way whoever manages to achieve 16 points will be the winner.\nBut winning point could be declared at the beginning of the game.");
					
					
					
					
					
				}
			});
			
			
			JButton btnExit = new JButton("Exit");
			btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnExit.setBackground(Color.RED);
			btnExit.setBounds(125, 295, 78, 32);
			jp_1.add(btnExit);
			
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					frame.setVisible(false);
					frame1.setVisible(false);
				}
			});
			
			
			JButton btnSinglePlayer = new JButton("Single Player");
			btnSinglePlayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					frame.setVisible(false);
					
					ArtificialInteligence AI=new ArtificialInteligence(frame1);
					
					frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
					int width = gd.getDisplayMode().getWidth();
					int height = gd.getDisplayMode().getHeight();
					
					frame1.setSize(width,height);
					frame1.getContentPane().add(AI);
					frame1.setResizable(false);
					frame1.setVisible(true);
					
				}
			});
			btnSinglePlayer.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnSinglePlayer.setBackground(new Color(51, 255, 51));
			btnSinglePlayer.setBounds(105, 115, 121, 32);
			jp_1.add(btnSinglePlayer);
			
			
			//background.setLayout(new FlowLayout());
			/*JLabel l1=new JLabel("Here is a button");
			JButton b1=new JButton("I am a button");
			frame.getContentPane().add(background);
			background.add(l1);
			background.add(b1);*/
			//frame.setSize(new Dimension(img.getWidth(null), img.getHeight(null)));
	
			
			frame.setVisible(true);
			
			
			
			
		}
	}
