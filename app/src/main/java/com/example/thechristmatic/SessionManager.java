package com.example.thechristmatic;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String SHARED_PREF_NAME="MyPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_INDEX = "index";
    private static final String KEY_STUDENT_NAME = "studentName";

    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void saveLoginCredentials(String username, String password){
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.contains(KEY_USERNAME) && sharedPreferences.contains(KEY_PASSWORD);

    }

    public void clearLoginCredentials(){
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_PASSWORD);
        editor.apply();
    }

    public void saveIntentValues(int index, String studentName){
        editor.putInt(KEY_INDEX,index);
        editor.putString(KEY_STUDENT_NAME, studentName);
        editor.apply();
    }

    public int getIndex(){
        return sharedPreferences.getInt(KEY_INDEX, -1);
    }

    public String getStudentName(){
        return sharedPreferences.getString(KEY_STUDENT_NAME, "");
    }
    public void clearIntent(){
        editor.remove(KEY_INDEX);
        editor.remove(KEY_STUDENT_NAME);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME,"");
    }

    public String getPassword() {
        return sharedPreferences.getString(KEY_PASSWORD,"");
    }
}
