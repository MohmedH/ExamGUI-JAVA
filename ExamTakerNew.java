import javax.swing.*;
import java.awt.CardLayout;

//THIS IS THE DRIVER FOR THE FIRST PAGE OF THE GUI AND TECHNICALLY THE DRIVER OF ENTIRE THING
public class ExamTakerNew extends JFrame{

    JPanel t = new JPanel();
    CardLayout car = new CardLayout();

    public static void main(String args[]){

        new ExamTakerNew();
    }

    public ExamTakerNew(){

        super("TEST");
        setResizable(true);
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        t.setLayout(car);
        t.add(new openingP());
        car.show(t, "open");
        add(t);
        setVisible(true);

    }
}
