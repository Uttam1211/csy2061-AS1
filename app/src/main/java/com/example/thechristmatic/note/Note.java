package com.example.thechristmatic.note;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Note implements Serializable {
    private String title;
    private String content;
    private Date date;
    private int id;

    public Note(String title, String content, Date date, String fileName) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


}
