import java.io.PrintWriter;

public class MCAnswer extends Answer {
    protected String text;
    protected boolean selected;
    protected double creditIfSelected;

    public MCAnswer(String s, double credit) {
        text = s;
        creditIfSelected = credit;
        selected = false;
    }

    public MCAnswer() {
        text = null;
        creditIfSelected = 0.0;
        selected = false;
    }

    /** This function prints out the answer.*/
    public void print() {
        System.out.println(text);
    }

    public void save(PrintWriter writer) {
        writer.write(text + "\n");
    }

    public void setSelected(boolean b) {
        selected = b;
    }

    public double getCreditIfSelected() {
        return creditIfSelected;
    }


    public boolean getSelected() {
        return selected;
    }
}
