/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.File;
import java.io.FileNotFoundException;
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
        File examFile = new File("exam.txt");
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








    }
}
