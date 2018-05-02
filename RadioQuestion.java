import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public class RadioQuestion extends JPanel implements ActionListener{
    int correctAns;
    QuizG quiz;
    int selected = -1;
    //questions
    JPanel qPanel=new JPanel();
    //answers
    JPanel aPanel=new JPanel();
    JRadioButton[] responses;
    ButtonGroup group=new ButtonGroup();
    JTextField respon;
    int chek = 0;
    int sAT = 0;
    //bottom
    JPanel botPanel=new JPanel();
    JButton next=new JButton("Next Q");
    JButton finish=new JButton("Previous Q");
    JButton submit = new JButton("Submit Test");

    //WILL MAKE A PANEL WITH THE NECESSARY LAYOUT DEPENDING ON QUESTION TYPE
    public RadioQuestion(String q, List<String> options, int ans, QuizG quiz){
        this.quiz=quiz;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //correctAns=ans;
        //question
        qPanel.add(new JLabel(q));
        add(qPanel);
        //answer
        chek = 0;
        sAT = 0;
        if(options.size() < 2) {
            respon = new JTextField(20);
            //respon.addActionListener( this);
            sAT = 1;
            aPanel.add(respon);

        }else {
            responses = new JRadioButton[options.size()];
            for (int i = 0; i < options.size(); i++) {
                responses[i] = new JRadioButton(options.get(i));
                responses[i].addActionListener(this);
                if(ans == 1){
                    chek = 1;
                }else {
                    group.add(responses[i]);
                }
                aPanel.add(responses[i]);
            }
        }
        add(aPanel);
        //bottom
        next.addActionListener(this);
        finish.addActionListener(this);
        submit.addActionListener(this);

        //System.out.println(quiz.cont);
        if(quiz.cont != quiz.numQs)
            botPanel.add(next);

        if(quiz.cont > 1)
            botPanel.add(finish);

        botPanel.add(submit);
        add(botPanel);
        quiz.cont++;
    }

    public void actionPerformed(ActionEvent e){
        Object src=e.getSource();

        //next button
        if(src.equals(next)){
            quiz.next();
            if(chek == 1){
                for(int k=0; k<responses.length; k++){
                    if(responses[k].isSelected()){
                        quiz.mcMAA.add(k);
                        //responses[k].setSelected(false);
                        //System.out.println(responses[k].getText());
                        //System.out.println(quiz.mcMAA.get(k));

                    }

                }
            }

            showResult();

        }
        //Previous button
        if(src.equals(finish)) {
            quiz.previous();
            quiz.ansInput.remove(quiz.ansInput.size() - 1);

            if (chek == 0) {
                for (int k = 0; k < quiz.mcMAA.size(); k++) {
                    quiz.mcMAA.remove(k);
                }
            }
        }
        //submit button
        if(src.equals(submit)){

            if(chek == 1){
                for(int k=0; k<responses.length; k++){
                    if(responses[k].isSelected()){
                        quiz.mcMAA.add(k);

                    }
                }
            }

            showResult();
            quiz.submit();
        }
        //radio buttons
        for(int i=0;i<responses.length;i++){
            if(src==responses[i]){
                selected=i;

            }
        }

    }
    //Show result, basically saves student answer into proper array back to main Driver class
    public void showResult(){
        String text;

        if(selected != -1)
            text =responses[selected].getText(); //Answer Picked
        else
            text = "null";

        if(sAT == 1) {
            if(!(respon.getText().equals(""))) {
                text = respon.getText();
            }else{
                text = "null";
            }
        }

        List<String> tt = new ArrayList<String>();
        int f;

        if(chek == 1){
            if(quiz.mcMAA.size() == 0)
                tt.add("null");
            for(int k = 0; k < quiz.mcMAA.size(); k++){
                f = quiz.mcMAA.get(k);
                tt.add(responses[f].getText());
            }
           quiz.mcMAA.clear();

        }else{
            tt.add(text);
        }
        quiz.ansInput.add(tt);
    }

}