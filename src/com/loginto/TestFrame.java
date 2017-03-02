package com.loginto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class TestFrame extends JFrame implements KeyListener{

	public static void main(String[] args){
		TestFrame fr = new TestFrame();
		fr.setSize(200,300);
		fr.setVisible(true);
		fr.setFocusable(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Test");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Test");
	}

}
