package com.example.mina.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class EnterContacts extends AppCompatActivity {


    EditText txtName, txtphone, txtEmail;
    Button bu;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_contacts);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtName = (EditText) findViewById(R.id.txtName);
        txtphone = (EditText) findViewById(R.id.txtphone);
    }

    public void check_Contact(View view) {
        /*
        create alert dialog to ensure that user want to save or no
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(EnterContacts.this);
        builder.setCancelable(false).setMessage("are you sure").setTitle("Alert Dialog");
        ///////left button
        builder.setNegativeButton("yes", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(EnterContacts.this, showAllContacts.class);
                intent.putExtra("name", txtName.getText().toString());
                intent.putExtra("phone", txtphone.getText().toString());
                intent.putExtra("mail", txtEmail.getText().toString());
                startActivity(intent);
            }
        });
        //right button
        builder.setPositiveButton("No", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        builder.show();
    }

    /////////////////////////////////////////////////////////////////////////


}
