package com.example.thechristmatic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.thechristmatic.details.studentInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    studentInfo studentInfoInstance = new studentInfo();
    String[][] studentDetails = studentInfoInstance.studentDetails;
    private SessionManager sessionManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);


        }
        sessionManager = new SessionManager(getActivity().getApplicationContext());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        int index = sessionManager.getIndex();
        String studentName = sessionManager.getStudentName();
        TextView greetingText = rootView.findViewById(R.id.nameContent);
        greetingText.setText("Hello, " + studentName);

        TextView studentName1 = rootView.findViewById(R.id.studentNameEdit);
        studentName1.setText(studentName);

        TextView age = rootView.findViewById(R.id.studentAgeEdit);
        age.setText(studentDetails[6][index]);

        TextView studentAddress = rootView.findViewById(R.id.studentAddressEdit);
        studentAddress.setText(studentDetails[4][index]);

        TextView studentCountry = rootView.findViewById(R.id.studentCountryEdit);
        studentCountry.setText(studentDetails[0][index]);

        TextView studentGender = rootView.findViewById(R.id.studentGenderEdit);
        studentGender.setText(studentDetails[1][index]);

        TextView studentHobbies = rootView.findViewById(R.id.studentHobbiesEdit);
        studentHobbies.setText(studentDetails[5][index]);

        TextView studentParents = rootView.findViewById(R.id.studentParentsEdit);
        studentParents.setText(studentDetails[2][index]);

        TextView studentPhone = rootView.findViewById(R.id.studentPhoneEdit);
        studentPhone.setText(studentDetails[3][index]);

return rootView;
    }
}