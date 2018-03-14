/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        Scanner examScanner;
        Scanner answerScanner;
        try {
            examScanner = new Scanner(examFile);
            answerScanner = new Scanner(answerFile);
        }
        //catch the exception
        catch(FileNotFoundException e) {
            e.printStackTrace();
            examScanner = null;
            answerScanner = null;

        }

        System.out.println("print out the exam constructor stuff");
        Exam exam1 = new Exam(examScanner);

        exam1.print();

        /*

        Reorder the exam!

        exam1.reorderQuestions();
        exam1.reorderMCAAnswers(-1);
        exam1.print();
        */
// TODO: 3/14/18  Finish the storing stuff here.
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("stored_exam.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        exam1.save(writer);
        System.out.println("The exam is saved. Can be loaded later.");





    }
}
