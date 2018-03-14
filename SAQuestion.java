/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

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

        return new Answer();
    }

    public Answer getNewAnswer(String text) {
        return new Answer();
    }

    public void getAnswerFromStudent() {
        System.out.print("short answer: ");
        Scanner userInput = new Scanner(System.in);

        // get the user input integer
        String userSelectedAnswer = userInput.nextLine();

        System.out.println("-    You answered: "+ userSelectedAnswer + ".");
        // do the standard input and then save it as
        // studentAnswer.
        SAAnswer studentAnswer = new SAAnswer(userSelectedAnswer);
        setStudentAnswer(studentAnswer);
    }

    public double getValue() {
        Answer rightAnswer = getRightAnswer();
        if (getStudentAnswer().getCredit(rightAnswer) > 0) {
            return getMaxValue();
        }
        else {
            return 0.0;
        }
    }
}
