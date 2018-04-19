package com.example.ifchyyy.librarybarcodescanner.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.ifchyyy.librarybarcodescanner.dataclasses.Book;

import java.util.Date;
import java.util.UUID;

public class BookCursorWrapper extends CursorWrapper {

    public BookCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    //get infromation about task
    public Book getBooks() {

        String bookTitle = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.CONTENT_ID));
        String bookAuthor = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.TITLE));
        String bookDescription = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.DESCRIPTION));
        String bookDate = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.DATE));
        String bookPreview = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.PREVIEW));
        String bookPhoto = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.PHOTO));
        int bookIsReaded = getInt(getColumnIndex(SQLiteBookTable.BookTable.Columns.READ));
        int bookIsLented = getInt(getColumnIndex(SQLiteBookTable.BookTable.Columns.LENT));


        Book book = new Book();
        book.setBookTitle(bookTitle);
        book.setBookAuthor(bookAuthor);
        book.setBookDescription(bookDescription);
        book.setBookDate(bookDate);
        book.setBookPhoto(bookPhoto);
        book.setBookPreviewLink(bookPreview);
        book.setRead(bookIsReaded != 0);
        book.setLent(bookIsReaded != 0);

        return book;
    }
}