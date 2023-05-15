package com.example.thechristmatic.Quiz;

import static com.example.thechristmatic.Quiz.QuizData.SHARED_PREFS_KEY;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thechristmatic.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnFragment newInstance(String param1, String param2) {
        LearnFragment fragment = new LearnFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_learn, container, false);

            CardView scienceCardView = view.findViewById(R.id.card_view_science);
            scienceCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog("Science");
                }
            });

            CardView mathCardView = view.findViewById(R.id.card_view_math);
            mathCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog("Math");
                }
            });

            CardView computerCardView = view.findViewById(R.id.card_view_computer);
            computerCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog("Computer");
                }
            });

            CardView physicsCardView = view.findViewById(R.id.card_view_physics);
            physicsCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog("Physics");
                }
            });

            CardView chemCardView = view.findViewById(R.id.card_view_chem);
            chemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialog("Chemistry");
                }
            });

            return view;
        }

        private void showAlertDialog(String subject) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Do you want to view log or start for " + subject + "?");
            builder.setPositiveButton("View Log", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Handle "View Log" button click
                    String sub = subject;
                    List<QuizData> quizDataList = new ArrayList<>();
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
                    Set<String> quizDataSet = sharedPreferences.getStringSet(sub, new HashSet<String>());
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(subject)
                                .setMessage(messageBuilder.toString())
                                .setPositiveButton(R.string.ok, null)
                                .show();
                    } else {
                        Toast.makeText(getContext(), R.string.no_quiz_data, Toast.LENGTH_SHORT).show();
                    }
                }

            });
            builder.setNegativeButton("Start", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Handle "Start" button click
                    Intent intent = new Intent(getActivity(), com.example.thechristmatic.Quiz.QuizActivity.class);
                    intent.putExtra("subject", subject);
                    startActivity(intent);

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

