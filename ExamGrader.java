import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamGrader {
    public static void main(String[] args){
        // Get the files from here.
        /*
        DESCRIPTION : Load up an Exam file and an Answer file, confirming that they are a matched set
                      If only an Answer file is provided, perhaps as an optional command-line argument, then
                      automatically load up the corresponding Exam file
         */
        System.out.println("\n=================================================");
        System.out.println("  EXAMGRADER HW DESCRIPTION");
        System.out.println("  1. Load up an Exam file and an Answer file.\n");
        System.out.println("  This program load the student answer text file first");
        System.out.println("  and then load the exam file that is specified in the");
        System.out.println("  answer text file. Also, as the description mentions,");
        System.out.println("  we have to 'confirm' so that this actually prompt the,");
        System.out.println("  exam name, and then compare both exam's name.");
        System.out.println("===================================================");



        File studentAnswerFile = new File("./src/Lim_answer.txt");
        Scanner answerScanner = null;
        try {
            answerScanner = new Scanner(studentAnswerFile);
        }
        //catch the exception
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }


        String studentName;
        String examName;
        studentName = answerScanner.nextLine();
        examName = answerScanner.nextLine();

        System.out.println("Exam Name in the student answer file: " + examName);
        System.out.print("Type exam name to confirm: ");
        Scanner scanner = new Scanner(System.in);
        String inputExamName = scanner.next();

        if(!inputExamName.equals(examName)) {
            System.out.println("Your input exam name is different than the exam name in the answer text file. Please re try.");
            System.out.println("Exiting the program... ");
            return;
        }

        String examPath = String.format("./src/%s.txt", examName);

        System.out.println("Loading exam: " + examPath);
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

        // todo
        exam1.restoreStudentAnswers(answerScanner);



        int numQuestions = exam1.getNumberOfQuestions();

        // From here we store the information that should be in csv file into an ArrayList.
        ArrayList<String> l = new ArrayList<String>();

        l.add(studentName);
        //l.add(studentUID);
        l.add(examName);



        System.out.println("\n=================================================");
        System.out.println("  EXAMGRADER HW DESCRIPTION");
        System.out.println("  2. Evaluate the answers, and report the results to the screen.");
        System.out.println("===================================================\n");

        System.out.println("**** Score Report ****");
        double score;
        String scoreString;
        for (int i=0; i<numQuestions; i++) {
            score = exam1.getQuestion(i).getValue();
            scoreString = new DecimalFormat("#0.0").format(score);
            System.out.println("Q"+ i + ": " + scoreString);
            l.add(scoreString);
        }
        System.out.println("Total Score: " + new DecimalFormat("#0.0").format(exam1.getValue()) + "\n");
        l.add(Double.toString(exam1.getValue()));



        System.out.println("\n=================================================");
        System.out.println("  EXAMGRADER HW DESCRIPTION");
        System.out.println("  3. Store the results to a CSV ( comma separated values )");
        System.out.println("===================================================\n");


        PrintWriter writer = null;
        studentName = studentName.replaceAll("\\s+","");
        studentName = studentName.toLowerCase();
        String csvPath = String.format("./src/%s_results.csv", studentName);
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

        // now print the contents.
    }
}
