/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

public class MCSAAnswer extends MCAnswer {
    
    public MCSAAnswer(String text, double creditIfSelected) {

        super(text, creditIfSelected);
    }

    public double getCredit(Answer rightAnswer) {
        // if the rightAnswers text is same as the current answer's text return the score.
        // otherwise, return 0.0
        if(this.equals(rightAnswer)) {
            return this.getCreditIfSelected();
        }
        return 0.0;
    }
    
}
