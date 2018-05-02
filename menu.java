import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;


public class menu extends JFrame{
	protected JButton loadButton;
	protected JButton AddQuestionButton;
	protected JButton RemoveButton;
	protected JButton ReorderButton;
	protected JButton printExam;
	protected JButton SaveButton;
	protected JButton QuitButton;
	public Exam test;
	public menu(Exam test){
		
		
		this.setSize(400,450);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("ExamBuilder");
		
		JPanel start = new JPanel();
		start.setLayout(null);
		
		
		JLabel label = new JLabel("Select one of the following");
		
		
		
		loadButton = new JButton("Load Exam");
		AddQuestionButton = new JButton("Add question");
		RemoveButton = new JButton("Remove question");
		ReorderButton = new JButton("Reorder");
		printExam = new JButton("Print");
		SaveButton = new JButton("Save Exam");
		QuitButton = new JButton("Quit");
		
		ListenForButton listen = new ListenForButton(test);
		
		loadButton.addActionListener(listen);
		AddQuestionButton.addActionListener(listen);
		RemoveButton.addActionListener(listen);
		ReorderButton.addActionListener(listen);
		printExam.addActionListener(listen);
		SaveButton.addActionListener(listen);
		QuitButton.addActionListener(listen);
		
		//JTextField text = new JTextField("", 20);
		//text.setToolTipText("Enter Exam name");
		
		//ListenForKeys listenKeys = new ListenForKeys();
		
		//text.addKeyListener(listenKeys);
		label.setBounds(110, 0, 200, 40);
		loadButton.setBounds(130, 50, 160, 40);
		AddQuestionButton.setBounds(130, 100, 160, 40);
		RemoveButton.setBounds(130, 150, 160, 40);
		ReorderButton.setBounds(130, 200, 160, 40);
		printExam.setBounds(130, 250, 160, 40);
		SaveButton.setBounds(130, 300, 160, 40);
		QuitButton.setBounds(130, 350, 160, 40);
		
		
		
		start.add(loadButton);
		//start.add(text);
		start.add(AddQuestionButton);
		start.add(RemoveButton);
		start.add(ReorderButton);
		start.add(printExam);
		start.add(SaveButton);
		start.add(QuitButton);
		
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
			
			if(e.getSource() == loadButton){
				dispose();
				new load(test);
			}
			else if( e.getSource() == AddQuestionButton){
				dispose();
				new AddQuestion(test);
			}
			else if( e.getSource() == RemoveButton){
				dispose();
				new Remove(test);
			}
			else if( e.getSource() == ReorderButton){
				dispose();
				new Reorder(test);
			}
			else if( e.getSource() == printExam){
				test.print();
				new printing(test);
				dispose();

			}
			else if( e.getSource() == SaveButton){
				dispose();
				new Save(test);
			}
			else if( e.getSource() == QuitButton){
				System.exit(0);
			}
				
		}
			
	}
	
	/*private class ListenForKeys implements KeyListener{
		public void keyPressed(KeyEvent e){
			
		}
		
		public void keyReleased(KeyEvent e){
			
		}
		
		public void keyTyped(KeyEvent e){
			
		}
	}
		
	*/
	
	
}
