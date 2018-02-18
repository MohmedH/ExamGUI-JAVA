/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.util.ArrayList;
import java.util.Collections;

public class Exam {
    private String text; // name of the exam.
    private ArrayList<Question> questions; // questions array list.
    private int N; // this is number of questions.


    /** Exam constructor */
    public Exam(String name) {
        text = name;
        questions = new ArrayList<Question>();
        N = 5; // As the HW description mentioned the default value of N is 5
    }


    /** It prints out the whole exam including questions and answers.*/
    public void print() {
        System.out.println("Exam Name: " + text);
        int j = 0;
        // Iterate the Questions ArrayList and then print out each question and answers.
        for (Question q : questions) {
            System.out.println("");
            System.out.println("Q." + j + ": " + q.getName());
            // Iterates the answers and print out.
            for (int i=0; i < N; i++) {
                q.print(i);
            }
            j++;
        }
    }


    /** It takes a question instance and it to the Questions ArrayList*/
    public void addQuestion(Question q) {
        questions.add(q); // Add a question instance to the ArrayList.
    }


    /** It shuffles the questions ArrayList.*/
    public void reorderQuestions() {
        Collections.shuffle(questions);
    }

    // TODO: 2/18/18  
    public void reorderMCAAnswers(int pos) {

    }

    // TODO: 2/18/18  
    public void getAnswerFromStudent(int pos) {

    }

    /** Returns the total score of the exam.*/
    public double getValue() {
        double total_score = 0.0; // Initialize the total score as 0
        // Iterate the question instances and add all the scores.
        for (Question q : questions) {
            total_score += q.getValue();
        }
        return total_score;
    }

    public Question getQuestion(int i) {
        return questions.get(i); // returns the ith question instance.
    }



}
