## Design information - Jiejun

## Requirement: 
1.	When starting the application, a user can choose whether to (1) log in as a specific student or (2) register as a new student.
a.	To register as a new student, the user must provide the following student information:
i.	A unique username
ii.	A major
iii.	A seniority level (i.e., freshman, sophomore, junior, senior, or grad)
iv.	An email address
b.	The newly added student is immediately created in the system.
c.	For simplicity, there is no password creation/authentication; that is, selecting or entering a student username is sufficient to log in as that student.
d.	Also for simplicity, student and quiz information is local to a device.
## a & b realization
For requirement a, it is realized in the User class. User class has a “login” method for specific students who already have accounts, and “register()” method for new student who does not have an account. When the new user started the register process, he/she has to fill in the ‘username’, ‘major’, ‘seniority level’, ‘email’ information. Then the basic information of the user is kept in the User class and the newly added student is immediately created in the system.

## c & d realization
Student username is the only key variable for the User class, and it has to be identical so student can log in to their own user profile page.  Student and quiz information is local to a device, so we do not need a Server class to control the User class.

##Requirements
2.	The application allows students to (1) add a quiz, (2) remove a quiz they created, (3) practice quizzes, and (4) view the list of quiz score statistics.

## realization
This step is realized by the Quiz_Current class. This class is a subclass of the Quiz, which is the quizzes designed by the current user. It implements all the variables and methods of the parent class, and it also has its own methods such as “addquiz()” to add a quiz, “removeQuiz()” to remove a quiz they created. 

In order to practice quizzes, the User has to get access to another class, called Quiz_Other. This class is also a subclass of Quiz, which guarantees the owner of the quiz not the current user. By calling the practiceQuiz() method in the Quiz_Other class, the user can get access to the quiz, practice a random selected quiz. The user can also view the list of the quizscore statistics by the getScore() method. 

##Requirements
3.	To add a quiz, a student must enter the following quiz information:
a.	Unique name
b.	Short description
c.	List of N words, where N is between 1 and 10,  together with their definitions 
d.	List of N * 3 incorrect definitions, not tied to any particular word, where N is the number of words in the quiz.
## realization
Under the parent class, it has several private variables such as quizname, which is the Unique name of the quiz, shortDescription represents the Short description of the quiz, wordlist represents the lists of the N words(1<N<10), and incorrectList is the list of N*3 incorrect definitions. This fields are added under the addquiz() method. 

##Requirements
4.	To remove a quiz, students must select it from the list of the quizzes they created. Removing a quiz must also remove the score statistics associated with that quiz.

## realization
Under the Quiz_Current class, students can use the removeQuiz(0 method to remove the quiz and also the score statistics in the quizscore array. 

##Requirements
5.	To practice a quiz, students must select it from the list of quizzes created by other students.
## realization
To practice a quiz, students must select it from the list of quizzes Under quiz_Other class. 

##Requirements
6.	When a student is practicing a quiz, the application must do the following:
a.	Until all words in the quiz have been used in the current practice session: 
i.	Display a random word in the quiz word list.
ii.	Display four definitions, including the correct definition for that word (the other three definitions must be randomly selected from the union of (1) the set of definitions for the other words in the quiz and (2) the set of incorrect definitions for the quiz. 

## realization
Under the Quiz class, there is a displayQuiz() method, we can call this method to displayQuiz(), it can select a random word with correct answer from the wordlist of the quiz, and random select three words in the incorrectList. 

##Requirements
iii.	Let the student select a definition and display “correct” (resp., “incorrect”) if the definition is correct (resp., incorrect).

## realization
      The practiceQuiz () method will provide UI for the student to select answer for the quiz. However, for this assignment, we are not going to discuss further how we realize this method.
      
##Requirements
b.	After every word in the quiz has been used, the student will be shown the percentage of words they correctly defined, and this information will be saved in the quiz score statistics for that quiz and student.

## realization
After the test, the practiceQuiz() method will calculate the score of the student, and display the score on screen. 


##Requirements
7.	The list of quiz score statistics for a student must list all quizzes, ordered based on when they were last played by the student (most recent first). Clicking on a quiz must display (1) the student’s first score and when it was achieved (date and time), (2) the student’s highest score and when it was achieved (date and time), and (3) the names of the first three students to score 100% on the quiz, ordered alphabetically.

## realization
The quizscore variable under the Quiz class has a quizDate field, a quiz name field, a username field and a score field. Then by using the getScore() method under Quiz_Other class, it can filter the list and get the first score of the student, including date and time, the student’s highest score and when it was achieve, and the names of the first three students to score 100% on the quiz, ordered alphabetically. 

##Requirements
8.	The user interface must be intuitive and responsive.

This is not represented in my design, as it will be handled entirely within the GUI implementation.

##Requirements

9.	The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.

## realization
This is not represented in my design, as it will be handled entirely within the GUI implementation.






