package com.example.thechristmatic.note;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thechristmatic.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditNoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        titleEditText = findViewById(R.id.title_edit_text);
        contentEditText = findViewById(R.id.content_edit_text);

        note = (Note) getIntent().getSerializableExtra("updatedNote");
        if(note!=null){
        titleEditText.setText(note.getTitle());
        contentEditText.setText(note.getContent());
        }

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        Date date = new Date();
        String pattern = "yyyyMMdd_HHmmss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String fileName = "note_" + simpleDateFormat.format(new Date()) + ".xml";


        Note updatedNote = new Note(title, content, date, fileName);
        updatedNote.setId(note.getId());

        Intent intent = new Intent();
        intent.putExtra("updatedNote", updatedNote);
        setResult(RESULT_OK, intent);
        finish();
    }
}
