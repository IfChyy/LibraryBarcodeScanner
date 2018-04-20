package com.example.ifchyyy.librarybarcodescanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ifchyyy.librarybarcodescanner.dataclasses.Book;
import com.example.ifchyyy.librarybarcodescanner.dataclasses.BooksLab;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    private Button previewButton;
    private TextView bookAuthor, bookTitle, bookDescription, bookDate;
    private ImageView thumbView;
    private BooksLab bookLab;
    private Book book;
    private CheckBox read, lent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //init book lab
        bookLab = BooksLab.get(this);

        //get extra from calling intent
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("bookContent") != null) {
            book = bookLab.getBook(bundle.getString("bookContent"));
        }

        //init preview button
        previewButton = findViewById(R.id.preview_btn);
        previewButton.setOnClickListener(this);

        //init author thext
        bookAuthor = findViewById(R.id.book_author);
        bookAuthor.setText(book.getBookAuthor());

        //init book title text
        bookTitle = findViewById(R.id.book_title);
        bookTitle.setText(book.getBookTitle());

        //init book description text
        bookDescription = findViewById(R.id.book_description);
        bookDescription.setText(book.getBookDescription());

        //init date of book text
        bookDate = findViewById(R.id.book_date);
        bookDate.setText(book.getBookDate());

        //init book cover photo
        thumbView = findViewById(R.id.thumb);
        Picasso.get().load(book.getBookPhoto()).into(thumbView);

        read = findViewById(R.id.book_read);
        read.setChecked(book.getRead());
        read.setOnClickListener(this);

        lent = findViewById(R.id.book_lent);
        lent.setChecked(book.getLent());
        lent.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.update_book) {
            bookLab.updateBook(book);
            finish();
        }

        if (item.getItemId() == R.id.delete_book) {
            bookLab.deleteBook(book);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //if preview button is pressed
        if (v.getId() == previewButton.getId()) {
            ArrayList<Book> bookArrayList = bookLab.getBooks();
            Toast.makeText(this, bookArrayList.size() + " ", Toast.LENGTH_SHORT).show();

        }
        //check if book read and set it
        if (v.getId() == read.getId()) {
            book.setRead(!book.getRead());

            Toast.makeText(this, book.getRead() + " ", Toast.LENGTH_SHORT).show();
        }

        //check if book lent set it
        if (v.getId() == lent.getId()) {
            book.setLent(!book.getLent());
        }
    }
}