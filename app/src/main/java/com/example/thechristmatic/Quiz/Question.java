package com.example.thechristmatic.Quiz;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;
    private int selectedAnswerIndex = -1; // -1 indicates that no answer has been selected yet

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getSelectedAnswerIndex() {
        return selectedAnswerIndex;
    }

    public void setSelectedAnswerIndex(int selectedAnswerIndex) {
        this.selectedAnswerIndex = selectedAnswerIndex;
    }

    public boolean isAnswered() {
        return selectedAnswerIndex != -1;
    }

    public boolean isCorrect() {
        return selectedAnswerIndex == correctAnswerIndex;
    }
}
