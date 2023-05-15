package com.example.thechristmatic.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thechristmatic.R;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private OnItemClickListener listener;

    public NoteAdapter(List<Note> notes, OnItemClickListener listener) {
        this.notes = notes;
        this.listener = listener;
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note, listener);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
        void onEditClick(Note note);
        void onDeleteClick(Note note);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
    public void updateNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView dateTextView;
        private TextView previewTextView;
        private CardView cardView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            dateTextView = itemView.findViewById(R.id.date);
            previewTextView = itemView.findViewById(R.id.preview);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void bind(final Note note, final OnItemClickListener listener) {
            titleTextView.setText(note.getTitle());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = dateFormat.format(note.getDate());
            dateTextView.setText(dateString);

            previewTextView.setText(note.getContent());

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(note);
                }
            });

            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                    popupMenu.getMenuInflater().inflate(R.menu.note_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.edit_note:
                                    listener.onEditClick(note);
                                    return true;
                                case R.id.delete_note:
                                    listener.onDeleteClick(note);
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popupMenu.show();
                    return true;
                }
            });
        }
    }
}
