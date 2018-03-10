/*
 Class : CS-362
 Name  : Seho Lim
 netID : slim67
 */

public class ExamTester {


    public static void main(String []args) {
        // print out author's information
        System.out.println("\nCS-362 Project 1");
        System.out.println("Name: Seho Lim");
        System.out.println("ACCC account name: slim");
        System.out.println("netID: slim67");

        // Create an Exam instance.
        Exam exam1 = new Exam("2018_Final_Exam");

        // create three Question instances.
        MCSAQuestion MCSAQ1 = new MCSAQuestion("what is 1 + 1?", 10);
        MCSAQuestion MCSAQ2 = new MCSAQuestion("what is 2 + 2?", 10);


        SAQuestion SAQ1 = new SAQuestion("what does starbucks sell, that is black in liquid?", 20);
        SAQuestion SAQ2 = new SAQuestion("what fruit monkeys like that is yellow and long", 20);

        SAAnswer singleAnswer1 = new SAAnswer("coffee");
        SAAnswer singleAnswer2 = new SAAnswer("banana");

        // Add three created question instances to the exam.
        exam1.addQuestion(MCSAQ1);
        exam1.addQuestion(MCSAQ2);
        exam1.addQuestion(SAQ1);
        exam1.addQuestion(SAQ2);

        MCSAAnswer ans1 = new MCSAAnswer("1", 0);
        MCSAAnswer ans2 = new MCSAAnswer("2", 10);
        MCSAAnswer ans3 = new MCSAAnswer("3", 0);
        MCSAAnswer ans4 = new MCSAAnswer("4", 0);
        MCSAAnswer ans5 = new MCSAAnswer("5", 0);

        MCSAQ1.addAnswer(ans1);
        MCSAQ1.addAnswer(ans2);
        MCSAQ1.addAnswer(ans3);
        MCSAQ1.addAnswer(ans4);
        MCSAQ1.addAnswer(ans5);

        MCSAAnswer secondAns1 = new MCSAAnswer("1", 0);
        MCSAAnswer secondAns2 = new MCSAAnswer("2", 0);
        MCSAAnswer secondAns3 = new MCSAAnswer("3", 0);
        MCSAAnswer secondAns4 = new MCSAAnswer("4", 10);
        MCSAAnswer secondAns5 = new MCSAAnswer("5", 0);

        MCSAQ2.addAnswer(secondAns1);
        MCSAQ2.addAnswer(secondAns2);
        MCSAQ2.addAnswer(secondAns3);
        MCSAQ2.addAnswer(secondAns4);
        MCSAQ2.addAnswer(secondAns5);

        System.out.println("\n\n==========================================================================");
        System.out.println("HW DESCRIPTION:\nPrint the exam. This can be simple text printing to the screen for now");
        System.out.println("===========================================================================\n");
        exam1.print();
        exam1.reorderQuestions();
        exam1.reorderMCAAnswers(-1);
        System.out.println("\n\n==========================================================================");
        System.out.println("HW DESCRIPTION:\nRandomly reorder the questions on the exam, and randomly reorder the answers to the multiple\n" +
                "choice questions. Then print the exam again");
        System.out.println("===========================================================================\n");
        exam1.print();
        MCSAQ1.setRightAnswer(ans2);
        MCSAQ2.setRightAnswer(secondAns4);
        SAQ1.setRightAnswer(singleAnswer1);
        SAQ2.setRightAnswer(singleAnswer2);

        System.out.println("\n\n==========================================================================");
        System.out.println("HW DESCRIPTION:\nGet “student” answers from the person running the program");
        System.out.println("===========================================================================\n");

        for(int i=1; i < exam1.getNumberOfQuestions()+1; i++) {
            exam1.getAnswerFromStudent(i);
        }

        System.out.println("\n\n==========================================================================");
        System.out.println("HW DESCRIPTION:\nGrade the exam, by totaling the value of the questions, and report the overall score, along\n" +
                "with the value contributed for each question.");
        System.out.println("===========================================================================\n");

        System.out.println("**** Score Report ****");

        System.out.println("Q1: " + exam1.getQuestion(0).getValue());
        System.out.println("Q2: " + exam1.getQuestion(1).getValue());
        System.out.println("Q3: " + exam1.getQuestion(2).getValue());
        System.out.println("Q4: " + exam1.getQuestion(3).getValue());

        System.out.println("Total Score: " + exam1.getValue());
    }
}
