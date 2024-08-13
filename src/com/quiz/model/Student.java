package com.quiz.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.quiz.db.conn.DatabaseConnection;

public class Student {
	private int studentId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String city;
	private String email;
	private String mobileNumber;
	private int score;

	public void register() throws ClassNotFoundException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter First Name:");
		firstName = sc.nextLine();
		System.out.println("Enter Last Name:");
		lastName = sc.nextLine();
		System.out.println("Enter Username:");
		username = sc.nextLine();
		System.out.println("Enter Password:");
		password = sc.nextLine();
		System.out.println("Enter City:");
		city = sc.nextLine();
		System.out.println("Enter Email:");
		email = sc.nextLine();
		System.out.println("Enter Mobile Number:");
		mobileNumber = sc.nextLine();

		String query = "INSERT INTO students (firstName, lastName, username, password, city, email, mobileNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {

			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, username);
			stmt.setString(4, password);
			stmt.setString(5, city);
			stmt.setString(6, email);
			stmt.setString(7, mobileNumber);

			stmt.executeUpdate();
			System.out.println("Registration successful!\r\n");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean login() throws ClassNotFoundException {
		System.out.println("\r\n*****Student Login******\r\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Username:");
		String inputUsername = sc.nextLine();
		System.out.println("Enter Password:");
		String inputPassword = sc.nextLine();

		String query = "SELECT * FROM students WHERE username = ? AND password = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(query)) {

			pstmt.setString(1, inputUsername);
			pstmt.setString(2, inputPassword);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				this.studentId = rs.getInt("studentId");
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.username = rs.getString("username");
				this.city = rs.getString("city");
				this.email = rs.getString("email");
				this.mobileNumber = rs.getString("mobileNumber");
				this.score = rs.getInt("score");

				System.out.println("Login successful!\r\n Welcome " + this.firstName + "!");
				return true;
			} else {
				System.out.println("Invalid username or password!\r\n");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	 public void takeQuiz() throws ClassNotFoundException {
         try (Connection conn = DatabaseConnection.getConnection();
              Statement stmt = conn.createStatement()) {

             ResultSet rs = stmt.executeQuery("SELECT * FROM questions");
             Scanner sc = new Scanner(System.in);
             int correctAnswers = 0;

             while (rs.next()) {
                 System.out.println("Que: "+rs.getString("questionText"));
                 System.out.println("A. " + rs.getString("optionA"));
                 System.out.println("B. " + rs.getString("optionB"));
                 System.out.println("C. " + rs.getString("optionC"));
                 System.out.println("D. " + rs.getString("optionD"));
                 System.out.print("Enter your choice (A/B/C/D): ");
                 String userAnswer = sc.nextLine().toUpperCase();

                 if (userAnswer.equals(rs.getString("correctAnswer"))) {
                     correctAnswers++;
                 }
             }

             this.score = correctAnswers;
             String updateQuery = "UPDATE students SET score = ? WHERE studentId = ?";
             PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(updateQuery);
             pstmt.setInt(1, correctAnswers);
             pstmt.setInt(2, studentId);
             pstmt.executeUpdate();

             System.out.println("Quiz completed! Your score is: " + correctAnswers+"\r\n");

         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

	 
	 public void viewScore() {
		 	System.out.println("\r\n**********View Result*************\r\n");
	        System.out.println("Your score is: " + this.score);
	        System.out.println("\r\n**********************************\r\n");
	    }

}