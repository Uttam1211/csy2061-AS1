package com.example.thechristmatic.Quiz;

import static android.content.Intent.EXTRA_SUBJECT;


import static com.example.thechristmatic.Quiz.QuizData.SHARED_PREFS_KEY;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thechristmatic.R;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button confirmButton;

    private int currentQuestionIndex;
    private List<Question> questions;
String subject="Science";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_TheChristmatic);
        setContentView(R.layout.activity_quiz);

        questionTextView = findViewById(R.id.question_text_view);
        optionsRadioGroup = findViewById(R.id.options_radio_group);
        confirmButton = findViewById(R.id.confirm_button);

        // Get the subject from the intent extras

        subject = getIntent().getStringExtra("subject");
if(subject==null) subject="Math";
        // Create the list of questions for the selected subject
        questions = new ArrayList<>();
        switch (subject) {
            case "Science":
                questions.add(new Question(
                        "What is the largest organ in the human body?",
                        new String[]{"Brain", "Liver", "Skin", "Heart"},
                        2
                ));
                questions.add(new Question(
                        "What is the force that holds atoms together?",
                        new String[]{"Gravity", "Magnetism", "Electromagnetism", "Strong nuclear force"},
                        3
                ));
                questions.add(new Question(
                        "What is the largest planet in our solar system?",
                        new String[]{"Earth", "Jupiter", "Mars", "Venus"},
                        1
                ));
                questions.add(new Question(
                        "What type of energy does a wind turbine produce?",
                        new String[]{"Fossil fuel", "Nuclear energy", "Renewable energy", "None of the above"},
                        2
                ));
                questions.add(new Question(
                        "What is the process by which plants make their own food?",
                        new String[]{"Photosynthesis", "Respiration", "Digestion", "Fermentation"},
                        0
                ));
                break;
            case "Math":
                questions.add(new Question(
                        "What is the Pythagorean theorem?",
                        new String[]{"a^2 + b^2 = c^2", "a^3 + b^3 = c^3", "a^2 + b^3 = c^3", "a^3 + b^2 = c^3"},
                        0
                ));
                questions.add(new Question(
                        "What is the derivative of x^2 with respect to x?",
                        new String[]{"2x", "x^2", "1/x", "ln(x)"},
                        0
                ));
                questions.add(new Question(
                        "What is the value of pi (π)?",
                        new String[]{"3.14", "3.141", "3.1415", "3.14159"},
                        3
                ));
                questions.add(new Question(
                        "What is the equation of a line with slope 2 and y-intercept 3?",
                        new String[]{"y = 2x + 3", "y = 3x + 2", "x = 2y + 3", "x = 3y + 2"},
                        0
                ));
                questions.add(new Question(
                        "What is the sum of the first 10 positive integers?",
                        new String[]{"45", "50", "55", "60"},
                        1
                ));
                break;
            case "Physics":
                questions.add(new Question(
                        "What is Newton's first law of motion?",
                        new String[]{"Every action has an equal and opposite reaction", "Force equals mass times acceleration", "An object at rest will remain at rest unless acted upon by a force", "An object in motion will remain in motion unless acted upon by a force"},
                        2
                ));
                questions.add(new Question(
                        "What is the unit of measurement for force?",
                        new String[]{"Meters per second", "Newtons", "Joules", "Watts"},
                        1
                ));
                questions.add(new Question(
                        "What is the law of conservation of energy?",
                        new String[]{"Energy cannot be created or destroyed, only converted from one form to another", "For every action there is an equal and opposite reaction", "The total momentum of a closed system remains constant", "The rate of change of momentum of an object is proportional to the force applied"},
                        0
                ));
                questions.add(new Question(
                        "What is the formula for calculating the kinetic energy of an object?",
                        new String[]{"KE = mv^2", "KE = (1/2)mv^2", "KE = mgh", "KE = Fd"},
                        1
                ));
                questions.add(new Question(
                        "What is the speed of light in a vacuum?",
                        new String[]{"299,792,458 m/s", "300,000,000 m/s", "299,792,458 km/h", "186,282 miles/s"},
                        0
                ));
                break;
            case "Computer":
                questions.add(new Question(
                        "What does CPU stand for?",
                        new String[]{"Central Processing Unit", "Computer Processing Unit", "Control Processing Unit", "Command Processing Unit"},
                        0
                ));
                questions.add(new Question(
                        "What is an IP address?",
                        new String[]{"A unique identifier for a device on a network", "A type of computer virus", "A file format for images", "A programming language"},
                        0
                ));
                questions.add(new Question(
                        "What is the file extension for a Java source code file?",
                        new String[]{".java", ".class", ".jar", ".xml"},
                        0
                ));
                questions.add(new Question(
                        "What is the purpose of HTML?",
                        new String[]{"To create web pages", "To store data in a database", "To write computer programs", "To perform complex calculations"},
                        0
                ));
                questions.add(new Question(
                        "What is the name of the programming language used to create Android apps?",
                        new String[]{"Java", "C++", "Python", "JavaScript"},
                        0
                ));
                break;
            case "Chemistry":
                questions.add(new Question(
                        "What is the atomic number of oxygen?",
                        new String[]{"6", "8", "12", "16"},
                        1
                ));
                questions.add(new Question(
                        "What is the pH of a neutral solution?",
                        new String[]{"0", "1", "7", "14"},
                        2
                ));
                questions.add(new Question(
                        "What is the chemical symbol for gold?",
                        new String[]{"Au", "Ag", "Cu", "Fe"},
                        0
                ));
                questions.add(new Question(
                        "What is the process by which a liquid turns into a gas?",
                        new String[]{"Melting", "Freezing", "Condensation", "Vaporization"},
                        3
                ));
                questions.add(new Question(
                        "What is the chemical formula for water?",
                        new String[]{"CO2", "H2SO4", "HCl", "H2O"},
                        3
                ));
                break;
            default:
                Toast.makeText(this, "Invalid subject: " + subject, Toast.LENGTH_SHORT).show();
                finish();
                return;
        }
        // Set the first question
        currentQuestionIndex = 0;
        setQuestion(currentQuestionIndex);
        // Set a listener for the confirm button
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an answer has been selected
                if (optionsRadioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update the current question's selected answer
                int selectedAnswerIndex = optionsRadioGroup.indexOfChild(findViewById(optionsRadioGroup.getCheckedRadioButtonId()));
                questions.get(currentQuestionIndex).setSelectedAnswerIndex(selectedAnswerIndex);

                // Move to the next question or finish the quiz
                if (currentQuestionIndex < questions.size() - 1) {
                    currentQuestionIndex++;
                    setQuestion(currentQuestionIndex);
                } else {
                    finishQuiz();
                }
            }
        });
    }

    private void setQuestion(int index) {
        // Get the current question and its options
        Question question = questions.get(index);
        String questionText = question.getQuestionText();
        String[] options = question.getOptions();

        // Set the question text
        questionTextView.setText(questionText);

        // Clear any previously selected answer
        optionsRadioGroup.clearCheck();

        // Set the options as radio button texts
        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = (RadioButton) optionsRadioGroup.getChildAt(i);
            radioButton.setText(options[i]);
            radioButton.setEnabled(!question.isAnswered()); // Disable the option if the question is already answered
        }

        // Select the current answer if the question has already been answered
        int selectedAnswerIndex = question.getSelectedAnswerIndex();
        if (selectedAnswerIndex != -1) {
            RadioButton selectedRadioButton = (RadioButton) optionsRadioGroup.getChildAt(selectedAnswerIndex);
            selectedRadioButton.setChecked(true);
        }

        // Update the confirm button text based on whether the question is answered
        if (question.isAnswered()) {
            confirmButton.setText(R.string.next_question);
        } else {
            confirmButton.setText(R.string.confirm_answer);
        }
    }

    private void finishQuiz() {
        // Calculate the score
        int numCorrect = 0;
        for (Question question : questions) {
            if (question.isCorrect()) {
                numCorrect++;
            }
        }
        double score = (double) numCorrect / questions.size() * 100;

        // Show the score in a dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.quiz_finished)
                .setMessage(getString(R.string.score_message, numCorrect, questions.size(), score))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Save the quiz data
                        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                        QuizData quizData = new QuizData(subject, score, dateTime);

                        if (quizData.saveQuizData(getApplicationContext(), subject, score, dateTime)) {
                            Toast.makeText(getApplicationContext(), "Score saved", Toast.LENGTH_SHORT).show();

                            // Check for new quiz data file
                            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
                            Set<String> quizDataSet = sharedPreferences.getStringSet(subject, new HashSet<String>());
                            if (quizDataSet.size() > 0) {
                                String latestQuizDataJson = quizDataSet.toArray()[quizDataSet.size() - 1].toString();
                                QuizData latestQuizData = new Gson().fromJson(latestQuizDataJson, QuizData.class);
                                if (latestQuizData.getDateTime().equals(dateTime)) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                    builder.setTitle("New quiz data available")
                                            .setMessage("Do you want to view the latest quiz data?")
                                            .setPositiveButton("View", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    // Handle "View Log" button click
                                                    List<QuizData> quizDataList = new ArrayList<>();
                                                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
                                                    Set<String> quizDataSet = sharedPreferences.getStringSet(subject, new HashSet<String>());
                                                    Gson gson = new Gson();
                                                    for (String quizDataJson : quizDataSet) {
                                                        QuizData quizData = gson.fromJson(quizDataJson, QuizData.class);
                                                        quizDataList.add(quizData);
                                                    }
                                                    if (quizDataList.size() > 0) {
                                                        StringBuilder messageBuilder = new StringBuilder();
                                                        for (QuizData quizData : quizDataList) {
                                                            messageBuilder.append(getString(R.string.quiz_log_item, quizData.getDateTime(), quizData.getScore()));
                                                            messageBuilder.append("\n");
                                                        }
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                                                        builder.setTitle(subject)
                                                                .setMessage(messageBuilder.toString())
                                                                .setPositiveButton(R.string.ok, null)
                                                                .show();
                                                    } else {
                                                        Toast.makeText(getApplicationContext(), R.string.no_quiz_data, Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            })
                                            .setNegativeButton("Cancel", null)
                                            .show();
                                }
                            }
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to save score", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setCancelable(false)
                .show();
    }

}

