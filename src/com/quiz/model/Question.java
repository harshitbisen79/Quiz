package com.quiz.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.quiz.db.conn.DatabaseConnection;

public class Question {
    private int questionId;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public void addQuestion() throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Question:");
        questionText = sc.nextLine();
        System.out.println("Enter Option A:");
        optionA = sc.nextLine();
        System.out.println("Enter Option B:");
        optionB = sc.nextLine();
        System.out.println("Enter Option C:");
        optionC = sc.nextLine();
        System.out.println("Enter Option D:");
        optionD = sc.nextLine();
        System.out.println("Enter Correct Answer (A/B/C/D):");
        correctAnswer = sc.nextLine().toUpperCase();

        String query = "INSERT INTO questions (questionText, optionA, optionB, optionC, optionD, correctAnswer) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, questionText);
            stmt.setString(2, optionA);
            stmt.setString(3, optionB);
            stmt.setString(4, optionC);
            stmt.setString(5, optionD);
            stmt.setString(6, correctAnswer);

            stmt.executeUpdate();
            System.out.println("Question added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
