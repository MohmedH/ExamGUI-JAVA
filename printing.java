import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class printing extends JFrame {
	
	protected JButton Done;
	protected JTextField text;
	
	
	public printing(){	
		this.setSize(400,400);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Printing the Exam");
		
		JPanel start = new JPanel();
		
		
	
		
		start.setLayout(null);
		
		Done = new JButton("Return To menu");
		
		ListenForButton listen = new ListenForButton();
		
		Done.addActionListener(listen);
		
	
		

		JTextArea textArea1 = new JTextArea(15, 20);
		
		JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    
	    scrollbar1.setBounds(50, 10, 300, 300);
	    Done.setBounds(125, 325, 150, 30);
	    
	    //textArea1.append(test.print());
	    start.add(scrollbar1);

		start.add(Done);
		
		
		this.add(start);
		
		this.setVisible(true);
	}
	
	
	private class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == Done){
				
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
