/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.PrintWriter;
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
            ansText = ansText.trim();
            MCAnswer ans = new MCSAAnswer(ansText, score);
            //System.out.print(score);
            //System.out.println(ansText);
            //System.out.println(answers);
            //System.out.println(ans);
            answers.add(ans);
        }

    }

    public void setRightAnswer(Answer answer) {
        rightAnswer = answer;
    }

    public void saveStudentAnswers(PrintWriter writer) {
        writer.write("MCSAAnswer\n");
        if(studentAnswer instanceof MCSAAnswer) {
            writer.write(((MCSAAnswer) studentAnswer).text + "\n");
        }
    }

    public Answer getNewAnswer() {
        return new MCSAAnswer();
    }

    public void save(PrintWriter writer) {
        writer.write(text + "\n");
        // get the answer and print out.
        int numOfAnswers = answers.size();
        writer.write(Integer.toString(numOfAnswers) + "\n");
        for (int i=0; i < numOfAnswers; i++) {
            answers.get(i).save(writer);
        }
        writer.write("\n");

    }

    public void restoreStudentAnswers(String s, Scanner scanner) {
        System.out.println("- "+ s);
        //System.out.println("-- MCSAQuestion resotore answer");
        double score = 0.0;
        for(MCAnswer ans: answers) {
            if(ans.text.equals(s)){
                score = ans.creditIfSelected;
            }
        }
        //System.out.println(score + " " + ansText);
        MCSAAnswer ans = new MCSAAnswer(s, score);
        ans.setSelected(true);
        setStudentAnswer(ans);

    }

    public Answer getNewAnswer(String text, double creditIfSelected) {
        return new MCSAAnswer(text, creditIfSelected);
    }

    /** ask a student for an answer */
    public void getAnswerFromStudent() {
        System.out.println("(A ~ E) press 's' to skip this question");
        Scanner userInput = ScannerFactory.getKeyboardScanner();
        // get the user input integer
        String userSelectedAnswer = userInput.nextLine();
        System.out.println("-    You answered: "+ userSelectedAnswer + "");
        // do the standard input and then save it as studentAnswer.
        MCAnswer ans = null;
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
        } else if(userSelectedAnswer.compareToIgnoreCase("s") == 0) {
            System.out.println("Skipped MCSA Question");
            MCSAAnswer answer = new MCSAAnswer("null", 0);
            answer.setSelected(true);
            setStudentAnswer(answer);
            return;
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
        //ArrayList<MCAnswer> answers = getAnswers();
        Answer studentAnswer = getStudentAnswer();
        if (studentAnswer instanceof MCSAAnswer){
            if(((MCSAAnswer) studentAnswer).selected) {
                return ((MCSAAnswer) studentAnswer).getCreditIfSelected() * maxValue ;
            }
        }
        return 0.0;
    }
}
