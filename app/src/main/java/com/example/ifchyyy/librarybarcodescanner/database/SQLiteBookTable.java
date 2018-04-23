package com.example.ifchyyy.librarybarcodescanner.database;

/**
 * sql book class creating the fields of the database
 */
public class SQLiteBookTable  {

    //creatte table
    public static final class BookTable {
        public static final String NAME = "books";

        //create fields
        public static final class Columns {
            public static final String CONTENT_ID = "bookId";
            public static final String TITLE = "title";
            public static final String AUTHOR = "author";
            public static final String DESCRIPTION = "description";
            public static final String DATE = "date";
            public static final String PREVIEW = "preview";
            public static final String PHOTO = "photo";
            public static final String READ = "read";
            public static final String LENT = "lent";

        }
    }
}
