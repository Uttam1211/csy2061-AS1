package com.example.thechristmatic.note;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thechristmatic.R;

import java.text.SimpleDateFormat;

public class ViewNoteActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView dateTextView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        titleTextView = findViewById(R.id.title_text_view);
        dateTextView = findViewById(R.id.date_text_view);
        contentTextView = findViewById(R.id.content_text_view);

        Note note = (Note) getIntent().getSerializableExtra("updatedNote");
        titleTextView.setText(note.getTitle());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(note.getDate());
        dateTextView.setText(dateString);

        contentTextView.setText(note.getContent());
    }
}
