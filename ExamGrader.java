import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
// Team31
// Seho Lim

public class ExamGrader {


    public static JFrame frame;

    public static void main(String[] args){

        class MyWindowListener implements WindowListener {

            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }

            public void windowOpened(WindowEvent arg0) {}
            public void windowClosed(WindowEvent arg0) {}
            public void windowIconified(WindowEvent arg0) {}
            public void windowDeiconified(WindowEvent arg0) {}
            public void windowActivated(WindowEvent arg0) {}
            public void windowDeactivated(WindowEvent arg0) {}

        }

        // Get the files from here.
        /*
        DESCRIPTION : Load up an Exam file and an Answer file, confirming that they are a matched set
                      If only an Answer file is provided, perhaps as an optional command-line argument, then
                      automatically load up the corresponding Exam file
         */

        /*
        System.out.println("\n=================================================");
        System.out.println("  EXAMGRADER HW DESCRIPTION");
        System.out.println("  1. Load up an Exam file and an Answer file.\n");
        System.out.println("  This program load the student answer text file first");
        System.out.println("  and then load the exam file that is specified in the");
        System.out.println("  answer text file. Also, as the description mentions,");
        System.out.println("  we have to 'confirm' so that this actually prompt the,");
        System.out.println("  exam name, and then compare both exam's name.");
        System.out.println("===================================================");
*/
        frame=new JFrame();
        frame.addWindowListener(new MyWindowListener());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        while(true){
            JOptionPane.showMessageDialog(frame,"This will grade your exam. Please press OK.");


            String answerName = JOptionPane.showInputDialog(frame, "Please type answer file name. If you saved answer file in certain paths, inlcude path.\n Example: lim_answer.txt", "lim_answer.txt");
            if(answerName == null){
                JOptionPane.showMessageDialog(frame,"You didn't put any exam name. Exiting the program . . . ");
                System.exit(0);
            }

            //System.out.println("Please type answer file name. If you saved answer file in certain paths, inlcude path.");
            //System.out.print("-> (ex: Lim_answer.txt): ");
            //System.out.println("\n");

            String answerFilePath = String.format("%s", answerName);


            File studentAnswerFile = new File(answerFilePath);

            JOptionPane.showMessageDialog(frame, String.format("Loading answer file: %s", answerName));
            //System.out.println(String.format("Loading answer file: %s", answerName));

            Scanner answerScanner = null;
            try {
                answerScanner = new Scanner(studentAnswerFile);
            }
            catch(FileNotFoundException e) {
                JOptionPane.showMessageDialog(frame,"Error, your input was wrong");
                return;
            }


            String studentName;
            String examName;
            studentName = answerScanner.nextLine();
            examName = answerScanner.nextLine();

            int a = JOptionPane.showConfirmDialog(frame,"Is this exam right? ->" + examName + "\n Click Cancel if this is wrong. Click OK if it is correct.");
            if(a == JOptionPane.NO_OPTION){
                JOptionPane.showMessageDialog(frame,"You said no. Exiting the program ...");

                System.exit(0);
            }
            else if(a == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(frame,"You clicked cancel. Exiting the program ...");

                System.exit(0);
            }

            //System.out.println("Is this exam right? ->" + examName);
            //System.out.print("Please confirm. (y/n) ->");
            //String inputExamName = scanner.next();
            //System.out.println("");

        /*
        if(inputExamName.equals("n")) {
            System.out.println("You hit 'n'");
            System.out.println("Exiting the program ...");
            return;
        }
        else if(inputExamName.equals("y")) {
            System.out.println("You confirmed the Exam Name");
        }
        else {
            System.out.println("You hit the wrong command.");
            System.out.println("Exiting the program ...");
        }
*/
            String examPath = String.format("%s.txt", examName);

            JOptionPane.showMessageDialog(frame, String.format("Loading the exam %s", examPath));


            File examFile = new File(examPath);
            Scanner examScanner = null;
            try {
                examScanner = new Scanner(examFile);
            }
            //catch the exception
            catch(FileNotFoundException e) {
                e.printStackTrace();
            }

            Exam exam1 = new Exam(examScanner);

/*
            String examString = exam1.getExamString();
            JOptionPane.showMessageDialog(frame, "Here is the exam you answered to. :) ");
            JOptionPane.showMessageDialog(frame, examString);

            */

            exam1.restoreStudentAnswers(answerScanner);
            int numQuestions = exam1.getNumberOfQuestions();

            // From here we store the information that should be in csv file into an ArrayList.
            ArrayList<String> l = new ArrayList<String>();

            l.add(studentName);
            //l.add(studentUID);
            l.add(examName);

        /*
        System.out.println("\n=================================================");
        System.out.println("  EXAMGRADER HW DESCRIPTION");
        System.out.println("  2. Evaluate the answers, and report the results to the screen.");
        System.out.println("===================================================\n");
*/
            String scoreReport = "**** Score Report ****\n";
            //System.out.println("**** Score Report ****");
            double score;
            String scoreString;

            for (int i=0; i<numQuestions; i++) {
                score = exam1.getQuestion(i).getValue();
                //System.out.println("Question: " + exam1.getQuestion(i).text);
                scoreString = new DecimalFormat("#0.0").format(score);
                scoreReport += "Q"+ (i+1) + ": " + scoreString + "\n";
                l.add(scoreString);
            }
            scoreReport += "\nTotal Score: " + new DecimalFormat("#0.0").format(exam1.getValue()) + "\n";
            l.add(Double.toString(exam1.getValue()));
            JOptionPane.showMessageDialog(frame, scoreReport);

        /*
        System.out.println("\n=================================================");
        System.out.println("  EXAMGRADER HW DESCRIPTION");
        System.out.println("  3. Store the results to a CSV ( comma separated values )");
        System.out.println("===================================================\n");
        System.out.println("CREATING CSV FILE . . . . ");

        */
            JOptionPane.showMessageDialog(frame, "Now creating csv file. . .");
            PrintWriter writer = null;
            studentName = studentName.replaceAll("\\s+","");
            studentName = studentName.toLowerCase();
            String csvPath = String.format("%s_results.csv", studentName);
            try {
                writer = new PrintWriter(csvPath, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            for (int i=0; i < l.size(); i++) {
                writer.write(l.get(i));
                if(i < l.size()-1)
                    writer.write(",");
            }
            writer.flush();

            System.out.println("Created csv file: " + csvPath);
            JOptionPane.showMessageDialog(frame, "Created csv file: " + csvPath);




            int b = JOptionPane.showConfirmDialog(frame,"All the task is done do you want to Exit????");
            if(b == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(frame,"You said YES. Exiting the program ...");
                System.exit(0);
            }
            else if (b == JOptionPane.CLOSED_OPTION) {
                System.out.println("Closed by hitting the cross");
                System.exit(0);
            }

        }




        // now print the contents.
    }
}
