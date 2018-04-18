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

            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {

            String scanContent = scanningResult.getContents();


            String scanFormat = scanningResult.getFormatName();

            formatText.setText("FORMAT: " + scanFormat);
            contentText.setText("CONTENT: " + scanContent);

        } else {

            Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT).show();
        }
    }
}
