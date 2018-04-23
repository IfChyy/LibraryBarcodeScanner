package com.example.ifchyyy.librarybarcodescanner.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ifchyyy.librarybarcodescanner.BookActivity;
import com.example.ifchyyy.librarybarcodescanner.R;
import com.example.ifchyyy.librarybarcodescanner.dataclasses.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Adapter class helper for populating the arraylist of books
 */
public class BookListAdapter extends ArrayAdapter<Book> {

    private ArrayList<Book> trips;
    private Context context;


    public BookListAdapter (Context context, ArrayList<Book> tripArrayList){
        super(context, R.layout.book_list_item, tripArrayList);
        this.trips = tripArrayList;
        this.context = context;

    }
    //view holder class helper for holding each item individually
    private class ViewHolder{
        ImageView bookCover;
        TextView bookTitle;
        CheckBox read, lented;
    }



    //-------------makes switches not change from on to off and vise versa while scrolling
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    //--------------------------------------------------------------------------------------------


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Book book = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            //------------inflate the layout
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.book_list_item, parent, false);

            //----------set the title and cover of each book
            holder.bookTitle = convertView.findViewById(R.id.bookTitle);
            holder.bookTitle.setText(book.getBookTitle());

            holder.bookCover = convertView.findViewById(R.id.bookCover);
            Picasso.get().load(book.getBookPhoto()).resize(100,100).into(holder.bookCover);

            //set two checkboxes for read or lent book
            holder.read = convertView.findViewById(R.id.read);
            holder.read.setChecked(book.getRead());
            holder.read.setClickable(false);

            holder.lented = convertView.findViewById(R.id.lent);
            holder.lented.setChecked(book.getLent());
            holder.lented.setClickable(false);




            //get if book clicked to open a new activity showing book info
            convertView.setTag(holder);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(context, BookActivity.class);
                    in.putExtra("bookContent", book.getBookContent());
                    context.startActivity(in);
                }
            });


        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}