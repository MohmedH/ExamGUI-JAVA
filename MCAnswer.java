public class MCAnswer extends Answer {
    private String text;
    private boolean selected;
    private double creditIfSelected;

    public MCAnswer(String s, double credit) {
        text = s;
        creditIfSelected = credit;
        selected = false;
    }

    /** This function prints out the answer.*/
    public void print() {
        System.out.println(text);

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
