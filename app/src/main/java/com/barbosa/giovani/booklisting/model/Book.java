package com.barbosa.giovani.booklisting.model;

import java.util.List;

/**
 * Created by giovani on 16/02/18.
 */

public class Book {
    private String mTitle;
    private List<String> mAuthorList;

    public Book(String title, List<String> authorList) {
        this.mTitle = title;
        this.mAuthorList = authorList;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public List<String> getAuthorList() {
        return mAuthorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.mAuthorList = authorList;
    }

    @Override
    public String toString() {
        return "Title: " + mTitle + " | Author: " + mAuthorList.get(0);
    }
}
