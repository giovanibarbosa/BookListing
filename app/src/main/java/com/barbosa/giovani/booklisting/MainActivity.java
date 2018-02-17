package com.barbosa.giovani.booklisting;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.barbosa.giovani.booklisting.loaders.BooksLoader;
import com.barbosa.giovani.booklisting.model.Book;
import com.barbosa.giovani.booklisting.utils.Utils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<List<Book>> {
    private static final String BOOKS_REQUEST = "https://www.googleapis.com/books/v1/volumes?q=flowers";

    private static final String TAG = MainActivity.class.getName();

    private static final int BOOK_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BooksLoader(this, BOOKS_REQUEST);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        for (Book book: books) {
            Log.e(TAG, book.toString());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.e(TAG, "onLoaderReset");
    }
}
