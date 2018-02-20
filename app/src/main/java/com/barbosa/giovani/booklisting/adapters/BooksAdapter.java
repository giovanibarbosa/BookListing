package com.barbosa.giovani.booklisting.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

    public BooksAdapter(Context context, List<Book> books) {
        this.mContext = context;
        mBooks = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent,
                    false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Book currentBook = (Book) getItem(position);
        viewHolder.titleTextView.setText(currentBook.getTitle());
//        viewHolder.authorsLayoututhors.setText(currentBook.getItemDescription());

        return convertView;
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

    public void setBooks(final List<Book> books) {
        this.mBooks = books;
    }

    private class ViewHolder {
        TextView titleTextView;
        View authorsLayout;

        private ViewHolder(View view) {
            titleTextView = view.findViewById(R.id.title_text_view);
            authorsLayout = view.findViewById(R.id.authors_view);
        }
    }
}
