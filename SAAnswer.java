/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

import java.io.PrintWriter;
import java.util.Scanner;

public class SAAnswer extends Answer {

    protected String text;

    public SAAnswer(){}

    public SAAnswer(String s) {
        text = s;
    }

    public SAAnswer(Scanner scanner) {
        System.out.println("SAAnswer constructor");
        text = scanner.nextLine();
    }
    
    public void print() {

    }

    public void save(PrintWriter writer) {
        writer.write(text + "\n\n");
    }

    public String getText() {
        return text;
    }

    /** Abstract method for childeren's double getCredit methods. */
    public double getCredit(Answer rightAnswer) {

        if (getText().equals(((SAAnswer) rightAnswer).getText())) {
            return 1;
        }
        return 0.0;
    }
}
