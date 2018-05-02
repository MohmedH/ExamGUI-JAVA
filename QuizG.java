import javax.swing.*;
import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class QuizG extends JFrame{
    JPanel p=new JPanel();
    CardLayout cards=new CardLayout();
    int numQs;
    int wrongs=0;
    int total=0;
    int counter = 0;
    int mcMA = 0;
    int cont = 1;
    int openin = 0;
    String firstName;
    String examName;
    int numberOFQ = 0;

    List<String> ques = new ArrayList<String>(); //HOLDS THE QUESTIONs STRING
    List<String> title = new ArrayList<String>();
    List<List<String>> choicess = new ArrayList<List<String>>(); //HOLD ANSWERS
    List<RadioQuestion> tes = new ArrayList<RadioQuestion>();
    List<List<String>> ansInput = new ArrayList<List<String>>(); //HOLD THE STUDENT ANSWERS
    List<String> qTypes = new ArrayList<String>(); //HOLD THE TYPE OF QUESTIONS TO KEEP THE ORDER
    List<Integer> indenti = new ArrayList<Integer>(); //FILLED WITH 0 or 1 Indentifier for MCMA
    List<Integer> mcMAA = new ArrayList<Integer>(); //HOLD ALL STUDNET MCMA CHOICES
    List<Integer> numAns = new ArrayList<Integer>();


    public static void main(String args[], String nN, String fN){

        //TAKES IN TWO STRINGS, Name of student, and name of the exam from first page of GUI
        new QuizG(nN,fN);
    }

    //DRIVER TO MAKE THE ENTIRE TEST
    public QuizG(String nN, String fN) {

        super("TEST");
        setResizable(true);
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        firstName = nN;
        examName = fN;

        //OLD STUFF FROM EXAM TAKE TO READ IN FILE
            //-------------------------------------------------------------------------------------
            File examFile = new File(fN+".txt");

            Scanner examScanner = null;

            try {
                examScanner = new Scanner(examFile);
            }
            //catch the exception
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Exam exam1 = new Exam(examScanner);

            examScanner.close();
            //------------------------------------------------

            try {
                examScanner = new Scanner(examFile);
            }
            //catch the exception
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // exam1.print()
            while (examScanner.hasNextLine()) {
                String line = examScanner.nextLine();

                if (line.equalsIgnoreCase("SAQuestion")) {
                    qTypes.add("SAQuestion");
                    examScanner.nextLine();
                    String q = examScanner.nextLine();
                    //System.out.println(q);
                    ques.add(q);
                    String choi = examScanner.nextLine();
                    List<String> temp = new ArrayList<String>();
                    temp.add(choi);
                    choicess.add(temp);
                    indenti.add(0);
                    numberOFQ++;


                }
                if (line.equalsIgnoreCase("MCMAQuestion")) {
                    qTypes.add("MCMAQuestion");
                    examScanner.nextLine();
                    String q = examScanner.nextLine();
                    //System.out.println(q);
                    ques.add(q);
                    examScanner.nextLine();
                    examScanner.nextLine();
                    List<String> temp = new ArrayList<String>();
                    for (int i = 0; i < 5; i++) {
                        String choi = examScanner.nextLine();
                        String[] ansArr = choi.split(" ", 2);
                        //System.out.println(ansArr[1]);
                        temp.add(ansArr[1]);
                    }

                    choicess.add(temp);
                    indenti.add(1);
                    numberOFQ++;


                }
                if (line.equalsIgnoreCase("MCSAQuestion")) {
                    qTypes.add("MCSAQuestion");
                    examScanner.nextLine();
                    String q = examScanner.nextLine();
                    ques.add(q);
                    examScanner.nextLine();
                    List<String> temp = new ArrayList<String>();
                    for (int i = 0; i < 5; i++) {
                        String choi = examScanner.nextLine();
                        String[] ansArr = choi.split(" ", 2);
                        temp.add(ansArr[1]);
                    }

                    choicess.add(temp);
                    indenti.add(0);
                    numberOFQ++;


                }
                if (line.equalsIgnoreCase("NumQuestion")) {
                    qTypes.add("NumQuestion");
                    examScanner.nextLine();
                    String q = examScanner.nextLine();
                    ques.add(q);
                    String choi = examScanner.nextLine();
                    List<String> temp = new ArrayList<String>();
                    temp.add(choi);
                    choicess.add(temp);
                    indenti.add(0);
                    numberOFQ++;

                }

            }

            //GET THE NUMBER OF QUESTIONS IN THE EXAM
            numQs = exam1.getNumberOfQuestions();
            numQs = numberOFQ;

            //CALL RadioQuestion class to make a GUI page for each question
            for (int i = 0; i < numQs; i++) {
                int num = indenti.get(i);
                if (num == 1) {
                    mcMA = 1;
                }
                tes.add(new RadioQuestion(ques.get(i), choicess.get(i), num, this));
                mcMA = 0;
            }

            //SET MAIN GUI PAGE W/Layout cardlayout
            p.setLayout(cards);

            //START ADDING IN Each question panel to main panel
            for (int i = 0; i < numQs; i++) {
                p.add(tes.get(i), "q" + i);
            }

            //SHOW THE FIRST PANEL
            cards.show(p, "q" + counter);
            counter += 1;
            add(p);
            setVisible(true);
        }

        //WILL SHOW NEXT QUESTION using cardlayout
    public void next(){

        cards.next(p);
        cont+=1;

    }
    //WILL SHOW PREVIOUS Question using cardlayout
    public void previous(){

        cards.previous(p);
        cont-=1;

    }
    //CALL SHOWSUMMARY METHOD
    public void submit(){

        showSummary();
    }

    //THIS IS THE AFTER SUBMIT IS HIT, WILL START writing to a new file and then exit
    public void showSummary(){
        JOptionPane.showMessageDialog(null,"All Done :)\nAnd It's been saved");

        PrintWriter pw = null;
        try{
            pw = new PrintWriter(firstName +"_AnswerFile.txt"); //CHANGE SO IT SAVES TO NAME_ANSWER.tXT
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Instant timestamp = Instant.now();
        timestamp.toString();
        pw.write(firstName+"\n"+ examName +"\n" +timestamp+ "\n\n");

        for(int i = 0; i < qTypes.size(); i++){

            pw.write(qTypes.get(i)+"\n");

            if(ansInput.size() == 0) {
                pw.write("null" + "\n");
            }else if(i < ansInput.size()) {
                for(int n = 0; n < ansInput.get(i).size(); n++){
                    pw.write(ansInput.get(i).get(n) + "\n");
                }
            }else {
                pw.write("null" + "\n");
            }

            pw.write("\n");
        }
        pw.close();


        System.exit(0);
    }
}