import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class ExamTaker {

    public static void main( String []args) throws FileNotFoundException {

        //START BY TAKING INPUT OF USER FIRST AND LAST NAME
        Scanner user_input = new Scanner(System.in);

        System.out.println("Welcome to the exam, we need to collect some information first.\n");
        System.out.println("What is your first name?");

        String name = user_input.next();

        System.out.println("What is your last name?");
        String lastName = user_input.next();
        //-----------------------------------------------------------------------------------------


        //LOAD THE REORDERED EXAM FILE "stored_exam.txt" was the correct file for this
        System.out.println("===================================================\n");


        //End of loading and printing the exam -------------------------------------------------------

        //GEtting the name of the exam from the student
        System.out.println("We just need to verify that exam you are taking, please enter in the exam name");
        System.out.println("NOTE: please make sure this is correct as it has to do with your grading");
        System.out.println("Exam Name: (example: stored_exam)");
        String eName = user_input.next();
        System.out.println("Thanks for that! Now we can start the test :)");
        //----------------------------------------------------------------------------------------------

        File examFile = new File(String.format("./src/%s.txt", eName));
        //START collecting the student answers


        Scanner examScanner = null;

        try {
            examScanner = new Scanner(examFile);
        }
        //catch the exception
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        Exam exam1 = new Exam(examScanner);

        exam1.print();

        for(int i=1; i < exam1.getNumberOfQuestions()+1; i++) {
            exam1.getAnswerFromStudent(i);

        }
        //FINISH TAKING ANSWERS FROM STUDNETS --------------------------------------------------------


        //SAVING THE STUDENT ANSWERS TO A FILE "stored_answersHW4.txt
        PrintWriter answer_writer = null;
        try {
            answer_writer  = new PrintWriter(lastName+"_answer.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        exam1.saveStudentAnswers(answer_writer, name, lastName, eName);
        answer_writer.close();

        File ans = new File(lastName+"_answer.txt");
        //FINISH SAVING STUDENT ANSWERS TO FILE stored_answerHW4.txt ----------------------------------


        //MY SOLUTION TO GETTING THE ANSWERS THAT WERE SKIPPED/Ask if they want to keep answers blank
        Scanner s = new Scanner(ans);

        int i = 1, j = 1, k = 1;
        int loopCounter = 0;
        String skipSA = "n", skipMCSA = "n", skipMCMA = "n";
        int questionNumb = 1;
        int counter = 1;
        int prob1 = 0, prob2 = 0, prob3 = 0;
        int tes = exam1.getNumberOfQuestions();
        while(loopCounter == 0 )
        {
            String line = s.nextLine();


            //System.out.println(j);

            if(line.equalsIgnoreCase("SAAnswer") ) {

                //  System.out.println(line);
                if(counter <= tes)
                    prob1 = counter;

                counter++;

                if(s.nextLine().equals("") && skipSA.compareToIgnoreCase("n") == 0) {


                    System.out.println("HEY it looks like you didnt put an answer for SA question");
                    System.out.println("Would you like to leave this question blank? 'y' for Yes, 'n' for No");
                    skipSA = user_input.next();

                    if(skipSA.compareToIgnoreCase("n") == 0) {

                        System.out.println("Ok, go ahead and enter in your answer, if you would like to skip again press 's'");
                        exam1.getAnswerFromStudent(prob1);
                        PrintWriter fin = new PrintWriter(lastName+"_answer.txt");
                        exam1.saveStudentAnswers(fin, name, lastName,eName);
                        i = 1;
                    }else{
                        skipSA = "y";

                    }

                }else{
                    i = 0;
                }


            }
            if(line.equalsIgnoreCase("MCSAAnswer") && skipMCSA.compareToIgnoreCase("n") == 0) {

                // System.out.println(line);
                if(counter <= tes)
                    prob2 = counter;

                counter++;

                if(s.nextLine().equals("") && skipMCSA.compareToIgnoreCase("n") == 0 ) {

                    System.out.println("HEY it looks like you didnt put an answer for MCSA Question");
                    System.out.println("Would you like to leave this question blank? 'y' for Yes, 'n' for No");
                    skipMCSA = user_input.next();

                    if(skipMCSA.compareToIgnoreCase("n") == 0) {

                        System.out.println("Ok, go ahead and enter in your answer, if you would like to skip again press 's' ");
                        j = 1;
                        exam1.getAnswerFromStudent(prob2);
                        PrintWriter fin = new PrintWriter(lastName+"_answer.txt");
                        exam1.saveStudentAnswers(fin, name, lastName,eName);

                    }else{
                        skipMCSA = "y";
                        j = 0;
                    }


                }else{
                    j = 0;
                }

            }
            if(line.equalsIgnoreCase("MCMAAnswer")) {

                // System.out.println(line);
                int number = s.nextInt();

                if(counter <= tes)
                    prob3 = counter;

                counter++;

                if(number == 0 && skipMCMA.compareToIgnoreCase("n") == 0 ) {

                    System.out.println("HEY it look like you didnt put an answer for MCMA");
                    System.out.println("Would you like to leave this question blank? 'y' for Yes, 'n' for No");
                    skipMCMA = user_input.next();

                    if(skipMCMA.compareToIgnoreCase("n") == 0) {
                        System.out.println("Ok, go ahead and enter in your answer, if you would like to skip again press 's' ");
                        exam1.getAnswerFromStudent(prob3);
                        PrintWriter fin = new PrintWriter(lastName+"_answer.txt");
                        exam1.saveStudentAnswers(fin, name, lastName,eName);
                        k = 1;
                    }else{
                        skipMCMA = "y";
                    }

                }else{
                    k = 0;
                }

            }


            if((i == 0) && (j == 0) && (k == 0)){
                loopCounter = 1;
            }

            if(!s.hasNextLine()){

                s.close();
                s = new Scanner(ans);
                counter = 1;
            }

        }


        System.out.println("Would you like to change any of answers to questions? Enter y for YES, and n for NO: ");

        String cont = user_input.next();

        int it = 0;

        String finalC = "n";

        if(cont.equalsIgnoreCase("n"))
            finalC = "y";

        while(cont.compareToIgnoreCase("y") == 0 || finalC.compareToIgnoreCase("n") == 0){

            if(it > 0){
                System.out.println("Alright next question you would like to change?: ");
            }else{
                System.out.println("Cool, so which Question did you want to change answers to?");
            }

            int qNum = user_input.nextInt();
            exam1.getAnswerFromStudent(qNum);

            PrintWriter fin = new PrintWriter("./src/"+ lastName+"_answer.txt");
            exam1.saveStudentAnswers(fin, name, lastName,eName);

            System.out.println("Ok, got it, now would you like to change any more? 'y' for yes and 'n' for no: ");
            cont = user_input.next();
            it++;

            if(cont.equalsIgnoreCase("n")){
                System.out.println("This is your last chance to go back, if you're sure and" +
                        " ready to submit, please hit 'y' if you want to go back and change hit 'n");
                finalC = user_input.next();
            }


        }

        System.out.println("Everything should have saved properly, Thanks " + name + ", Have a good day!");


    }


}
