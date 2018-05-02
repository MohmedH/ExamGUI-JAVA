/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exam {
    public String text; // name of the exam.
    private ArrayList<Question> questions; // questions array list.
    private int N; // this is number of questions.


    /** Exam constructor */
    public Exam(String name) {
        text = name;
        questions = new ArrayList<Question>();
        N = 5; // As the HW description mentioned the default value of N is 5
    }

    public Exam(Scanner scanner) {
        text = scanner.nextLine();
        questions = new ArrayList<Question>();
        while(scanner.hasNext()) {
            Question q = _createQuestion(scanner);
            if (q != null) {
                addQuestion(q);
            }
        }
    }


    public Question _createQuestion(Scanner scanner) {
        String tok = scanner.nextLine();

        if (tok.equals("SAQuestion")) {
            return new SAQuestion(scanner);
        }
        if (tok.equals("MCMAQuestion")) {
            return new MCMAQuestion(scanner);
        }
        if (tok.equals("MCSAQuestion")) {
            return new MCSAQuestion(scanner);
        }
        if (tok.equals("NumQuestion")){
            return new NumQuestion(scanner);
        }

        return null;
    }

    public void saveStudentAnswers(PrintWriter writer, String f, String l, String eNam) {
        //Scanner userscan = ScannerFactory.getKeyboardScanner();
        //System.out.print("your name: ");
        Instant timestamp = Instant.now();
        timestamp.toString();
        String username = f + " "+ l;
        String examNam = eNam;
        writer.write(username + "\n" + examNam + "\n" + timestamp + "\n\n");
        for (Question q : questions) {
            q.saveStudentAnswers(writer);
            writer.write("\n");
        }
        writer.flush();
    }


    public boolean isTokQuestion(String tok) {
        return (tok.equals("SAQuestion") || tok.equals("NumQuestion") || tok.equals("MCSAQuestion") || tok.equals("MCMAQuestion"));
    }


    public void save(PrintWriter writer) {
        writer.write(text + "\n\n");
        for (Question q : questions) {
            writer.write(q.getClass().getName() + "\n");
            writer.write(Double.toString(q.maxValue) + "\n");
            q.save(writer);
        }
        writer.flush();
    }


    public void restoreStudentAnswers(Scanner scanner) {
        System.out.println("RESTORING STUDENT ANSWER . . . . . ");
        scanner.nextLine();
        scanner.nextLine();
        while(scanner.hasNext()) {
            for (Question q : questions) {
                String examType = scanner.nextLine(); // Exam type.
                System.out.println("Q TYPE: " + examType);
                String ans = scanner.nextLine();
                if((ans.equals("0")&& examType.equals("MCMAAnswer")) || ans.equals("null") || ans.equals("0.0")){
                    System.out.println("- Student skipped this question.");
                    if (scanner.hasNext())
                        scanner.nextLine();
                }
                else {
                    System.out.println("- Student Answered:  " + ans);
                    q.restoreStudentAnswers(ans, scanner);
                    if (scanner.hasNext())
                        scanner.nextLine();
                }


                /*
                if(examType.equals("SAAnswer") || examType.equals("MCSAAnswer") || examType.equals("MCMAAnswer")) {
                    ans = scanner.nextLine();
                    //System.out.println("line105: " + ans);
                    if(examType.equals("MCMAAnswer") && ans.equals(0)) {
                        scanner.nextLine();
                    }

                }
                else {
                    scanner.nextLine();
                    ans = scanner.nextLine();
                    System.out.println("line115: " + ans);

                }

                */
            }

        }
    }

    /** It prints out the whole exam including questions and answers.*/
    public void print() {
        System.out.println("\n\nExam Name: " + text);
        System.out.println("-------------------------------------------------------------");

        int j = 0;
        // Iterate the Questions ArrayList and then print out each question and answers.
        for (Question q : questions) {
            System.out.println("");
            System.out.print("Q." + (j+1) + ": ");
            q.print();
            // Iterates the answers and print out.
            j++;
        }
        System.out.println("\n\nEnd of Exam. Congratulation!!");
        System.out.println("-------------------------------------------------------------");
    }


    public String getExamString() {
        String exam = "";
        exam += "\n\nExam Name: " + text;

        exam += "\n-------------------------------------------------------------";

        int j = 0;
        // Iterate the Questions ArrayList and then print out each question and answers.
        for (Question q : questions) {
            exam += "\n";
            exam += "Q." + (j+1) + ": ";
            exam += q.getExamString();
            // Iterates the answers and print out.
            j++;
        }
        exam += "\n\nEnd of Exam. Congratulation!!\n";
        exam += "-------------------------------------------------------------\n\n\n";
        return exam;
    }


    /** It takes a question instance and it to the Questions ArrayList*/
    public void addQuestion(Question q) {
        questions.add(q); // Add a question instance to the ArrayList.
    }


    /** It shuffles the questions ArrayList.*/
    public void reorderQuestions() {
        Collections.shuffle(questions);
    }


    /** It reorders the MCAAanswers */
    public void reorderMCAAnswers(int pos) {
        // if pos is negative shuffle all the answers of the MCQuestions.
        // if pos has value only the question in the position answers get shuffled.
        if (pos > questions.size()) {
            System.out.println("There is no such question number in this exam. Look at the exam again!");
            return;
        }

        if (pos < 0 ) {
            for(Question q : questions) {
                if(q instanceof MCQuestion) {
                    // type casting stuff here.
                    ((MCQuestion) q).reorderAnswers();
                }
            }
            return;
        }

        if (!(questions.get(pos) instanceof MCQuestion)) {
            System.out.println("This question's answers can't be shuffled.");
        }
    }


    /** creates a new Answer. Gets the answer from the std in and the answer is
     * stored in the studentAnswer field of the Question class.
     * */
    public void getAnswerFromStudent(int pos) {
        if(pos > questions.size()) {
            System.out.println("The questions number " + pos + "does not exists on this exam.");
            return;
        }
        if(pos < 1) {
            System.out.println("The question number is not right. It should be larger than 0");
            return;
        }
        System.out.print("Q" + pos +". Please type answer");
        questions.get(pos-1).getAnswerFromStudent();
    }


    /** Returns the total score of the exam.*/
    public double getValue() {
        double total_score = 0.0; // Initialize the total score as 0
        // Iterate the question instances and add all the scores.
        int i = 0;
        for (Question q : questions) {
            total_score += q.getValue();
            i++;
        }
        return total_score;
    }


    /** Takes an integer and returns the question from the array list. */
    public Question getQuestion(int i) {
        if (i >= questions.size()) {
            System.out.println("index out of bound!!");
            return null;
        }
        return questions.get(i); // returns the ith question instance.
    }
	public void removeQuestion(int index){
		questions.remove(index);
	}

    /** returns the number of the questions */
    public int getNumberOfQuestions() {
        return questions.size();
    }
}
