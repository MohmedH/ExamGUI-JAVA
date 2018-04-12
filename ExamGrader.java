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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please type answer file name. If you saved answer file in certain paths, inlcude path.");
        System.out.print("-> (ex: Lim_answer.txt): ");
        String answerName = scanner.next();
        System.out.println("\n");
        String answerFilePath = String.format("./src/%s", answerName);


        File studentAnswerFile = new File(answerFilePath);

        System.out.println(String.format("Loading answer file: %s", answerName));
        Scanner answerScanner = null;
        try {
            answerScanner = new Scanner(studentAnswerFile);
        }
        catch(FileNotFoundException e) {
            System.out.println("You input the wrong file name!!!!!!!!");
            System.out.println("Please input the right file name next time :-) ");
            return;
        }


        String studentName;
        String examName;
        studentName = answerScanner.nextLine();
        examName = answerScanner.nextLine();

        System.out.println("Exam Name in the student answer file: " + examName);
        System.out.print("Type exam name to confirm: ");

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
            System.out.println("Question: " + exam1.getQuestion(i).text);
            scoreString = new DecimalFormat("#0.0").format(score);
            System.out.println("Q"+ (i+1) + ": " + scoreString);
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
