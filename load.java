import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class load extends JFrame{
	protected JButton loadExam;
	protected JTextField text;
	
	public load(){
		
		this.setSize(400,200);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Loading a Exam");
		
		JPanel start = new JPanel();
		start.setLayout(null);
		
		
		
		loadExam = new JButton("Load Exam");
		
		ListenForButton listen = new ListenForButton();
		
		loadExam.addActionListener(listen);
		
		text = new JTextField("", 20);
		text.setToolTipText("Enter the name of the Exam");
		JLabel label = new JLabel("Enter the name of the Exam that you would like");
		JLabel label2 = new JLabel("to load. ex) stored_exam.txt");
		ListenForKeys listenKeys = new ListenForKeys();
		
		text.addKeyListener(listenKeys);
		
		label.setBounds(25, 0, 400, 20);
		label2.setBounds(85, 20, 400, 20);
		text.setBounds(85, 50, 200, 30);
		loadExam.setBounds(110, 100, 150, 30);
		
		start.add(text);
		start.add(label);
		start.add(label2);
		start.add(loadExam);
		
		this.add(start);
		
		this.setVisible(true);
		
		
		
		
		
	}	
	private class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == loadExam){
				Exam test = null;
				String fileName = text.getText();
				try{
					
					
					File file = new File(fileName);
					test = new Exam(new Scanner(file));
					JOptionPane.showMessageDialog(null, "Exam Loaded", "Success", JOptionPane.PLAIN_MESSAGE);
				}
				catch(Exception a){
					JOptionPane.showMessageDialog(null, "Failed To Load. Enter a valid filename", "Error", JOptionPane.WARNING_MESSAGE);
					dispose();
					new load();
				}

				
				dispose();
				new menu();
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
