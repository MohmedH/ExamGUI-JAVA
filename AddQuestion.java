import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

public class AddQuestion extends JFrame{
	
	JButton Add;
	JRadioButton NumQuestion;
	JRadioButton MCSAQuestion;
	JRadioButton MCMAQuestion;
	JRadioButton SAQuestion;
	JTextField QuestionText;
	JTextField maxCredit;
	JTextField AnswerText;
	JTextField numAnswers;
	JTextField tolerance;
	JTextField credit;
	JTextField baseCreditField;
	ButtonGroup bG;
	
	//ArrayList<string> multiAnswers = new ArrayList<string>();
	//ArrayList<Integer> multiCredit = new ArrayList<Integer>(); 
	
	public AddQuestion(Exam test){
		
		this.setSize(800,550);
		
		this.setLocationRelativeTo(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Add Question");
		
		JPanel start = new JPanel();
		
		start.setLayout(null);
		
		
		Add = new JButton("Add");
		NumQuestion = new JRadioButton("NumQuestion");
		MCSAQuestion = new JRadioButton("MCSAQuestion");
		MCMAQuestion = new JRadioButton("MCMAQuestion");
		SAQuestion = new JRadioButton("SAQuestion");
		bG = new ButtonGroup();
		
		bG.add(NumQuestion);
		bG.add(MCSAQuestion);
		bG.add(MCMAQuestion);
		bG.add(SAQuestion);
		
		
		ListenForButton listen = new ListenForButton(test);
		
		Add.addActionListener(listen);
		JLabel questionLabel = new JLabel("Select the type of the question:");
		JLabel questionName = new JLabel("Enter the Question:");
		JLabel AnswerLabel = new JLabel("Enter Answer(s) seperated by commas ex) a,b,c,d (no space between numbers)");
		JLabel creditLabel = new JLabel("Enter the credit for each Answer seperated by commas ex) 1,2,3,4 (no space between numbers)");
		JLabel numAnswersLabel = new JLabel("How many Answers are there? (only for MCSA/MCMA Question)");
		JLabel toleranceLabel = new JLabel("Enter the tolerance: (only for NumQuestion)");
		JLabel baseCreditLabel = new JLabel("Enter base credit: (only for MCMAQuestion)");
		JLabel maxCreditLabel = new JLabel("Enter the max credit:");
		
		QuestionText = new JTextField("", 20);
		QuestionText.setToolTipText("Enter the Question");
		
		AnswerText = new JTextField("", 20);
		AnswerText.setToolTipText("Enter Answer(s) to the Question seperated by commas");
		
		credit = new JTextField("", 20);
		credit.setToolTipText("Enter the credit for each Answer seperated by commas");
		
		numAnswers = new JTextField("", 20);
		numAnswers.setToolTipText("How many answers are there?");
		
		tolerance = new JTextField("", 20);
		tolerance.setToolTipText("Enter the tolerance");
		
		baseCreditField = new JTextField("", 20);
		baseCreditField.setToolTipText("Enter the base credit");
		
		maxCredit = new JTextField("", 20);
		maxCredit.setToolTipText("Enter the max credit");
		
		questionLabel.setBounds(275,0, 800, 20);
		NumQuestion.setBounds(100, 40, 150, 20);
		SAQuestion.setBounds(250, 40, 150, 20);
		MCMAQuestion.setBounds(400, 40, 150, 20);
		MCSAQuestion.setBounds(550, 40, 150, 20);
		questionName.setBounds(325, 70, 800, 20);
		QuestionText.setBounds(250, 100, 300, 20);
		maxCreditLabel.setBounds(325, 130, 800, 20);
		maxCredit.setBounds(250, 160, 300, 20);
		numAnswersLabel.setBounds(180, 190, 800, 20);
		numAnswers.setBounds(250, 220, 300, 20);
		AnswerLabel.setBounds(265, 250, 800, 20);
		AnswerText.setBounds(250, 280, 300, 20);
		creditLabel.setBounds(200, 310, 800, 20);
		credit.setBounds(250, 340, 300, 20); 
		toleranceLabel.setBounds(250, 370, 800, 20);
		tolerance.setBounds(250, 400, 300, 20);
		baseCreditLabel.setBounds(250, 430, 800, 20);
		baseCreditField.setBounds(250, 470, 300, 20);
		Add.setBounds(320,500,150, 30);
		
		
		start.add(questionLabel);
		start.add(maxCredit);
		start.add(maxCreditLabel);
		start.add(NumQuestion);
		start.add(MCSAQuestion);
		start.add(MCMAQuestion);
		start.add(SAQuestion);
		start.add(baseCreditLabel);
		start.add(baseCreditField);
		start.add(questionName);
		start.add(AnswerLabel);
		start.add(creditLabel);
		start.add(numAnswersLabel);
		start.add(toleranceLabel);
		
		start.add(QuestionText);
		start.add(AnswerText);
		start.add(credit);
		start.add(numAnswers);
		start.add(tolerance);
		start.add(Add);
		
		this.add(start);
		
		
		this.setVisible(true);
		
		
		
		
	}
	private class ListenForButton implements ActionListener{
		private Exam test;

		public ListenForButton(Exam test) {
			this.test = test;
		}
		
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource() == Add){
				String Text = QuestionText.getText();
				if( NumQuestion.isSelected()){
					//Text = AnswerText.getText();
					double Val = Double.parseDouble(maxCredit.getText());
					double realAnswer = Double.parseDouble(AnswerText.getText());
					double tol = Double.parseDouble(tolerance.getText());
					NumAnswer NUMA = new NumAnswer( realAnswer);
					NumQuestion NUMQ = new NumQuestion(Text, Val, tol);
					NUMQ.setRightAnswer(NUMA);
					test.addQuestion(NUMQ);

					JOptionPane.showMessageDialog(null, "NumQuestion Added", "Success", JOptionPane.PLAIN_MESSAGE);
				}
				else if( SAQuestion.isSelected()){
					System.out.println("What is the right answer: ");
					double Val = Double.parseDouble(maxCredit.getText());
					String rightAnswer = AnswerText.getText();
					SAAnswer SAA = new SAAnswer(rightAnswer);
					SAQuestion SAQ = new SAQuestion(Text,Val);
					SAQ.setRightAnswer(SAA);
					test.addQuestion(SAQ);

					JOptionPane.showMessageDialog(null, "SAQuestion Added", "Success", JOptionPane.PLAIN_MESSAGE);
				}
				else if( MCSAQuestion.isSelected()){
					//System.out.println("Number of Answers for this question: ");
					int numberofAnswers = Integer.parseInt(numAnswers.getText());
					double Val = Double.parseDouble(maxCredit.getText());
					MCSAQuestion MCSAQ = new MCSAQuestion(Text,Val);
					//addAnswers(MCSAQ , answer);
					String[] max = credit.getText().split(", ");
					String[] tokens = AnswerText.getText().split(", ");
					 
					for(int a=0;a<numberofAnswers;a++){
						//System.out.println("Answer #"+(a+1)+": \n Enter the text of the answer: " );
						//String answerText=input.nextLine();
						//System.out.println("Enter the credit if selected: ");
						//double creditIfSelected=Double.parseDouble(input.nextLine());
						MCSAQ.addAnswer((MCAnswer)((MCSAQuestion)MCSAQ).getNewAnswer(tokens[a], Double.parseDouble(max[a])));

					}
					test.addQuestion(MCSAQ);

					
					JOptionPane.showMessageDialog(null, "MCSAQuestion Added", "Success", JOptionPane.PLAIN_MESSAGE);
				}else if (MCMAQuestion.isSelected()){
					//System.out.println("Enter the base credit:" );
					double baseCredit = Double.parseDouble(baseCreditField.getText());
					//System.out.println("Number of Answers for this question: ");
					double Val = Double.parseDouble(maxCredit.getText());
					int NumofAnswers = Integer.parseInt(numAnswers.getText());
					MCMAQuestion MCMAQ = new MCMAQuestion(Text,Val, baseCredit);
					
					String[] max = credit.getText().split(",");
					String[] tokens = AnswerText.getText().split(",");
					
					//addAnswers(MCMAQ , NumofAnswers);
					for(int a=0;a<NumofAnswers;a++){

						//System.out.println("Answer #"+(a+1)+": \n Enter the text of the answer: " );
						//String answerText = input.nextLine();
						//System.out.println("Enter the credit if selected: ");
						//double creditIfSelected=Double.parseDouble(input.nextLine());
						//((MCMAQuestion)MCMAQ).getNewAnswer(answerText, creditIfSelected);
						MCMAQ.addAnswer((MCAnswer)((MCMAQuestion)MCMAQ).getNewAnswer(tokens[a], Double.parseDouble(max[a])));

					}
					test.addQuestion(MCMAQ);
					

					
					JOptionPane.showMessageDialog(null, "MCMAQuestion Added", "Success", JOptionPane.PLAIN_MESSAGE);
					
					
				}
				
				dispose();
				new menu(test);
			}
				
		}
			
	}
	
	
	
	
	
	
}
