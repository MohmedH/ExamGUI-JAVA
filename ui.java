import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

import java.awt.event.*;


public class ui extends JFrame{
    public static void main(String[] args) {
        new ui();
    }

    public ui() {
        this.setSize(400,400);
        this.setLocationRelativeTo(null);


        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width/2) - ( this.getWidth()/2);
        int yPos = (dim.height/2) - (this.getHeight()/2);

        this.setLocation(xPos, yPos);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Exam Controller");



        JPanel thePanel = new JPanel();
        JLabel label1 = new JLabel("Do stuff");
        label1.setText("New text: ");
        label1.setToolTipText("this doesn't do anything");


        JButton button1 = new JButton("Do thing");

        button1.setText("New Button");
        button1.setToolTipText("This is a button");

        JTextField textField1 = new JTextField("Type here", 15);
        textField1.setColumns(10);

        textField1.setText("Type here!!!");
        textField1.setToolTipText("its a field");


        JTextArea textArea1 = new JTextArea(15, 20);
        textArea1.setText("sadfasfwqefw asdf wqer  sadf asfwqer we sadf sadf qwer ");
        textArea1.setLineWrap(true);

        thePanel.add(label1);
        thePanel.add(button1);
        thePanel.add(textField1);
        thePanel.add(textArea1);
        this.add(thePanel);


        this.setVisible(true);
    }
}
