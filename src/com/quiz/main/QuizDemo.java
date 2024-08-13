package com.quiz.main;

import com.quiz.service.QuizCatelogService;

public class QuizDemo {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		QuizCatelogService quizCatelogService = new QuizCatelogService();
		quizCatelogService.getQuizCatelogDetails();
	}
	
	

}
