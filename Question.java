/*
 Class : CS-362
 text  : Seho Lim
 netID : slim67
 */
/Users/seholim/IdeaProjects/cs342Proj1/src
import java.util.ArrayList;
import java.util.Collections;

public class Question {
    private String text; // Question name.
    private Answer rightAnswer;
    private Answer studentAnswer;
    private double maxValue; // Indicate how much this question is currently valued.
    private ArrayList<Answer> Answers; // Answers ArrayList.

    /** Question constructor. It receives the question name.*/
    public Question(String qName) {
        Answers = new ArrayList<Answer>();
        text = qName;
        maxValue = 0;
    }

    // TODO: 2/18/18  
    public void setRightAnswer(Answer ans) {
        
    }

    // TODO: 2/18/18  
    public Answer getNewAnswer() {

    }

    // TODO: 2/18/18
    public getAnswerFromStudent() {

    }


    /** Return the current score */
    public double getValue() {
        return maxValue;
    }


    /** Return the name of the question */
    public String getName() {
        return text;
    }


    /** Add a answer instance to the ArrayList. */
    public void AddAnswer(Answer a) {
        // If there are less than 5 answers then add a answer.
        if (Answers.size() < 5) {
            Answers.add(a);
        }
        // Otherwise, print out the error message.
        else {
            System.out.println("The limit for number of answers are 5.");
        }
    }


    /** Get the answer and then print out the answer. */
    public void print(int answerNum) {
        // pick the answer and then print out the answer.
        Answers.get(answerNum).print(answerNum);
    }


    /** Take the position of the answer and select the answer. And unselect the other answers.*/
    public void selectAnswer(int position) {
        // Pick an answer instance.
        Answer ans = Answers.get(position);
        // Unselect all the answers.
        unselectAllAnswers();
        // Set the answer as selected.
        ans.setSelected(true);
        // Add up the current value of the answer.
        maxValue += ans.getValue();
    }


    /** Take the position of the answer and unselect the answer.*/
    public void unselectAnswer(int position) {
        // Pick an answer instance.
        Answer ans = Answers.get(position);
        // Set the answer as not selected.
        ans.setSelected(false);
        // Subtracts the current value of the answer.
        maxValue -= ans.getValue();

    }


    /** Unselect all the answers. */
    public void unselectAllAnswers() {
        for (Answer a : Answers) {
            a.setSelected(false);
        }
    }


    /** Shuffle all the answers in the ArrayList */
    public void reorderAnswers() {
        Collections.shuffle(Answers);
    }

}
