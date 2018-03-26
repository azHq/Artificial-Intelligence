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
import java.io.IOException;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Font;

public class azaz {

		private JFrame frame;
		private JLabel background_1;
		private JLabel background_2;
		private JPanel jp_1;
	
		public azaz() {
			
			
			initialize();
			
			
		}
		
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						azaz window = new azaz();
						window.frame.setVisible(true);
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
							JFrame frame=new JFrame("MD.EZAZUL HAQUE");
							
							SholoGuti sl=new SholoGuti(frame);
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
							int width = gd.getDisplayMode().getWidth();
							int height = gd.getDisplayMode().getHeight();
							
							frame.setSize(width,height);
							frame.getContentPane().add(sl);
							frame.setResizable(false);
							frame.setVisible(true);
					
					
					}
					
			});
			jp_1.setLayout(null);
			jp_1.add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("Statistics");
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_2.setBackground(new Color(0, 255, 0));
			btnNewButton_2.setBounds(115, 205, 100, 32);
			jp_1.add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("Help");
			btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNewButton_3.setBackground(new Color(51, 255, 51));
			btnNewButton_3.setBounds(125, 250, 78, 32);
			jp_1.add(btnNewButton_3);
			
			JButton btnExit = new JButton("Exit");
			btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnExit.setBackground(Color.RED);
			btnExit.setBounds(125, 295, 78, 32);
			jp_1.add(btnExit);
			
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					frame.setVisible(false);
				}
			});
			
			
			JButton btnSinglePlayer = new JButton("Single Player");
			btnSinglePlayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					frame.setVisible(false);
					JFrame frame=new JFrame("MD.EZAZUL HAQUE");
					
					ArtificialInteligence AI=new ArtificialInteligence(frame);
					
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
					int width = gd.getDisplayMode().getWidth();
					int height = gd.getDisplayMode().getHeight();
					
					frame.setSize(width,height);
					frame.getContentPane().add(AI);
					frame.setResizable(false);
					frame.setVisible(true);
					
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
