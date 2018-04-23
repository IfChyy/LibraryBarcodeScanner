package com.example.ifchyyy.librarybarcodescanner.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.ifchyyy.librarybarcodescanner.dataclasses.Book;

import java.util.Date;
import java.util.UUID;

/**
 * Book cursor wrapper class helper class for querying through each book
 */
public class BookCursorWrapper extends CursorWrapper {

    public BookCursorWrapper(Cursor cursor) {
        super(cursor);
    }


    //get infromation about task
    public Book getBooks() {
        String bookContent = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.CONTENT_ID));
        String bookTitle = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.TITLE));
        String bookAuthor = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.AUTHOR));
        String bookDescription = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.DESCRIPTION));
        String bookDate = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.DATE));
        String bookPreview = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.PREVIEW));
        String bookPhoto = getString(getColumnIndex(SQLiteBookTable.BookTable.Columns.PHOTO));
        int bookIsRead = getInt(getColumnIndex(SQLiteBookTable.BookTable.Columns.READ));
        int bookIsLent = getInt(getColumnIndex(SQLiteBookTable.BookTable.Columns.LENT));

        Book book = new Book();
        book.setBookContent(bookContent);
        book.setBookTitle(bookTitle);
        book.setBookAuthor(bookAuthor);
        book.setBookDescription(bookDescription);
        book.setBookDate(bookDate);
        book.setBookPhoto(bookPhoto);
        book.setBookPreviewLink(bookPreview);
        book.setRead(bookIsRead != 0);
        book.setLent(bookIsLent != 0);




        return book;
    }
}