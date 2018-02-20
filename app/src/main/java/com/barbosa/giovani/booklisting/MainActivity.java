package com.barbosa.giovani.booklisting;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.barbosa.giovani.booklisting.adapters.BooksAdapter;
import com.barbosa.giovani.booklisting.loaders.BooksLoader;
import com.barbosa.giovani.booklisting.model.Book;
import com.barbosa.giovani.booklisting.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager
        .LoaderCallbacks<List<Book>> {
    /** Book volumes url. */
    private static final String BOOK_VOLUME_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    /** Log tag. */
    private static final String TAG = MainActivity.class.getName();

    /** Id for the book loader. */
    private static final int BOOK_LOADER_ID = 1;

    private BooksAdapter mAdapter;

    List<Book> mBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookList  = new ArrayList<>();

        mAdapter = new BooksAdapter(this, mBookList);

        final ListView listView = findViewById(R.id.book_list_view);
        listView.setAdapter(mAdapter);

        final TextView emptyTextView = findViewById(R.id.empty_list_view);
        listView.setEmptyView(emptyTextView);

        final View searchView = findViewById(R.id.search_view);
        final EditText searchEditText = findViewById(R.id.search_edit_text);
        final ImageView searchButton = searchView.findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearchButtonClick(searchEditText);
            }
        });

        getLoaderManager().initLoader(BOOK_LOADER_ID, null, this);
    }

    private void performSearchButtonClick(EditText searchEditText) {
        if (searchEditText != null && !searchEditText.getText().toString().isEmpty()) {
            final String fullQuery = (BOOK_VOLUME_URL + searchEditText.getText().toString())
                    .replace(Constants.WHITE_SPACE, Constants.PLUS_SYMBOL).trim();

            makeOperationSearchQuery(fullQuery);
        }
    }

    private void makeOperationSearchQuery(final String url) {
        final Bundle queryBundle = new Bundle();
        queryBundle.putString(Constants.QUERY_KEY, url);

        LoaderManager loaderManager = getLoaderManager();
        Loader<Book> loader = loaderManager.getLoader(BOOK_LOADER_ID);

        if(loader==null) {
            loaderManager.initLoader(BOOK_LOADER_ID, queryBundle, this);
        } else {
            loaderManager.restartLoader(BOOK_LOADER_ID, queryBundle, this);
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BooksLoader(this, args);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        if (books != null) {
            mAdapter.setBooks(books);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.e(TAG, "onLoaderReset");
    }
}
