import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;


public class Remove extends JFrame{
	protected JButton RemoveQuestion;
	protected JTextField text;
	
	public Remove(Exam test){
		
		this.setSize(450,150);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Removing A Question");
		
		JPanel start = new JPanel();
		
		start.setLayout(null);
		
		this.add(start);
		
		RemoveQuestion = new JButton("Remove A Question");
		
		ListenForButton listen = new ListenForButton(test);
		
		RemoveQuestion.addActionListener(listen);
		
		JLabel label = new JLabel("Enter the question number that you would like to remove:");
		
		text = new JTextField("", 20);
		text.setToolTipText("Enter the number of the Question that you would like to Remove");
		
		ListenForKeys listenKeys = new ListenForKeys();
		
		text.addKeyListener(listenKeys);
		
		label.setBounds(20, 0, 450, 20);
		text.setBounds(110, 30, 200, 30);
		RemoveQuestion.setBounds(110, 70, 200, 30); 
		
		start.add(label);
		start.add(text);
		start.add(RemoveQuestion);
		this.add(start);
		
		this.setVisible(true);
		
		
		
		
	}
	
	private class ListenForButton implements ActionListener{
		private Exam test;

		public ListenForButton(Exam test) {
			this.test = test;
		}
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == RemoveQuestion){
				int index = Integer.parseInt(text.getText());
				test.removeQuestion(index-1);
				dispose();
				new menu(test);
			}
				
		}
			
	}
	
	private class ListenForKeys implements KeyListener{
		public void keyPressed(KeyEvent e){
			
		}
		
		public void keyReleased(KeyEvent e){
			
		}
		
		public void keyTyped(KeyEvent e){
			
		}
	}
}
