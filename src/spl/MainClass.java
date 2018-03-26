package spl;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class MainClass {
	
	
	
	public static void main(String[] args) {
		
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int  height = gd.getDisplayMode().getHeight();
		
		JFrame frame=new JFrame("MD.EZAZUL HAQUE");
		SholoGuti sl=new SholoGuti(frame);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,width+20, height+20);
		frame.add(sl);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

}
