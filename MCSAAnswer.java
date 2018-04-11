/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.PrintWriter;

public class MCSAAnswer extends MCAnswer {

    public MCSAAnswer(){}
    
    public MCSAAnswer(String text, double creditIfSelected) {

        super(text, creditIfSelected);
    }


    public double getCredit(Answer rightAnswer) {
        // if the rightAnswers text is same as the current answer's text return the score.
        if(this.equals(rightAnswer)) {
            return this.getCreditIfSelected();
        }
        return 0.0;
    }
    
}
