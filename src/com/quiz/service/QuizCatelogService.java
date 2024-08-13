package com.quiz.service;

import java.util.Scanner;

import com.quiz.model.Admin;
import com.quiz.model.Question;
import com.quiz.model.Student;

public class QuizCatelogService {

	public void getQuizCatelogDetails() throws ClassNotFoundException {

		Student student = new Student();
		Admin admin = new Admin();
		Question question = new Question();

		while (true) {
			// Display user Options
			System.out.println("*********Welcome to Quiz based application********\r\n"
					+ "\r\n-----------------User Operation------------------\r\n" + "1. Student Registration\r\n"
					+ "2. Student Login\r\n" + "3. Take Quiz\r\n"
					+ "4. View Score\r\n" 
					+ "\r\n-----------------Admin Operation------------------\r\n"
					+ "5. Admin Login\r\n"
					+ "6. Add Question (Admin)\r\n"
					+ "7. View All Scores (Admin)\r\n" + "8. Fetch Student Score (Admin)\r\n"
					+ "9. Exit.\r\n" + "\r\n******************************************************");

			// create Scanner class object to take input from user
			System.out.println("Enter your choice: ");
			Scanner scanner = new Scanner(System.in);
			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				student.register();
				break;

			case 2:
				student.login();
				break;

			case 3:
				student.takeQuiz();
				break;

			case 4:
				student.viewScore();
				break;
			case 5:
				if (admin.login()) {
					System.out.println("Admin logged in successfully.");
				}
				break;
			case 6:
				if (admin.login()) {
					question.addQuestion();
				}
				break;
			case 7:
				if (admin.login()) {
					admin.viewAllScores();
				}
				break;
			case 8:
				if (admin.login()) {
					admin.fetchStudentScore();
				}
				break;
			case 9:
				System.exit(0);
			default:
				System.out.println("Invalid choice! Please try again.");
			}
		}
	}

}
