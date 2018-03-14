/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.util.ArrayList;
import java.util.Scanner;

public class MCSAQuestion extends MCQuestion{
    public MCSAQuestion(String text, double maxValue) {
        super(text, maxValue);
    }

    public MCSAQuestion(Scanner scanner) {
        maxValue = Double.parseDouble(scanner.nextLine());
        //System.out.println(maxValue);
        text = scanner.nextLine();
        answers = new ArrayList<MCAnswer>();
        //System.out.println(text);
        int numOfExamples = Integer.parseInt(scanner.nextLine());
        double score = 0.0;
        for (int i=0; i < numOfExamples; i++) {
            score = Double.parseDouble(scanner.next());
            //scanner.nextLine();
            String ansText = scanner.nextLine();
            MCAnswer ans = new MCSAAnswer(ansText, score);
            //System.out.print(score);
            //System.out.println(ansText);
            //System.out.println(answers);
            //System.out.println(ans);
            answers.add(ans);
        }

    }

    public Answer getNewAnswer() {
        
        return new Answer();
    }

    public Answer getNewAnswer(String text, double creditIfSelected) {
        return new Answer();
    }

    /** ask a student for an answer */
    public void getAnswerFromStudent() {
        System.out.println("(A ~ E)");
        Scanner userInput = new Scanner(System.in);

        // get the user input integer
        String userSelectedAnswer = userInput.next();

        System.out.println("-    You answered: "+ userSelectedAnswer + ".");
        // do the standard input and then save it as studentAnswer.
        MCAnswer ans;
        if (userSelectedAnswer.equals("A")) {
            ans = getAnswers().get(0);
        } else if (userSelectedAnswer.equals("B")) {
            ans = getAnswers().get(1);
        } else if (userSelectedAnswer.equals("C")) {
            ans = getAnswers().get(2);
        } else if (userSelectedAnswer.equals("D")) {
            ans = getAnswers().get(3);
        } else if (userSelectedAnswer.equals("E")) {
            ans = getAnswers().get(4);
        } else {
            System.out.println("Your answer is not on the answer list. Your input is wrong.");
            return;
        }
        setAnswersNotSelected();
        ans.setSelected(true);
        setStudentAnswer(ans);
    }

    public void setAnswersNotSelected() {
        int numOfAnswers = getAnswers().size();
        ArrayList<MCAnswer> answers = getAnswers();
        for (int i =0; i < numOfAnswers; i ++){
            answers.get(i).setSelected(false);
        }
    }

    public double getValue(){
        ArrayList<MCAnswer> answers = getAnswers();
        double score = 0.0;
        for (int i = 0; i < answers.size(); i++ ) {
            if (answers.get(i).getSelected()){
                score += answers.get(i).getCredit(getRightAnswer());
            }
        }
        return score;
    }
}
