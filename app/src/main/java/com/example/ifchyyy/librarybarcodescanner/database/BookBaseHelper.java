package com.example.ifchyyy.librarybarcodescanner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BookBaseHelper extends SQLiteOpenHelper {
    private static final int VERISON = 1;
    private static final String DATABASE_NAME = "booksDatabase.db";

    //init the database
    public BookBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERISON);
    }

    //create the three tables with their collumns
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + SQLiteBookTable.BookTable.NAME + "("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SQLiteBookTable.BookTable.Columns.CONTENT_ID + ", "
                + SQLiteBookTable.BookTable.Columns.TITLE + ", "
                + SQLiteBookTable.BookTable.Columns.AUTHOR + ", "
                + SQLiteBookTable.BookTable.Columns.DESCRIPTION + ", "
                + SQLiteBookTable.BookTable.Columns.DATE + ", "
                + SQLiteBookTable.BookTable.Columns.PREVIEW + ", "
                + SQLiteBookTable.BookTable.Columns.PHOTO + ", "
                + SQLiteBookTable.BookTable.Columns.READ + ", "
                + SQLiteBookTable.BookTable.Columns.LENT + " )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}