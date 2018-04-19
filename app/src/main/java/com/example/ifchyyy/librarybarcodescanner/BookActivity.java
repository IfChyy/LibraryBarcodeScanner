package com.example.ifchyyy.librarybarcodescanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ifchyyy.librarybarcodescanner.dataclasses.Book;
import com.example.ifchyyy.librarybarcodescanner.dataclasses.BooksLab;

import java.util.ArrayList;


public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    private Button  previewButton;
    private TextView bookAuthor, bookTitle, bookDescription, bookDate;
    private ImageView thumbView;
    private String scanContent;
    private BooksLab bookLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

//init book lab
        bookLab = BooksLab.get(this);


        //init preview button
        previewButton = findViewById(R.id.preview_btn);
        previewButton.setOnClickListener(this);
        //   previewButton.setVisibility(View.INVISIBLE);

        //init author thext
        bookAuthor = findViewById(R.id.book_author);

        //init book title text
        bookTitle = findViewById(R.id.book_title);

        //init book description text
        bookDescription = findViewById(R.id.book_description);

        //init date of book text
        bookDate = findViewById(R.id.book_date);

        //init book cover photo
        thumbView = findViewById(R.id.thumb);
    }

    @Override
    public void onClick(View v) {
        //if preview button is pressed
        if (v.getId() == previewButton.getId()) {
            ArrayList<Book> bookArrayList = bookLab.getBooks();
            Toast.makeText(this, bookArrayList.size() + " ", Toast.LENGTH_SHORT).show();

        }
    }
}
