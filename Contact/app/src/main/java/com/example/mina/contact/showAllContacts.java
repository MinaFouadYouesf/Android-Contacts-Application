package com.example.mina.contact;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class showAllContacts extends AppCompatActivity {


    String name, phone, mail;
    ArrayList<listObject> arr = new ArrayList<listObject>();
    ListView listView;
    EditText search;
    ListAdapter adapter;
    ArrayList<listObject> listObjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_all_contacts);
            listView = (ListView) findViewById(R.id.list_item);
            search = (EditText) findViewById(R.id.search);
            //get intent and data that users enter in class entercontacts.java
            Intent i = getIntent();
            Bundle b = i.getExtras();
            //create object of database connection class
            DbConnection dp = new DbConnection(showAllContacts.this);


            /*
            if bundle carry data insert it to database
             */
            if (b != null) {
                name = b.getString("name");
                phone = b.getString("phone");
                mail = b.getString("mail");
                dp.InsertRowUser(name, phone, mail);

            }

            //get data from databse and put it in array
            arr = dp.getAllRecords();

            adapter = new ListAdapter(showAllContacts.this, R.layout.list_design, arr);
            listView.setAdapter(adapter);


             /*
             if user click item in contacts ListView go to userpage class with array(arr) that carry data
              */
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(showAllContacts.this, userPage.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userData", (Serializable) arr);
                    bundle.putInt("postion", position);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }
            });

            /*
            if user search in the search text
             */
            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // showAllContacts.this.adapter.getFilter().filter(s);

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int textlength = s.length();
                    listObjects = new ArrayList<listObject>();
                    for (listObject c : arr) {
                        if (textlength <= c.Name.length()) {
                            if (c.Name.toLowerCase().contains(s.toString().toLowerCase())) {
                                listObjects.add(c);
                            }
                        }
                    }
                    adapter = new ListAdapter(showAllContacts.this, R.layout.list_design, listObjects);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(showAllContacts.this, userPage.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("userData", (Serializable) listObjects);
                            bundle.putInt("postion", position);
                            intent.putExtra("bundle", bundle);
                            startActivity(intent);
                        }
                    });


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        } catch (NullPointerException E) {

        } catch (Exception e) {
        }
    }

    /*
    option menu that contain rate and add contact
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.rate) {

            Intent i = new Intent(showAllContacts.this, RatingApp5.class);
            startActivity(i);

        } else if (item.getItemId() == R.id.contacts) {

            Intent intent = new Intent(showAllContacts.this, EnterContacts.class);
            startActivity(intent);
        }
        return false;
    }

}
