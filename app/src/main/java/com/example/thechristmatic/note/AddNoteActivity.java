package com.example.thechristmatic.note;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thechristmatic.R;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = findViewById(R.id.title_edit_text);
        contentEditText = findViewById(R.id.content_edit_text);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                // Set current date and time on file name
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                String fileName = "note_" + dateFormat.format(currentDate) + ".xml";

                // Create new note object
                Note note = new Note(title, content, currentDate, fileName);

                // Save note data to XML file
                try {
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlSerializer serializer = factory.newSerializer();

                    FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    serializer.setOutput(fos, "UTF-8");
                    serializer.startDocument(null, true);
                    serializer.startTag(null, "note");
                    serializer.startTag(null, "title");
                    serializer.text(note.getTitle());
                    serializer.endTag(null, "title");
                    serializer.startTag(null, "content");
                    serializer.text(note.getContent());
                    serializer.endTag(null, "content");
                    serializer.startTag(null, "date");
                    serializer.text(note.getDate().toString());
                    serializer.endTag(null, "date");
                    serializer.endTag(null, "note");
                    serializer.endDocument();
                    serializer.flush();
                    fos.close();

                    Intent data = new Intent();
                    data.putExtra("updatedNote", note);
                    setResult(RESULT_OK, data);
                    finish();
                } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}

