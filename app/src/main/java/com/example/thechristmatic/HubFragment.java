package com.example.thechristmatic;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.thechristmatic.Quiz.LearnFragment;
import com.example.thechristmatic.Quiz.QuizActivity;
import com.example.thechristmatic.calculator.CalculatorActivity;
import com.example.thechristmatic.rollDice.RollDiceActivity;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HubFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HubFragment newInstance(String param1, String param2) {
        HubFragment fragment = new HubFragment();
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
        View view = inflater.inflate(R.layout.fragment_hub, container, false);

        CardView noteCardView = view.findViewById(R.id.hub_noteBtn);
        CardView calculatorCardView = view.findViewById(R.id.calculatorStart);
        CardView rollDiceCardView = view.findViewById(R.id.rolldice);
        CardView QuizCardView = view.findViewById(R.id.quizHubId);
        CardView bgcolorCardView = view.findViewById(R.id.bgChangeHub);
        RelativeLayout hub = (RelativeLayout) view.findViewById(R.id.hubDash);
        CardView learnCardView = view.findViewById(R.id.hubLearn);
        CardView facebookCardView = view.findViewById(R.id.hubFacebook);
        CardView twittweCardView = view.findViewById(R.id.hubTwitter);
        CardView youTubeCardView = view.findViewById(R.id.hubYoutube);




        noteCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoteActivity.class);
                startActivity(intent);
            }
        });

        calculatorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalculatorActivity.class);
                startActivity(intent);
            }
        });
        rollDiceCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RollDiceActivity.class);
                startActivity(intent);
            }
        });
        
        QuizCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);
            }
        });

        bgcolorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = generateRandomColor();
                hub.setBackgroundColor(color);

            }
        });
        learnCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);
            }
        });

        facebookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com"; // Replace with the URL you want to open
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        twittweCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.twitter.com"; // Replace with the URL you want to open
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        youTubeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com"; // Replace with the URL you want to open
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

       return view;
    }
    private int generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        int color = Color.rgb(red, green, blue);
        return color;
    }
}