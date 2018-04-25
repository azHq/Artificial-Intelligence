package spl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Message extends JPanel{
	
	BufferedImage img;
	JFrame frame;
	String team;
	JFrame mainframe;
	
	int numberOfpawn,minutes,seconds;
	
	public Message(JFrame mainframe,JFrame frame,String team,int numberOfpawn,int minutes,int seconds) throws IOException {
		this.frame=frame;
		this.mainframe=mainframe;
		this.team=team;
		this.numberOfpawn=numberOfpawn;
		this.minutes=minutes;
		this.seconds=seconds;
		
		img = ImageIO.read(getClass().getResourceAsStream("/images4.jpg"));
	}
	
	public void paintComponent(Graphics g) {
		
		 	g.drawImage(img, 0, 0,  558,380, this);
		 
		 	if(team.equals("Sry try again!")) {
		 		
		 		JLabel newAcc5 = new JLabel(team);
				newAcc5.setForeground(Color.GREEN);
				newAcc5.setFont(new Font("Tahoma", Font.BOLD, 26));
				newAcc5.setBounds(175, 155, 390, 34);
				add(newAcc5);
				
				JButton btnNewButton_1 = new JButton("new game");
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton_1.setBackground(Color.blue);
				btnNewButton_1.setForeground(Color.white);
				btnNewButton_1.setBounds(281, 260, 105, 36);
				add(btnNewButton_1);
				
				btnNewButton_1 .addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						frame.setVisible(false);
						//azaz m=new azaz(mainframe);
						
						ArtificialInteligence ai=new ArtificialInteligence(mainframe);
					}
				});
				
				
				JButton btnNewButton = new JButton("cancel");
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton.setBackground(Color.red);
				btnNewButton.setForeground(Color.white);
				btnNewButton.setBounds(120, 260, 105, 35);
				add(btnNewButton);
				
				
				btnNewButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						frame.setVisible(false);
						mainframe.setVisible(false);
						
					}
				});

		 		
		 	}
		 	else {
		 	
			 	JLabel newAcc = new JLabel(team+" have won by "+numberOfpawn+" pawns");
				newAcc.setForeground(Color.GREEN);
				newAcc.setFont(new Font("Tahoma", Font.BOLD, 26));
				newAcc.setBounds(75, 105, 390, 34);
				add(newAcc);
				
				JLabel newAcc2 = new JLabel("in");
				newAcc2.setForeground(Color.GREEN);
				newAcc2.setFont(new Font("Tahoma", Font.BOLD, 26));
				newAcc2.setBounds(245, 135, 224, 34);
				add(newAcc2);
				
				JLabel newAcc3 = new JLabel(minutes+" minutes and "+seconds+" seconds");
				newAcc3.setForeground(Color.GREEN);
				newAcc3.setFont(new Font("Tahoma", Font.BOLD, 26));
				newAcc3.setBounds(105, 160, 450, 34);
				add(newAcc3);
			 
			 
			 	JButton btnNewButton = new JButton("cancel");
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton.setBackground(Color.red);
				btnNewButton.setForeground(Color.white);
				btnNewButton.setBounds(120, 260, 105, 35);
				add(btnNewButton);
				
				
				btnNewButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						frame.setVisible(false);
						mainframe.setVisible(false);
						
					}
				});
				
				
				
				
				JButton btnNewButton_1 = new JButton("new game");
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
				btnNewButton_1.setBackground(Color.blue);
				btnNewButton_1.setForeground(Color.white);
				btnNewButton_1.setBounds(281, 260, 105, 36);
				add(btnNewButton_1);
				
				btnNewButton_1 .addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						
						frame.setVisible(false);
						azaz m=new azaz(mainframe);
					}
				});
		 	}
		
	}
}
