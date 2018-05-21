package com.example.mina.contact;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RatingApp5 extends AppCompatActivity {

    RatingBar ratingBar;
    TextView ratingValue,lastRating;
    /*
    rating class enables users to rate app
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_app5);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingValue = (TextView) findViewById(R.id.ratingValue);
        lastRating = (TextView) findViewById(R.id.lastRating);
        /*
        create shared preference to save last rate
         */
        loadData();
        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("data",0);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
/*
if click rating bar set the new rate
 */
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                editor.putString("rating",String.valueOf(rating));
                editor.commit();

                ratingValue.setText("Rating :  " + String.valueOf(rating));


                Toast.makeText(getApplicationContext(),
                        "new rating:- "+ String.valueOf(rating), Toast.LENGTH_LONG).show();
            }
        });


    }
    public void loadData(){

    SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("data",0);
    String savesRate=sharedPreferences.getString("rating","no rating");
        lastRating.setText("Last Rating : "+savesRate);
        ratingValue.setText(String.valueOf(ratingBar.getRating()));
        float num=Float.parseFloat(savesRate);
        ratingBar.setRating(num);
    }
}
