import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MCMAQuestion extends MCQuestion{
    protected ArrayList<Answer> studentAnswer; // answer array list.
    private double baseCredit;

    public MCMAQuestion(){
        studentAnswer = null;
        baseCredit = 0;
    }

    public MCMAQuestion(String text, double maxValue, double baseCredit) {
        super();
        this.text = text;
        this.maxValue = maxValue;
        this.studentAnswer = new ArrayList<Answer>();
        this.answers = new ArrayList<MCAnswer>();
    }

    public void save(PrintWriter writer) {
        writer.write(text + "\n");
        // get the answer and print out.
        int numOfAnswers = answers.size();
        writer.write(Double.toString(baseCredit) + "\n");
        writer.write(Integer.toString(numOfAnswers) + "\n");
        for (MCAnswer answer : answers) {
            answer.save(writer);
        }
        writer.write("\n");
    }

    public void restoreStudentAnswers(String s, Scanner scanner) {
        System.out.println("sout!!!: " + s);
        int numOfAnswers = Integer.parseInt(s);

        double score = 0.0;
        String ansText = "";
        System.out.println("STUDENT MULTIPLE ANSWERS: ");
        for(int i=0; i < numOfAnswers; i++) {
            ansText = scanner.nextLine();

            System.out.println("- "+ ansText);
            for(MCAnswer ans: answers) {
                if(ans.text.equals(ansText)){
                    score = ans.creditIfSelected;
                }
            }
            //System.out.println(score + " " + ansText);
            MCMAAnswer ans = new MCMAAnswer(ansText, score);
            ans.setSelected(true);
            studentAnswer.add(ans);
        }
    }


    public void saveStudentAnswers(PrintWriter writer) {
        writer.write("MCMAAnswer\n");
        writer.write(Integer.toString(studentAnswer.size()) + "\n");
        for (Answer ans: studentAnswer) {
            if(ans instanceof MCMAAnswer) {
                writer.write(((MCMAAnswer) ans).text + "\n");
            }
        }
    }

/*
    public void getAnswerFromStudent() {
        System.out.println("(A ~ E)");
        Scanner userInput = ScannerFactory.getKeyboardScanner();

        // get the user input integer
        String userSelectedAnswers = userInput.nextLine();

        String[] arr = userSelectedAnswers.split(" ");
        MCAnswer ans = null;
        System.out.print("-  You answered: ");
        for ( String choice : arr) {
            if (choice.equals("A")) {
                ans = getAnswers().get(0);
            } else if (choice.equals("B")) {
                ans = getAnswers().get(1);
            } else if (choice.equals("C")) {
                ans = getAnswers().get(2);
            } else if (choice.equals("D")) {
                ans = getAnswers().get(3);
            } else if (choice.equals("E")) {
                ans = getAnswers().get(4);
            } else {
                System.out.println("Your answer is not on the answer list. Your input is wrong.");
                return;
            }
            System.out.print(choice + " ");

            ans.setSelected(true);
            studentAnswer.add(ans);
        }
        System.out.println("");
    }
*/

    public void getAnswerFromStudent() {
        System.out.println("(A ~ E) or 's' to skip");
        Scanner userInput = ScannerFactory.getKeyboardScanner();
        // get the user input integer
        String userSelectedAnswers = userInput.nextLine();
        String[] arr = userSelectedAnswers.split(" ");
        MCAnswer ans = null;
        System.out.print("-  You answered: ");
        for ( String choice : arr) {
            if (choice.equals("A")) {
                ans = getAnswers().get(0);
            } else if (choice.equals("B")) {
                ans = getAnswers().get(1);
            } else if (choice.equals("C")) {
                ans = getAnswers().get(2);
            } else if (choice.equals("D")) {
                ans = getAnswers().get(3);
            } else if (choice.equals("E")) {
                ans = getAnswers().get(4);
            } else if (choice.compareToIgnoreCase("s") == 0){
                System.out.println("Skipped MCMA Question");
                return;
            } else {
                System.out.println("Your answer is not on the answer list. Your input is wrong.");
                return;
            }
            System.out.print(choice + " ");
            ans.setSelected(true);
            studentAnswer.add(ans);
        }
        System.out.println("");
    }


    public MCMAQuestion(Scanner scanner) {
        super();
        maxValue = Double.parseDouble(scanner.nextLine());
        //System.out.println(maxValue);
        text = scanner.nextLine();
        //System.out.println(text);
        baseCredit = Double.parseDouble(scanner.nextLine());
        studentAnswer = new ArrayList<Answer>();
        answers = new ArrayList<MCAnswer>();
        int numOfexamples = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < numOfexamples; i++) {
            double score = Double.parseDouble(scanner.next());
            String ansText = scanner.nextLine();
            ansText = ansText.trim();
            MCMAAnswer ans = new MCMAAnswer(ansText, score);
            answers.add(ans);
        }
    }


    public double getValue(){
        //ArrayList<MCAnswer> answers = getAnswers();
        //System.out.println("MCMAQesution get value");
        double sum = 0.0;
        for (Answer ans: studentAnswer) {
          //  System.out.println("for loop");
            if (ans instanceof MCMAAnswer){
                if(((MCMAAnswer) ans).selected) {
            //        System.out.print("Selected answer: ");
                    //System.out.println(((MCMAAnswer) ans).text);
                    sum += ((MCMAAnswer) ans).getCreditIfSelected();
                }
            }
        }

        sum += baseCredit;
        sum = sum * maxValue;

        return sum;
    }
    public Answer getNewAnswer(String text, double creditIfSelected) {
        return new MCMAAnswer(text, creditIfSelected);
    }
}
