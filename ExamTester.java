/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ExamTester {


    public static void main(String []args) {
        // print out author's information
        System.out.println("\nCS-362 Project 1");
        System.out.println("Name: Seho Lim");
        System.out.println("ACCC account name: slim");
        System.out.println("netID: slim67\n");
        // Create a scanner instances and try to print out.

        // Get the files from here.
        File examFile = new File("src/exam.txt");
        File answerFile = new File("src/student_answer.txt");


        //Path source = Paths.get("exam_data.txt");\
        Scanner examScanner = null;
        try {
            examScanner = new Scanner(examFile);
        }
        //catch the exception
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  1.  Create an Exam from an input data file.");
        System.out.println("===================================================\n");
        Exam exam1 = new Exam(examScanner);
        System.out.println("...... Created Exam.");

        exam1.print();


        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  2.  Reorder the Exam.");
        System.out.println("===================================================\n");

        exam1.reorderQuestions();
        exam1.reorderMCAAnswers(-1);
        exam1.print();


        PrintWriter writer = null;

        try {
            writer = new PrintWriter("src/stored_exam.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  3.  Save the reordered Exam into a different file.");
        System.out.println("===================================================\n");

        exam1.save(writer);

        System.out.println("...... Saved this Exam.");

        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  4. Get student answers for all the questions.");
        System.out.println("===================================================\n");
        for(int i=1; i < exam1.getNumberOfQuestions()+1; i++) {
            exam1.getAnswerFromStudent(i);
        }

        System.out.println("**** Score Report Before Reloading Exam and Answers****");
        System.out.println("Q1: " + exam1.getQuestion(0).getValue());
        System.out.println("Q2: " + exam1.getQuestion(1).getValue());
        System.out.println("Q3: " + exam1.getQuestion(2).getValue());
        System.out.println("Total Score: " + exam1.getValue() + "\n");

        //System.out.println("Total Score: " + exam1.getValue());
        PrintWriter answer_writer = null;
        try {
            answer_writer  = new PrintWriter("src/stored_answer.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  5.  Store the student answers in an answer file.");
        System.out.println("===================================================\n");

        exam1.saveStudentAnswer(answer_writer);

        System.out.println("...... Saved your answers.");

        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  6.  Clear the existing Exam and student answers in memory.");
        System.out.println("===================================================\n");

        exam1 = null;
        writer = null;
        answer_writer = null;
        examScanner = null;
        examFile = null;

        System.out.println("...... Cleared the previous exams and ansers.\n\n");

        examFile = new File("src/stored_exam.txt");
        answerFile = new File("src/stored_answer.txt");

        Scanner answerScanner = null;
        try {
            answerScanner = new Scanner(answerFile);
        }
        //catch the exception
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            examScanner = new Scanner(examFile);
        }
        //catch the exception
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  7.  Load the revised Exam and corresponding student answer files.");
        System.out.println("===================================================\n");

        System.out.println("\n******* reload exam *******");

        exam1 = new Exam(examScanner);
        System.out.println("\n******* reload answers *******");
        exam1.restoreStudentAnswers(answerScanner);


        System.out.println("\n=================================================");
        System.out.println("  HW DESCRIPTION");
        System.out.println("  8.  Grade the Exam and report the results.");
        System.out.println("===================================================\n");

        System.out.println("\n**** Score Report After Reloading Exam and Answers ****");

        System.out.println("Q1: " + exam1.getQuestion(0).getValue());
        System.out.println("Q2: " + exam1.getQuestion(1).getValue());
        System.out.println("Q3: " + exam1.getQuestion(2).getValue());
        System.out.println("Total Score: " + exam1.getValue());

    }
}
