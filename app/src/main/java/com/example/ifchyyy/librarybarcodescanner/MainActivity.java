package com.example.ifchyyy.librarybarcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ifchyyy.librarybarcodescanner.dataclasses.Book;
import com.example.ifchyyy.librarybarcodescanner.dataclasses.BooksLab;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * main activity class to hold the button for scanning the books
 */

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button scanButton, previewButton;
    private TextView formatText, contentText;
    private RelativeLayout mainLayout;
    private TextView bookAuthor, bookTitle, bookDescription, bookDate;
    private ImageView thumbView;
    private String scanContent;
    private BooksLab bookLab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init relative layout so we can atach a snakcbar to it to show some info
        mainLayout = findViewById(R.id.main_layout);

        //init book lab
        bookLab = BooksLab.get(this);


        //init scan button
        scanButton = (Button) findViewById(R.id.scan_button);
        scanButton.setOnClickListener(this);

        //init format text
        formatText = (TextView) findViewById(R.id.scan_format);

        //init content text
        contentText = (TextView) findViewById(R.id.scan_content);


        ArrayList<Book> bookArrayList = bookLab.getBooks();
        Toast.makeText(this, bookArrayList.size() + " ", Toast.LENGTH_SHORT).show();
/*

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
*/

    }


    @Override
    public void onClick(View v) {
        //check if scan button pressed
        if (v.getId() == scanButton.getId()) {
            //init the scanIntegrator from ZxIng library
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //initiate the scan on button pressed
            scanIntegrator.initiateScan();

        }

     /*   //if preview button is pressed
        if (v.getId() == previewButton.getId()) {
            ArrayList<Book> bookArrayList = bookLab.getBooks();
            Toast.makeText(this, bookArrayList.size() + " ", Toast.LENGTH_SHORT).show();

        }*/


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //intetent result returnred from the scanned object
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //check if result is not null
        if (scanningResult != null) {
            //variables to stroe the format and content of the scanned item
            scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //fill our text views with thtat information
            formatText.setText("Format: " + scanFormat);
            contentText.setText("Content: " + scanContent);

            //if scanned result is equal to null, display a message
            Snackbar.make(mainLayout, "Found " + scanContent, Snackbar.LENGTH_LONG).show();

            //checl of scan content is not null and scan format not null and not ean scan format
            if (scanContent != null && scanFormat != null && scanFormat.equalsIgnoreCase("EAN_13")) {
                //get a reference of our scan content and api key searched in google books
                String bookSearchString = "https://www.googleapis.com/books/v1/volumes?" +
                        "q=isbn:" + scanContent + "&key=" + getResources().getString(R.string.google_books_api);


                handlingRequest(bookSearchString);
            } else {

            }

        } else {
            //if scanned result is equal to null, display a message
            Snackbar.make(mainLayout, "No result found", Snackbar.LENGTH_LONG).show();
        }
    }


    public void handlingRequest(String url) {
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        final Gson gson = new Gson();
        // pass second argument as "null" for GET requests
        final JsonObjectRequest req = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray itemsArray = response.getJSONArray("items");
                            JSONObject itemObject = itemsArray.getJSONObject(0);
                            JSONObject values = itemObject.getJSONObject("volumeInfo");

                            Book book = new Book();
                            book.setBookContent(scanContent);
                            book.setBookTitle(values.getString("title"));
                            book.setBookAuthor(values.getString("authors"));
                            book.setBookDescription(values.getString("description"));
                            book.setBookDate(values.getString("publishedDate"));
                            book.setBookPhoto(values.getJSONObject("imageLinks").getString("smallThumbnail"));
                            book.setBookPreviewLink(values.getString("previewLink"));


                            bookLab.addBook(book);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        mRequestQueue.add(req);
    }

}
