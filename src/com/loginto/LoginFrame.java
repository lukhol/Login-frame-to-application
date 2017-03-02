package com.loginto;

import java.awt.Dimension;
import java.awt.Toolkit;


import javax.swing.*;

public class LoginFrame extends JFrame{
	private static final long serialVersionUID = 7773952141401986686L;
	private LoginPanel panel = new LoginPanel();
	
	public static void main(String[] args){
		JFrame fr = new LoginFrame("Zaloguj sie...");
		fr.setVisible(true);
	}
	
	public LoginFrame(String title){
		this.setTitle(title);
		this.add(panel);
		this.pack();
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		panel.setParrentField(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
