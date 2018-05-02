import javax.swing.*;
import java.awt.CardLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

//THIS IS THE DRIVER FOR THE FIRST PAGE OF THE GUI AND TECHNICALLY THE DRIVER OF ENTIRE THING
public class ExamPDriver extends JFrame{

    JPanel t = new JPanel();
    CardLayout car = new CardLayout();

    public static void main(String args[]){

        new ExamPDriver();
    }

    public ExamPDriver(){

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
