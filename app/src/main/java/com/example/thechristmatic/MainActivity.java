package com.example.thechristmatic;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.thechristmatic.details.studentInfo;

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail;
private EditText txtPassword;

    private TextView ResetHobbies;
    private TextView ResetUsername;
    private Button ResetPass;
    private SessionManager sessionManager;
    studentInfo studentInfoInstance = new studentInfo();
    String[] usernames = studentInfoInstance.usernames;
    String[] passwords = studentInfoInstance.passwords;
    String[] hobbies = studentInfoInstance.hobbies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //initialize sessionManager
        sessionManager = new SessionManager(getApplicationContext());
        //check if user is logged in ?

        if(sessionManager.isLoggedIn()) {
            String userEmail = sessionManager.getUsername();
            String password = sessionManager.getPassword();
            boolean found = false;
            int index = -1;
            String studentName = null;
            for (int i = 0; i < usernames.length; i++) {
                if (userEmail.equalsIgnoreCase(usernames[i]) && password.equals(passwords[i])) {
                    found = true;
                    index = i;
                    studentName = usernames[i];
                    break;
                }

            }
            if (found) {
                Intent myIntentToDashboard = new Intent(MainActivity.this, DashboardActivity.class);
                myIntentToDashboard.putExtra("index", index);
                myIntentToDashboard.putExtra("studentName", studentName);
                startActivity(myIntentToDashboard);
                finish();
            }
        }
        TextView txtVSignup = findViewById(R.id.txtVSignUpPrompt);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        TextView txtVForgetPass = findViewById(R.id.txtVForgetPass);
        Button btnLogin = findViewById(R.id.btnlogin);




        btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String userEmail = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        boolean found = false;
        int index = -1;
        String studentName = null;
        for(int i =0; i<usernames.length; i++){
            if(userEmail.equalsIgnoreCase(usernames[i]) && password.equals(passwords[i])){
                found = true;
                index =i;
                studentName = usernames[i];
                break;
            }

        }
        if(found){
            //save to session Manager
            sessionManager.saveLoginCredentials(userEmail,password);
            //dialog object with custom layout
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.login_animation);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.show();

            //delay
            Handler handler = new Handler();

            int finalIndex = index;
            String finalStudentName = studentName;
            //save intent to session manager
            sessionManager.saveIntentValues(finalIndex,finalStudentName);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent myIntentToDashboard = new Intent(MainActivity.this, DashboardActivity.class);
                    myIntentToDashboard.putExtra("index", finalIndex);
                    myIntentToDashboard.putExtra("studentName", finalStudentName);

                    //start activity
                    startActivity(myIntentToDashboard);
                    dialog.dismiss();
                    finish();

                }
            }, 1000);
        } else {
            Toast.makeText(getApplicationContext(),"failed invalid",Toast.LENGTH_SHORT).show();
        }

    }
});
txtVSignup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(myIntent);
            finish();
        }
    });
txtVForgetPass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.forget_password);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        ResetPass = dialog.findViewById(R.id.btnResetPass);
        ResetUsername = dialog.findViewById(R.id.txtUsername);
        ResetHobbies = dialog.findViewById(R.id.txtHobbies);
        dialog.show();


ResetPass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        for(int i =0; i<usernames.length; i++){
            if(ResetUsername.getText().toString().equalsIgnoreCase(usernames[i]) && ResetHobbies.getText().toString().equalsIgnoreCase(hobbies[i])){
               Toast toast;
               toast=Toast.makeText(getApplicationContext(),"Password is: "+ passwords[i],Toast.LENGTH_LONG);
               toast.setGravity(Gravity.TOP, 100,0);
               toast.show();
                break;
            }
            else {
                Toast toast =Toast.makeText(getApplicationContext(), "Wrong Username or Hobbies",Toast.LENGTH_SHORT);
                toast.show();

            }
    }
    }
});
    }
});
}

}