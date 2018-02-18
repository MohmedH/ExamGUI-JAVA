/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.util.Scanner;
public class ExamTester {


    public static void main(String []args) {
        // print out author's information
        System.out.println("\nCS-362 Project 1");
        System.out.println("Name: Seho Lim");
        System.out.println("netID: slim67");

        // Create an Exam instance.
        Exam exam1 = new Exam("2018_Summer");

        // create three Question instances.
        Question q1 = new Question("What is the most delicious thing in the world?");
        Question q2 = new Question("What do I want to do now?");
        Question q3 = new Question("what is the best tech company to work for?");

        // Add three created question instances to the exam.
        exam1.addQuestion(q1);
        exam1.addQuestion(q2);
        exam1.addQuestion(q3);

        // Create answers and add all the answers to each question.
        Answer q1_ans1 = new Answer("chicken");
        Answer q1_ans2 = new Answer("steak");
        Answer q1_ans3 = new Answer("lobster");
        Answer q1_ans4 = new Answer("bread");
        Answer q1_ans5 = new Answer("pork");
        q1_ans1.setValue(10, 0);
        q1_ans2.setValue(0, 0);
        q1_ans3.setValue(0, 0);
        q1_ans4.setValue(0, 0);
        q1_ans5.setValue(0, 0);

        Answer q2_ans1 = new Answer("video game");
        Answer q2_ans2 = new Answer("homework");
        Answer q2_ans3 = new Answer("working");
        Answer q2_ans4 = new Answer("playing");
        Answer q2_ans5 = new Answer("eating");
        q2_ans1.setValue(10, 0);
        q2_ans2.setValue(0, 0);
        q2_ans3.setValue(0, 0);
        q2_ans4.setValue(0, 0);
        q2_ans5.setValue(0, 0);

        Answer q3_ans1 = new Answer("google");
        Answer q3_ans2 = new Answer("apple");
        Answer q3_ans3 = new Answer("microsoft");
        Answer q3_ans4 = new Answer("amazon");
        Answer q3_ans5 = new Answer("twitter");
        q3_ans1.setValue(10, 0);
        q3_ans2.setValue(0, 0);
        q3_ans3.setValue(0, 0);
        q3_ans4.setValue(0, 0);
        q3_ans5.setValue(0, 0);

        // Add answers to the questions.
        q1.AddAnswer(q1_ans1);
        q1.AddAnswer(q1_ans2);
        q1.AddAnswer(q1_ans3);
        q1.AddAnswer(q1_ans4);
        q1.AddAnswer(q1_ans5);

        q2.AddAnswer(q2_ans1);
        q2.AddAnswer(q2_ans2);
        q2.AddAnswer(q2_ans3);
        q2.AddAnswer(q2_ans4);
        q2.AddAnswer(q2_ans5);

        q3.AddAnswer(q3_ans1);
        q3.AddAnswer(q3_ans2);
        q3.AddAnswer(q3_ans3);
        q3.AddAnswer(q3_ans4);
        q3.AddAnswer(q3_ans5);

        System.out.println("\n\n==========================================================================");
        System.out.println("\nHW DESCRIPTION:\nPrint the exam. This can be simple text\nprinting to the screen for now.");
        exam1.print();

        System.out.println("\n\n==========================================================================");
        System.out.println("\nHW DESCRIPTION:\nRandomly reorder the questions on the exam, and randomly\nreorder the answers to each" +
                "question. Then print the exam again.");

        exam1.reorderQuestions();
        q1.reorderAnswers();
        q2.reorderAnswers();
        q3.reorderAnswers();

        // print out the exam.
        exam1.print();

        System.out.println("\n\n==========================================================================");
        System.out.println("\nHW DESCRIPTION:\nSelect an answer to some or all of the\nquestions, as if a student had completed the exam.");

        // Take the user input for the all the questions.
        System.out.println("");
        for(int i=0; i < 3; i++) {
            System.out.print("Q" + i +". Please type answer 0 ~ 4 (int): ");
            Scanner userInput = new Scanner(System.in);
            // get the user input integer
            int userSelectedAnswer = userInput.nextInt();
            // get the Question in order.
            Question q = exam1.getQuestion(i);
            q.selectAnswer(userSelectedAnswer);
            System.out.println("Q" + i + " Your Answer: " + userSelectedAnswer);

        }

        // Total score of the exam.
        System.out.println("\n\n==========================================================================");
        System.out.println("\nHW DESCRIPTION:\nGrade the exam, by totaling the value of\nthe selected answers, and report the overall score," +
                "\nalong with the value contributed for each question.\n");

        System.out.println("Score");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Q0: " + exam1.getQuestion(0).getValue());
        System.out.println("Q1: " + exam1.getQuestion(1).getValue());
        System.out.println("Q2: " + exam1.getQuestion(2).getValue());
        System.out.println("Total Score: " + exam1.getValue());
    }
}
