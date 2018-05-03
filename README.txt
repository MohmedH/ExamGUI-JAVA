Homework5 = BY: Anas Tlemat, Seho Lim, Mohmed Hira
TEAM: 31!
EXAM TAKER made by: Mohmed Hira
EXAM GRADER made by: Seho Lim
EXAM MAKER made by: Anas Tlemat


============= HOW TO RUN THIS PROGRAM ===============
1. run 'make' on your terminal.
2. then you get ExamBuilder, ExamGrader, ExamTakerNew.
3. run these commands to run each program.
 - java ExamBuilder
 - java ExamGrader
 - java ExamTakerNew
======================================================



IMPORTANT NOTES FROM MEMBERS:

Mohmed Hira:
For the ExamTaker GUI, It is made up of the classes QuizG.java, which is the driver for the questions and answers, holds almost the entire ExamTaker GUI. RadioQuestion.java, This class is responsible for taking each question given to it, and making a panel and giving the proper layout for QuizG.java to use and store.
The first page in the GUI is a friendly page asking for users Name, and then the file they want to open OR exam name. NOTE: If the exam name is stored_exam.txt, you will just need to put stored_exam. Make sure the exam file is in the same folder as all the other class files.
To make this first page, I needed to add openingP.java which has the layout and has everything to do with the opening page, and then ExamTakerGUI.java, which is the driver for openingP.java. Now Because I have two separate drivers, and I need openingP.java to come first, ExamTakerGUI.java is the Main driver. THAT IS THE CLASS YOU WANT TO RUN! upon pressing the start test button on the opening page, openingP.java will call QuizG.java to start running.
I have also included a .jar named ExamTaker.jar, you can also just run this if you choose. Make sure the .txt exam file is in the same place as ExamTaker.jar.
Tl;DR For ExamTaker, there are 4 classes, QuizG.java, RadioQuesion.java, openingP.java, ExamTakeGUI.java. ExamTakerGUI is the driver for the entire thing. There is a .jar file as well name ExamTaker.java.