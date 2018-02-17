package com.barbosa.giovani.booklisting.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.barbosa.giovani.booklisting.model.Book;
import com.barbosa.giovani.booklisting.utils.Utils;

import java.util.List;

/**
 * Loads a list of books by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class BooksLoader extends AsyncTaskLoader<List<Book>> {
    /** Url to request the book list. */
    private String mUrl;

    /** Tag for log messages */
    private static final String LOG_TAG = BooksLoader.class.getName();

    /**
     * Constructor to this loader.
     * @param context the context
     * @param url url to request books
     */
    public BooksLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        return Utils.fetchBooksData(mUrl);
    }
}
