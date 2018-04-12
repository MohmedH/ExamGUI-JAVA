import java.io.PrintWriter;
import java.util.Scanner;

public class NumQuestion extends Question{
	
	protected double tolerance; //done
	
	public NumQuestion(String text, double maxValue, double tolerance){ //done
		super(text, maxValue);
		this.tolerance = tolerance;
	}
	
	public NumQuestion(Scanner scan){ //done

		maxValue = Double.parseDouble(scan.nextLine());
        text = scan.nextLine();
        rightAnswer = new NumAnswer(scan);
        tolerance = Double.parseDouble(scan.nextLine());


	}

	public void saveStudentAnswers(PrintWriter writer) {
		writer.write("NumAnswer\n");
		if(studentAnswer instanceof NumAnswer) {
			writer.write(((NumAnswer) studentAnswer).answer + "\n");
		}
	}

	public void restoreStudentAnswers(String s, Scanner scanner) {
		System.out.println("- "+ s);
		double answer = Double.parseDouble(s);
		NumAnswer ans = new NumAnswer(answer);
		studentAnswer = ans;
	}
	
	public Answer getNewAnswer(){ //done
		return null;

	}
	
	public void getAnswerFromStudent(){ // done
		System.out.print("Num Answer: ");
        Scanner userInput = ScannerFactory.getKeyboardScanner();
		String ans = userInput.nextLine();
        if(ans.equals("s"))
		{
			System.out.println("Skipped NumQuestion");
			NumAnswer answer = new NumAnswer("null");
			setStudentAnswer(answer);
			return;
		}
		else {
			// get the user input integer
			double userSelectedAnswer = Double.parseDouble(ans);
			System.out.println("-    You answered: "+ userSelectedAnswer + ".");
			// do the standard input and then save it as
			// studentAnswer.
			NumAnswer studentAnswer = new NumAnswer(userSelectedAnswer);
			setStudentAnswer(studentAnswer);

		}

	}
	
	public double getValue(){ // done
		Answer rightAnswer = getRightAnswer();
		if(getStudentAnswer() == null) {
			return 0.0;
		}
        if (getStudentAnswer().getCredit(rightAnswer) > 0) {
            return getMaxValue();
        }
        else {
            return 0.0;
        }
		
	}
	
	public void save(PrintWriter writer){  // done
		writer.write(text + "\n");
        //System.out.println(rightAnswer.getClass().getName());
        rightAnswer.save(writer);
        //writer.write(tolerance + "\n");
	}
	
	
}
