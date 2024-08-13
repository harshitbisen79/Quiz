package com.quiz.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
import com.quiz.db.conn.DatabaseConnection;

public class Admin {
    private String username;
    private String password;

    public boolean login() throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username:");
        String inputUsername = sc.nextLine();
        System.out.println("Enter Password:");
        String inputPassword = sc.nextLine();

        String query = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {

            stmt.setString(1, inputUsername);
            stmt.setString(2, inputPassword);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                this.username = rs.getString("username");
                this.password = rs.getString("password");

                System.out.println("Admin login successful!");
                return true;
            } else {
                System.out.println("Invalid admin username or password!");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void viewAllScores() throws ClassNotFoundException {
        String query = "SELECT studentId, firstName, lastName, score FROM students ORDER BY score ASC";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("studentId") + ", Name: " + rs.getString("firstName") + " " + rs.getString("lastName") + ", Score: " + rs.getInt("score"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fetchStudentScore() throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Student ID:");
        int studentId = sc.nextInt();

        String query = "SELECT firstName, lastName, score FROM students WHERE studentId = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Name: " + rs.getString("firstName") + " " + rs.getString("lastName") + ", Score: " + rs.getInt("score"));
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
