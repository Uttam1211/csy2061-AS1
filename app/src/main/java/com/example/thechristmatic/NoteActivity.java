package com.example.thechristmatic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thechristmatic.note.AddNoteActivity;
import com.example.thechristmatic.note.EditNoteActivity;
import com.example.thechristmatic.note.Note;
import com.example.thechristmatic.note.NoteAdapter;
import com.example.thechristmatic.note.ViewNoteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NoteActivity extends AppCompatActivity implements NoteAdapter.OnItemClickListener {
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int EDIT_NOTE_REQUEST = 2;

    private List<Note> notes;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        recyclerView = findViewById(R.id.note_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        notes = new ArrayList<>();
        adapter = new NoteAdapter(notes, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
    }

    @Override
    public void onItemClick(Note note) {
        Intent intent = new Intent(NoteActivity.this, ViewNoteActivity.class);
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra("updatedNote", note);
        startActivity(intent);
    }

    @Override
    public void onEditClick(Note note) {
        Intent intent = new Intent(NoteActivity.this, EditNoteActivity.class);
        intent.putExtra("updatedNote", note);
        startActivityForResult(intent, EDIT_NOTE_REQUEST);
    }

    @Override
    public void onDeleteClick(Note note) {
        notes.remove(note);
        adapter.setNotes(notes);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            Note note = (Note) data.getSerializableExtra("updatedNote");
            notes.add(0,note);
            adapter.updateNotes(notes);
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            Note note = (Note) data.getSerializableExtra("updatedNote");
            for (int i = 0; i < notes.size(); i++) {
                if (notes.get(i).getId() == note.getId()) {
                    notes.set(i, note);
                    break;
                }
            }
            adapter.setNotes(notes);
        }
    }
}
