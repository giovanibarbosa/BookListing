package com.barbosa.giovani.booklisting;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.barbosa.giovani.booklisting.adapters.BooksAdapter;
import com.barbosa.giovani.booklisting.loaders.BooksLoader;
import com.barbosa.giovani.booklisting.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<List<Book>> {
    /** Book volumes url. */
    private static final String BOOK_VOLUME_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    /** String representing the book volumes query. */
    private String mQuery;

    /** StringBuilder for the request. */
    private StringBuilder mRequestBuilder;

    /** Log tag. */
    private static final String TAG = MainActivity.class.getName();

    /** Id for the book loader. */
    private static final int BOOK_LOADER_ID = 1;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Book> bookList = new ArrayList<>();

        BooksAdapter adapter = new BooksAdapter(this, bookList);

        mListView = findViewById(R.id.book_list_view);
        mListView.setAdapter(adapter);

        TextView emptyTextView = findViewById(R.id.empty_list_view);
        mListView.setEmptyView(emptyTextView);

//        mRequestBuilder = new StringBuilder(BOOK_VOLUME_URL);

        if (mQuery != null && !mQuery.isEmpty()) {
            getLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return null;
//        return new BooksLoader(this, BOOKS_REQUEST);
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
