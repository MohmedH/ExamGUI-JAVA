import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class ExamBuilder{
	public static void main(String[] args){
		Exam test=null;
		Scanner input=new Scanner(System.in);



		int i = 0;
		while(i == 0){
			System.out.println("Select one of the following:\n"
					+ "1) Load a saved Exam from a file.\n"
					+ "2) Add questions interactively.\n"
					+ "3) Remove questions interactively.\n"
					+ "4) Reorder questions, and/or answers.\n"
					+ "5) Print the exam.\n"
					+ "6) Save the exam.\n"
					+ "7) Quit.\n");
			System.out.print("Select an option: ");
			int choice = Integer.parseInt(input.nextLine());

			if(choice == 1){

				System.out.println("Enter the name of the file that you would like to Load: ");
				System.out.println("ex) stored_exam.txt");
				System.out.print("-> ");
				System.out.println("");


				/*

				System.out.println("Save as: ");
				System.out.println("ex) stored_exam.txt");
				System.out.print("-> ");
				System.out.println("");
				String fileName;
				try{
					fileName = input.nextLine().trim();
					File File=new File(fileName);
					PrintWriter pw=new PrintWriter(File);
					test.save(pw);
				}

*/				String fileName;
				fileName = input.nextLine().trim();
				try{

					// File File=new File(fileName);
					// PrintWriter pw=new PrintWriter(File);
					// test.save(pw);

					File file = new File(fileName);
					test = new Exam(new Scanner(file));
				}
				catch(Exception e){
					System.out.println("================ error =================");
					System.out.println("The file does not exists!");
					System.out.println("Please type the right file name next time! :)");
					return;
				}

				System.out.println("LOADING EXAM . . . .");
			}

			else if(choice == 2){

				if(test == null){
					System.out.println("What would you like the title of the exam to be? ");
					String title = input.nextLine();
					test = new Exam(title);
				}

				//addQuestion(test);

				System.out.println("Would you like to add a MCQuestion: MCMAQ, MCSAQuestion: MCSAQ, SAQuestion: SAQ, or NumQuestion: NumQ?");
				String questionType = input.nextLine().trim();
				System.out.println("Enter the text of the question: ");
				String Text = input.nextLine().trim();
				System.out.println("Enter the max value of the question: ");
				double Val = Double.parseDouble(input.nextLine());
				switch(questionType){

					case "MCMAQ":

						System.out.println("Enter the base credit:" );
						double baseCredit = Double.parseDouble(input.nextLine());
						System.out.println("Number of Answers for this question: ");
						int NumofAnswers = Integer.parseInt(input.nextLine());
						MCMAQuestion MCMAQ = new MCMAQuestion(Text,Val, baseCredit);
						//addAnswers(MCMAQ , NumofAnswers);
						for(int a=0;a<NumofAnswers;a++){

							System.out.println("Answer #"+(a+1)+": \n Enter the text of the answer: " );
							String answerText = input.nextLine();
							System.out.println("Enter the credit if selected: ");
							double creditIfSelected=Double.parseDouble(input.nextLine());
							//((MCMAQuestion)MCMAQ).getNewAnswer(answerText, creditIfSelected);
							MCMAQ.addAnswer((MCAnswer)((MCMAQuestion)MCMAQ).getNewAnswer(answerText,creditIfSelected));

						}
						test.addQuestion(MCMAQ);
						break;

					case "MCSAQ":

						System.out.println("Number of Answers for this question: ");
						int numAnswers = Integer.parseInt(input.nextLine());
						MCSAQuestion MCSAQ = new MCSAQuestion(Text,Val);
						//addAnswers(MCSAQ , answer);
						for(int a=0;a<numAnswers;a++){
							System.out.println("Answer #"+(a+1)+": \n Enter the text of the answer: " );
							String answerText=input.nextLine();
							System.out.println("Enter the credit if selected: ");
							double creditIfSelected=Double.parseDouble(input.nextLine());
							MCSAQ.addAnswer((MCAnswer)((MCSAQuestion)MCSAQ).getNewAnswer(answerText, creditIfSelected));

						}
						test.addQuestion(MCSAQ);
						break;

					case "SAQ":

						System.out.println("What is the right answer: ");
						String rightAnswer = input.nextLine();
						SAAnswer SAA = new SAAnswer(rightAnswer);
						SAQuestion SAQ = new SAQuestion(Text,Val);
						SAQ.setRightAnswer(SAA);
						test.addQuestion(SAQ);
						break;

					case "NumQ":

						System.out.println("What is the right answer: ");
						double realAnswer = Double.parseDouble(input.nextLine());
						System.out.println("What is the tolerance: ");
						double tol = Double.parseDouble(input.nextLine());
						NumAnswer NUMA = new NumAnswer( realAnswer);
						NumQuestion NUMQ = new NumQuestion(Text, Val, tol);
						NUMQ.setRightAnswer(NUMA);
						test.addQuestion(NUMQ);
					default:

						System.out.println("Not a valid option. ");
				}

			}

			else if(choice == 3){

				System.out.println("Remove questions");

				if(test == null){
					System.out.println("The exam does not exist");
				}
				else{
					System.out.println("What question number would you like to remove? ( 1 ~ endOfQuesion): ");
					int index= Integer.parseInt(input.nextLine());
					test.removeQuestion(index-1);
				}
			}

			else if(choice == 4){

				System.out.println("Press 1 to reorder the questions, or press 2 to reorder the answers");
				int reorder= Integer.parseInt(input.nextLine());
				if(reorder == 1){
					test.reorderQuestions();
				}
				else{

					if(reorder == 2){
						System.out.println("What question would you like to reorder, or you can enter -1 to reorder them all. ");
						reorder =  Integer.parseInt(input.nextLine());
						test.reorderMCAAnswers(reorder);
					}
				}
			}
			else if(choice == 5){

				System.out.println("Printing the exam");
				test.print();
				System.out.println("\n\n");

			}
			else if(choice == 6){

				System.out.println("Save as: ");
				System.out.println("ex) stored_exam.txt");
				System.out.print("-> ");
				System.out.println("");
				String fileName;
				try{
					fileName = input.nextLine().trim();
					File File=new File(fileName);
					PrintWriter pw=new PrintWriter(File);
					test.save(pw);
				}
				catch(Exception e){
					System.out.println("================ error =================");

					return;
				}
				System.out.println("SAVING FILE " + fileName + ". . . .");
			}

			else if(choice == 7){

				System.out.println("Quiting");
				i = 1;
			}

			else{
				System.out.println("Not a valid. Try Again");
			}
		}
	}
}