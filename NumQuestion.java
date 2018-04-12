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
        //System.out.println(maxValue);
        text = scan.nextLine();
        //System.out.println(text);
        rightAnswer = new NumAnswer(scan);
        
        tolerance = scan.nextDouble();
	}
	
	public Answer getNewAnswer(){ //done
		return null;

	}
	
	public void getAnswerFromStudent(){ // done
		System.out.print("Num Answer: ");
        Scanner userInput = ScannerFactory.getKeyboardScanner();

        // get the user input integer
        double userSelectedAnswer = userInput.nextDouble();

        System.out.println("-    You answered: "+ userSelectedAnswer + ".");
        // do the standard input and then save it as
        // studentAnswer.
        NumAnswer studentAnswer = new NumAnswer(userSelectedAnswer);
        setStudentAnswer(studentAnswer);
	}
	
	public double getValue(){ // done
		Answer rightAnswer = getRightAnswer();
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
