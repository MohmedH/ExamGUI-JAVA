/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

public class Answer {
    private double selectedValue; // Score when the user select this answer.
    private double notSelectedValue; // Score when the user does not select this answer.
    private String ans; // Name of the answer.
    private Boolean isSelected; // True when it is selected False when is not selected.


    /** Answer constructor */
    public Answer(String s) {
        ans = s; // Define the answer name.
        selectedValue = 0; // Initial selected value is 0
        notSelectedValue = 0; // Initial not selected value is 0
        isSelected = false; // Initially false.
    }


    /** Take two double values and assigned to two member variables*/
    public void setValue(double whenSelected, double whenNotSelected) {
        selectedValue = whenSelected; // When user select this answer, user get this score.
        notSelectedValue = whenNotSelected; // When user does not select this answer, user get this score.
    }


    /** Print out the answer and its position.*/
    public void print(int position) {
        // Print out the position and the answer.
        System.out.println(" " + position + ": " + ans);
    }


    /** Take a boolean value and set the is_selected to the value.*/
    public void setSelected(Boolean bool) {
        isSelected = bool;
    }


    /** Return the value, either value when the answer is selected or not selected.*/
    public double getValue() {
        if (isSelected) {
            return selectedValue;
        } else {
            return notSelectedValue;
        }
    }
}
