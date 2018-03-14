import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class MCQuestion extends Question{
    protected ArrayList<MCAnswer> answers;

    public MCQuestion() {
        super();
        answers = null;
    }

    public MCQuestion(String s, double maxVal) {
        super(s, maxVal);
        answers = new ArrayList<MCAnswer>();
    }

    public Answer getNewAnswer() {
        return new MCAnswer();
    }

    @Override
    public void getAnswerFromStudent() {

    }

    public void print() {
        char[] answerAlphabet = {'A', 'B', 'C', 'D', 'E'};
        // get the answer and print out.
        System.out.println(this.getTheQuestion());
        int numOfAnswers = answers.size();
        for (int i=0; i < numOfAnswers; i++) {
            System.out.print(" " + answerAlphabet[i] + ". ");
            answers.get(i).print();
        }
    }

    public void save(PrintWriter writer){}


    public ArrayList<MCAnswer> getAnswers() {
        return answers;
    }

    public void addAnswer(MCAnswer ans) {
        // If there are less than 5 answers then add a answer.
        if (answers.size() < 5) {
            answers.add(ans);
        }
        // Otherwise, print out the error message.
        else {
            System.out.println("The limit for number of answers are 5.");
        }
    }


    public void reorderAnswers() {
        Collections.shuffle(answers);

    }

}
