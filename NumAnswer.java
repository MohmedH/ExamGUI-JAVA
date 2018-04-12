import java.io.PrintWriter;
import java.util.Scanner;

public class NumAnswer extends Answer{
	
	private double answer;
	public NumAnswer(double answer){ //done
		this.answer = answer;
	}
	
	public NumAnswer(Scanner scan){ //done
		answer = Double.parseDouble(scan.nextLine());
		
	}
	
	public void print(){
		
	}
	
	public double getCredit(Answer rightAnswer){ //done
		double range = Math.abs(((NumAnswer) rightAnswer).answer - answer);
		if (range <= 3) {
            return 1;
        }
        return 0.0;
	}
	
	public void save(PrintWriter writer){ //done
		writer.write(answer + "\n\n");
	}
	
	
	
}
