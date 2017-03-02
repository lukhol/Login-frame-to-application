package com.loginto;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gridbag.GBC;

public class LoginPanel extends JPanel {
	private JLabel loginLabel = new JLabel("Login:");
	private JLabel passwordLabel = new JLabel("Has³o:");
	private JTextField loginField = new JTextField(20);
	private JPasswordField passwordField = new JPasswordField(20);
	private JButton loginButton, newAccountButton;
	private JTextField infoField = new JTextField();
	private LoginFrame parentFrame;
	
	
	public LoginPanel(){
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		loginButton = new JButton("Zaloguj");
		newAccountButton = new JButton("Stwórz konto");
		
		add(loginLabel, new GBC(0,0,1,1));
		add(loginField, new GBC(1,0,2,1));
		
		add(passwordLabel, new GBC(0,1,1,1));
		add(passwordField, new GBC(1,1,2,1));
		
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JButton but = (JButton)event.getSource();
				LoginPanel panel = (LoginPanel)but.getParent();
				
				DBConnect db = new DBConnect();
				if(db.checkLoginAndPassword(panel.getLogin(), panel.getPassword())){
					JFrame mfr = new TestFrame();
					panel.mClose();
					mfr.setVisible(true);
				}
				else panel.getInfoField().setText("Nieprawid³owa nazwa u¿ytkownika lub has³a.");
			}
		});
		
		newAccountButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				JButton but = (JButton)event.getSource();
				LoginPanel panel = (LoginPanel)but.getParent();
				
				DBConnect db = new DBConnect();
				if(db.addNewAccount(panel.getLogin(), panel.getPassword()))
					panel.getInfoField().setText("U¿ytkownik zosta³ dodany.");
				else panel.getInfoField().setText("Nie uda³o siê dodaæ u¿ytkownika.");
			}
		});
		
		add(loginButton, new GBC(0,2,3,1).setFill(GBC.HORIZONTAL));
		add(newAccountButton, new GBC(0,3,3,1).setFill(GBC.HORIZONTAL));
		
		infoField.setEditable(false);
		add(infoField, new GBC(0,4,3,1).setFill(GBC.HORIZONTAL));
	}
	
	public String getLogin(){
		return loginField.getText();
	}
	
	public char[] getPassword(){
		return passwordField.getPassword();
	}
	
	public JTextField getInfoField(){
		return infoField;
	}
	
	public void setParrentField(LoginFrame parentFrame){
		this.parentFrame = parentFrame;
	}
	
	public void mClose(){
		parentFrame.setVisible(false);
	}
}
