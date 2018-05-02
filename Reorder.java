import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class Reorder extends JFrame{
	protected JButton ReorderQuestions;
	protected JButton ReorderAnswers;
	protected JTextField text;
	
	
	
	public Reorder(Exam test){
		
		this.setSize(500,200);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Reordering the Exam");
		
		JPanel start = new JPanel();
		start.setLayout(null);
		
		
		JLabel label = new JLabel("Enter the number of the Question that you would like to reorder.");
		JLabel label2 = new JLabel(" Enter-1 to reorder all.  (only for Reorder Answers button)");
		
		ReorderQuestions = new JButton("Reorder Questions");
		
		ListenForButton listen = new ListenForButton(test);
		
		ReorderQuestions.addActionListener(listen);
		
		
		ReorderAnswers = new JButton("Reorder Answers");
		
		//ListenForButton listen = new ListenForButton();
		
		ReorderAnswers.addActionListener(listen);
		text = new JTextField("", 20);
		text.setToolTipText("Enter the Quesiton that you would like to ReOrder");
		
		ListenForKeys listenKeys = new ListenForKeys();
		
		text.addKeyListener(listenKeys);
		
		label.setBounds(25, 0, 500, 20);
		label2.setBounds(45, 20, 500, 20);
		text.setBounds(150, 50, 200, 30);
		ReorderQuestions.setBounds(75, 100, 175, 30);
		ReorderAnswers.setBounds(250, 100, 175, 30);
		
		start.add(text);
		start.add(ReorderQuestions);
		start.add(ReorderAnswers);
		start.add(label2);
		start.add(label);
		this.add(start);
		
		this.setVisible(true);
		
		
		
	}
	
	private class ListenForButton implements ActionListener{
		private Exam test;

		public ListenForButton(Exam test) {
			this.test = test;
		}
		
		public void actionPerformed(ActionEvent e){

			if(e.getSource() == ReorderQuestions){
				//test.reorderQuestions();
				dispose();
				new menu(test);
			}
			else if( e.getSource() == ReorderAnswers){
				//int reorder= Integer.parseInt(text.getText());
				//test.reorderMCAAnswers(reorder);
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
