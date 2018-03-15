/*
 Class : CS-362
 text  : Seho Lim
 netID : slim67
 */

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public abstract class Question {
    protected String text; // Question name.
    protected Answer rightAnswer;
    protected Answer studentAnswer;
    protected double maxValue; // Indicate how much this question is currently valued.


    /** Question constructor. It receives the question name.*/
    protected Question(String qName, double val) {
        text = qName;
        maxValue = val;
    }

    public Question() {
        text = null;
        rightAnswer = null;
        studentAnswer = null;
        maxValue = 0.0;
    }

    public Question(Scanner scanner) {
        maxValue = Double.parseDouble(scanner.next());
        text = scanner.next();
    }

    public void restoreStudentAnswers(Scanner scanner) {}

    abstract public void save(PrintWriter writer);

    public void print() {
        System.out.println(text);

    }

    public void setRightAnswer() {}

    abstract public Answer getNewAnswer();

    public void saveStudentAnswer(PrintWriter writer){}

    abstract public void getAnswerFromStudent();

    public Answer getRightAnswer() {
        return rightAnswer;
    }

    public double getValue() {
        return 0.0;
    }

    public String getTheQuestion() {
        return text;
    }

    public Answer getStudentAnswer() {
        return studentAnswer;
    }

    public double getMaxValue() {
        return maxValue;
    }


    public void setStudentAnswer(Answer ans) {
        studentAnswer = ans;
    }

}
