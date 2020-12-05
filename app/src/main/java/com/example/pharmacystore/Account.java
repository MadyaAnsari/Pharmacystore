package com.example.pharmacystore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;

import static java.util.Locale.getDefault;

public class Account extends AppCompatActivity {

    Button getmyloc;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView aduname, aduemail, aducontact, adulat, adulong, aduadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getmyloc = (Button) findViewById(R.id.agetmyloc);

        aduname = (TextView) findViewById(R.id.aaduname);
        aduemail = (TextView) findViewById(R.id.aaduemail);
        aducontact = (TextView) findViewById(R.id.aaducontact);
        adulat = (TextView) findViewById(R.id.aadulat);
        adulong = (TextView) findViewById(R.id.aadulong);
        aduadd = (TextView) findViewById(R.id.aaduadd);

        aduname.setText("Madya");
        aduemail.setText("Ansarimadya80@gmail.com");
        aducontact.setText("7303672440");

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        getmyloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                getLocation();

                //} else {
                //ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                //}
            }
        });
    }


    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    Geocoder geocoder = new Geocoder(Account.this,
                            getDefault());

                    try {

                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        adulat.setText(String.valueOf(addresses.get(0).getLatitude()));
                        adulong.setText(String.valueOf(addresses.get(0).getLongitude()));
                        aduadd.setText(String.valueOf(addresses.get(0).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}