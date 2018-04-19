package com.example.ifchyyy.librarybarcodescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * main activity class to hold the button for scanning the books
 */

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button scanButton;
    private TextView formatText, contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init scan button
        scanButton = (Button) findViewById(R.id.scan_button);
        scanButton.setOnClickListener(this);

        //init format text
        formatText = (TextView) findViewById(R.id.scan_format);

        //init content text
        contentText = (TextView) findViewById(R.id.scan_content);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == scanButton.getId()) {
            //init the scanIntegrator from ZxIng library
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            //initiate the scan on button pressed
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //intetent result returnred from the scanned object
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        //check if result is not null
        if (scanningResult != null) {
            //variables to stroe the format and content of the scanned item
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();

            //fill our text views with thtat information
            formatText.setText("Format: " + scanFormat);
            contentText.setText("Content: " + scanContent);

        } else {
            //if scanned result is equal to null, display a message

        }
    }
    

}
