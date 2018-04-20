package com.example.ifchyyy.librarybarcodescanner.dataclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ifchyyy.librarybarcodescanner.database.BookBaseHelper;
import com.example.ifchyyy.librarybarcodescanner.database.BookCursorWrapper;
import com.example.ifchyyy.librarybarcodescanner.database.SQLiteBookTable;
import com.example.ifchyyy.librarybarcodescanner.database.SQLiteBookTable.BookTable;

import java.util.ArrayList;
import java.util.UUID;

public class BooksLab{
    private SQLiteDatabase database;
    private static BooksLab tripsLab;

    //create a consturcotr for our tripLab with the database init
    BooksLab(Context context) {
        //init context;
        context = context.getApplicationContext();
        //init the database
        database = new BookBaseHelper(context).getWritableDatabase();

    }

    //get the instance of tripsLab if not created
    public static BooksLab get(Context context) {
        if (tripsLab == null) {
            tripsLab = new BooksLab(context);
        }
        return tripsLab;
    }

    public Book getBook(String contentId) {

        //search the DB fro trip with current id
        BookCursorWrapper cursor = queryBooks(BookTable.Columns.CONTENT_ID + "= ?",
                new String[]{contentId});


        try {
            //if trip not found cursro is empty - return
            if (cursor.getCount() == 0) {
                return null;
            }
            //if not empty get the first item from the query
            cursor.moveToFirst();
            //and return
            return cursor.getBooks();
        } finally {
            //close the cursor
            cursor.close();
        }

    }


    //add a trip to the database
    public void addBook(Book book) {
        ContentValues values = getContentValues(book);
        database.insert(BookTable.NAME, null, values);
    }

    //delete a trip from the database
    public void deleteBook(Book book) {
        database.delete(BookTable.NAME, BookTable.Columns.CONTENT_ID + " == \"" + book.getBookContent() + "\"", null);
    }

    ///method to update the infromation in the database
    //    // use "?" for adding the new id and preventing sql injection atack
    //    //to treat everything as a string
    public void updateBook(Book book) {
        ContentValues values = getContentValues(book);

        database.update(BookTable.NAME, values,
                BookTable.Columns.CONTENT_ID + " = ?",
                new String[]{book.getBookContent()});
    }

    //add rows of information about each trip to the database columns
    private static ContentValues getContentValues(Book book) {
        ContentValues values = new ContentValues();

        values.put(BookTable.Columns.CONTENT_ID, book.getBookContent());
        values.put(BookTable.Columns.TITLE, book.getBookTitle());
        values.put(BookTable.Columns.AUTHOR, book.getBookAuthor());
        values.put(BookTable.Columns.DESCRIPTION, book.getBookDescription());
        values.put(BookTable.Columns.DATE, book.getBookDate());
        values.put(BookTable.Columns.PREVIEW, book.getBookPreviewLink());
        values.put(BookTable.Columns.PHOTO, book.getBookPhoto());
        values.put(BookTable.Columns.READ, book.getRead() ? 1 : 0);
        values.put(BookTable.Columns.LENT, book.getLent() ? 1 : 0);

        return values;
    }



    //do a query in the DB to get values
    private BookCursorWrapper queryBooks(String whereClausem, String[] whereArgs) {
        Cursor cursor = database.query(BookTable.NAME,
                null,
                whereClausem,
                whereArgs,
                null,
                null,
                null);

        return new BookCursorWrapper(cursor);
    }


    //get all trips from the DB using where clause null "(*)" equl to select *

    public ArrayList<Book> getBooks(){
        ArrayList<Book> books = new ArrayList<>();
        BookCursorWrapper cursor = queryBooks(null, null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                books.add(cursor.getBooks());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return books;

    }
}