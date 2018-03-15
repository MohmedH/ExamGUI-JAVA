import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MCMAQuestion extends MCQuestion{
    private ArrayList<Answer> studentAnswer; // answer array list.
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
        writer.write(Double.toString(baseCredit));
        writer.write(Integer.toString(numOfAnswers) + "\n");
        for (MCAnswer answer : answers) {
            answer.save(writer);
        }
        writer.write("\n");
    }

    public void getAnswerFromStudent() {
        System.out.println("(A ~ E)");
        Scanner userInput = ScannerFactory.getKeyboardScanner();

        // get the user input integer
        String userSelectedAnswer = userInput.next();

        System.out.println("-    You answered: "+ userSelectedAnswer + ".");
        // do the standard input and then save it as studentAnswer.
        MCAnswer ans;
        if (userSelectedAnswer.equals("A")) {
            ans = getAnswers().get(0);
        } else if (userSelectedAnswer.equals("B")) {
            ans = getAnswers().get(1);
        } else if (userSelectedAnswer.equals("C")) {
            ans = getAnswers().get(2);
        } else if (userSelectedAnswer.equals("D")) {
            ans = getAnswers().get(3);
        } else if (userSelectedAnswer.equals("E")) {
            ans = getAnswers().get(4);
        } else {
            System.out.println("Your answer is not on the answer list. Your input is wrong.");
            return;
        }
        //setAnswersNotSelected();
        ans.setSelected(true);
        setStudentAnswer(ans);
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
            MCMAAnswer ans = new MCMAAnswer(ansText, score);
            answers.add(ans);

        }
    }


}
