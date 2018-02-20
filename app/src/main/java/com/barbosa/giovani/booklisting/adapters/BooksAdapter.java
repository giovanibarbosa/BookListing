package com.barbosa.giovani.booklisting.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.barbosa.giovani.booklisting.R;
import com.barbosa.giovani.booklisting.model.Book;

import java.util.List;

/**
 * Adapter for book list view.
 */
public class BooksAdapter extends BaseAdapter {
    /** The context */
    private Context mContext;

    /** The book list. */
    private List<Book> mBooks;

    private static final String TAG = BaseAdapter.class.getName();

    /**
     * BooksAdapter constructor.
     * @param context the context
     * @param books found
     */
    public BooksAdapter(Context context, List<Book> books) {
        this.mContext = context;
        mBooks = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (inflater != null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent,
                    false);
            viewHolder = new ViewHolder(convertView);

            Book currentBook = (Book) getItem(position);
            viewHolder.titleTextView.setText(currentBook.getTitle());
            setAuthorsViews(viewHolder, currentBook);
        }
        return convertView;
    }

    /**
     * Set the views with the authors' names.
     * @param viewHolder of the elements
     * @param currentBook of the authors
     */
    private void setAuthorsViews(ViewHolder viewHolder, Book currentBook) {
        for (String author : currentBook.getAuthorList()) {
            TextView textView = new TextView(mContext);
            textView.setText(author);
            if (Build.VERSION.SDK_INT < 23) {
                textView.setTextAppearance(mContext, android.R.style.TextAppearance_Small);
            } else {
                textView.setTextAppearance(android.R.style.TextAppearance_Small);
            }
            viewHolder.authorsLayout.addView(textView);
        }
    }

    @Override
    public int getCount() {
        return mBooks.size();
    }

    @Override
    public Object getItem(int position) {
        return mBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Set new data set.
     * @param books to be updated
     */
    public void setBooks(final List<Book> books) {
        this.mBooks = books;
    }

    /**
     * ViewHolder to initialize the view items.
     */
    private class ViewHolder {
        TextView titleTextView;
        LinearLayout authorsLayout;

        private ViewHolder(View view) {
            titleTextView = view.findViewById(R.id.title_text_view);
            authorsLayout = view.findViewById(R.id.authors_view);
        }
    }
}
