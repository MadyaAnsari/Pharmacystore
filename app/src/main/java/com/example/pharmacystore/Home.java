package com.example.pharmacystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private Button loc_button;
    private ImageView imageView11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageView11 = (ImageView) findViewById(R.id.imageView11) ;
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    openActivity5();
                }
        });



        loc_button = (Button) findViewById(R.id.loc_button);
        loc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String strUri = "http://maps.google.com/maps?q=loc:" + 18.9620326 + "," + 72.829751 + " (" + "AM Pharma" + ")";
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri));
//
//                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
//
//                startActivity(intent);

                openActivity4();
            }
        });
    }

    public void openActivity4() {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void openActivity5() {
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }
}