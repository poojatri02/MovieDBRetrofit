package com.ptrivedi.moviedbretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class movieinfo extends AppCompatActivity {
    public static final String NAME_KEY = "NAME_ID";
    public static final String REVIVE_KEY = "REVIVE_ID";
    public static final String IMAGE_KEY = "IMAGE_ID";

    private TextView txt_name;
    private TextView txt_review;
    private ImageView MovieImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movieinfo);


        txt_name = (TextView)findViewById(R.id.movie_name_2);
        txt_review = (TextView)findViewById(R.id.movie_review);
        MovieImage = (ImageView)findViewById(R.id.movie_image_2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {

            String name = extras.getString(NAME_KEY);
            String revive = extras.getString(REVIVE_KEY);
            String imagePath = extras.getString(IMAGE_KEY);
            String imageUrl = String.format("http://image.tmdb.org/t/p/w185"+ imagePath);

            Picasso.with(this).load(imageUrl).resize(185,280).into(MovieImage);

            txt_name.setText(name);
            txt_review.setText(revive);
        }

    }

}

