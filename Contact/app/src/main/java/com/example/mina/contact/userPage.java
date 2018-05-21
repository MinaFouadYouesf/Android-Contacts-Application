package com.example.mina.contact;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class userPage extends AppCompatActivity {

    int postion;
    String num, mail;
    TextView uName, chat_mail, mobile;
    ArrayList<listObject> data;
    ImageView message, call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Intent i = getIntent();
        Bundle b = i.getBundleExtra("bundle");
        data = (ArrayList<listObject>) b.getSerializable("userData");
        postion = b.getInt("postion");
        uName = (TextView) findViewById(R.id.uName);
        chat_mail = (TextView) findViewById(R.id.chat_mail);
        mobile = (TextView) findViewById(R.id.mobile);
        uName.setText(data.get(postion).Name);
        chat_mail.setText(data.get(postion).Email);
        mobile.setText(data.get(postion).Phone);
        message = (ImageView) findViewById(R.id.message);
        call = (ImageView) findViewById(R.id.call);
        registerForContextMenu(uName);
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "message content");
                String title = "message";
                Intent chooser = Intent.createChooser(sendIntent, title);
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
        /////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////
        num = data.get(postion).Phone;
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
                startActivity(intent);
            }
        });
        ///////////////////////////
        mail = data.get(postion).Email;
        chat_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:" + mail));
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
            }
        });
    }
    /////////////////////// Context Menu ///////////////////////////////
    DbConnection dp = new DbConnection(userPage.this);

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.delete){
            dp.delete(data.get(postion).Name);
            Intent intent=new Intent(userPage.this,showAllContacts.class);
            startActivity(intent);
        }
        return false;
    }

}
