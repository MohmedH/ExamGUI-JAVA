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
        // otherwise, return 0.0
        System.out.println(this);
        System.out.println(this.equals(rightAnswer));
        System.out.println(rightAnswer);
        if(this.equals(rightAnswer)) {
            System.out.println("yohooo");
            return this.getCreditIfSelected();
        }
        return 0.0;
    }
    
}
