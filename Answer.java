/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.PrintWriter;

public abstract class Answer {

    /** Answer constructor */
    public Answer() {}

    /** Print out the answer and its position.*/
    public void print() {}

    /** Return the value, either value when the answer is selected or not selected.*/
    public double getCredit(Answer rightAnswer) {
        return 0.0;
    }

    abstract public void save(PrintWriter writer);
}
