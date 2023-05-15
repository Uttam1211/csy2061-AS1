package com.example.thechristmatic.Quiz;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Set;

public class QuizData {
    static final String SHARED_PREFS_KEY = "quiz_data";

    private String subject;
    private double score;
    private String dateTime;

    public QuizData(String subject, double score, String dateTime) {
        this.subject = subject;
        this.score = score;
        this.dateTime = dateTime;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public static boolean saveQuizData(Context context, String subject, double score, String dateTime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        Set<String> quizDataSet = sharedPreferences.getStringSet(subject, new HashSet<String>());
        Gson gson = new Gson();
        QuizData quizData = new QuizData(subject, score, dateTime);
        String quizDataJson = gson.toJson(quizData);
        quizDataSet.add(quizDataJson);
        sharedPreferences.edit().putStringSet(subject, quizDataSet).apply();
        return true;
    }


}
