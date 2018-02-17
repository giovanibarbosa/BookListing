package com.barbosa.giovani.booklisting;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.barbosa.giovani.booklisting.model.Book;
import com.barbosa.giovani.booklisting.utils.Utils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String BOOKS_REQUEST = "https://www.googleapis.com/books/v1/volumes?q=flowers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BooksRequestAsyncTask().execute();
    }

    private class BooksRequestAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String... strings) {
            return Utils.fetchBooksData(BOOKS_REQUEST);
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            System.out.println(books.size() + " books size");
            for (Book book: books) {
                Log.e("livro", book.toString());
            }
        }
    }
}
