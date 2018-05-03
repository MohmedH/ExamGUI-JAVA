import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//THIS CLASS IS STRICTLY THE FIRST PAGE OF GUI ONLY TO GET INPUT OF NAME AND FILE YOU WANT TO OPEN
public class openingP extends JPanel implements ActionListener {

    QuizG quiz;
    ExamTakerNew dd;
    JPanel openPanel = new JPanel();
    JPanel textIn = new JPanel();
    JPanel bott = new JPanel();
    JButton but = new JButton("Start Test");
    JTextField name = new JTextField(20);
    JTextField fileName = new JTextField(20);
    JTextField examName = new JTextField(20);


    public openingP() {

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        openPanel.add(new JLabel("Please enter in your name, and file *remember to leave off the .txt*"));
        openPanel.add(new JLabel("IF YOU HIT 'Start Test' and nothing happens,"));
        openPanel.add(new JLabel("make sure you are loading the correct file."));
        openPanel.add(new JLabel("Verfiy the location of the file, and try again"));
        add(openPanel);

        textIn.add(name);
        name.setText("Type in full name");
        textIn.add(fileName);
        fileName.setText("enter exam name (without the .txt) ");
        add(textIn);

        but.addActionListener(this);
        bott.add(but);
        add(bott);
        //quiz.run = 1;




    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if(src.equals(but)){

            String f = fileName.getText();
            String n = name.getText();

            quiz.main(new String[0],n,f);
        }

    }

}
