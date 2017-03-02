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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
