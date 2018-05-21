package com.example.mina.contact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
splash screen using thread class
 */
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Intent intent = new Intent(MainActivity.this, showAllContacts.class);
                    startActivity(intent);
                    finish();
                } catch (Exception E) {

                }
            }
        };
        thread.start();
    }
}
