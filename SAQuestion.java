/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.PrintWriter;
import java.util.Scanner;

public class SAQuestion extends Question{

    public SAQuestion(String text, double maxValue){
        super(text, maxValue);
    }

    public SAQuestion(Scanner scanner) {
        maxValue = Double.parseDouble(scanner.nextLine());
        //System.out.println(maxValue);
        text = scanner.nextLine();
        //System.out.println(text);
        rightAnswer = new SAAnswer(scanner);

    }

    public Answer getNewAnswer() {

        return new SAAnswer();
    }

    public void save(PrintWriter writer) {
        writer.write(text + "\n");
        //System.out.println(rightAnswer.getClass().getName());
        rightAnswer.save(writer);

    }

    public Answer getNewAnswer(String text) {
        return new SAAnswer(text);
    }

    public void saveStudentAnswer(PrintWriter writer) {
        writer.write("SAAnswer\n");
        if(studentAnswer instanceof SAAnswer) {
            writer.write(((SAAnswer) studentAnswer).text + "\n");
        }
    }

    public void restoreStudentAnswers(String s, Scanner scanner) {
        //System.out.println("--SAAnswer restore answer:");
        System.out.println("- "+ s);
        studentAnswer = new SAAnswer(s);

    }
/*
    public void getAnswerFromStudent() {
        System.out.print("short answer: ");
        Scanner userInput = ScannerFactory.getKeyboardScanner();

        // get the user input integer
        String userSelectedAnswer = userInput.nextLine();

        System.out.println("-    You answered: "+ userSelectedAnswer + ".");
        // do the standard input and then save it as
        // studentAnswer.
        SAAnswer studentAnswer = new SAAnswer(userSelectedAnswer);
        setStudentAnswer(studentAnswer);
    }
*/
    public void getAnswerFromStudent() {
        System.out.print(" short answer (to skip press 's'): ");
        Scanner userInput = ScannerFactory.getKeyboardScanner();
        // get the user input integer
        String userSelectedAnswer = userInput.nextLine();
        if(userSelectedAnswer.compareToIgnoreCase("s") == 0){
            System.out.println("Skipped SA Question");
            SAAnswer studentAnswer = new SAAnswer("null");
            setStudentAnswer(studentAnswer);
            return;
        }else {
            System.out.println("-    You answered: " + userSelectedAnswer + ".");
            // do the standard input and then save it as
            // studentAnswer.
            SAAnswer studentAnswer = new SAAnswer(userSelectedAnswer);
            setStudentAnswer(studentAnswer);
        }
    }

    public double getValue() {
        Answer rightAnswer = getRightAnswer();
        if (rightAnswer == null) {
            return 0.0;
        }
        Answer studentAnswer = getStudentAnswer();
        if (studentAnswer == null) {
            return 0.0;
        }
        if (getStudentAnswer().getCredit(rightAnswer) > 0) {
            return getMaxValue();
        }
        else {
            return 0.0;
        }
    }
}
