import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class Save extends JFrame{
	protected JButton Done;
	protected JTextField text;
	
	public Save(){
		this.setSize(400,200);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Save Exam");
		
		JPanel start = new JPanel();
		
		start.setLayout(null);
		
		JLabel label = new JLabel("Save as: ex) stored_exam.txt");
		
		
		
		Done = new JButton("Save Exam");
		
		ListenForButton listen = new ListenForButton();
		
		Done.addActionListener(listen);
		
		text = new JTextField("", 20);
		text.setToolTipText("Enter Exam name");
		
		ListenForKeys listenKeys = new ListenForKeys();
		
		text.addKeyListener(listenKeys);
		
		label.setBounds(100, 0, 400, 20);
		text.setBounds(100, 30, 200, 30);
		Done.setBounds(120, 70, 150, 30);
		
		start.add(text);
		start.add(Done);
		
		start.add(label);
		this.add(start);
		
		this.setVisible(true);
		
		
		
	}
	private class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == Done){
				Exam test = null;
				String fileName;
				try{
					fileName = text.getText();
					File File = new File(fileName);
					PrintWriter pw = new PrintWriter(File);
					test.save(pw);
					JOptionPane.showMessageDialog(null, "Exam Saved", "Success", JOptionPane.PLAIN_MESSAGE);
				}
				catch(Exception a){
					JOptionPane.showMessageDialog(null, "Failed To Save. Enter a valid filename", "Error", JOptionPane.WARNING_MESSAGE);
					dispose();
					new Save();
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
