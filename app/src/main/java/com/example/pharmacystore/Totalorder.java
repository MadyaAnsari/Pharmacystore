package com.example.pharmacystore;


import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import android.os.Bundle;
import android.widget.TextView;

public class Totalorder extends AppCompatActivity {

    Spinner dropdown;
    Button calculatetbtn;
    TextView ov,dv,sv,tv,q;
    String med;
    String qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totalorder);

        dropdown = (Spinner) findViewById(R.id.spinner1);
        calculatetbtn = (Button) findViewById(R.id.calculatebtn);
        ov = (TextView) findViewById(R.id.orderview);
        dv = (TextView) findViewById(R.id.discountview);
        sv = (TextView) findViewById(R.id.shippingview);
        tv = (TextView) findViewById(R.id.totalview);
        q = (TextView) findViewById(R.id.quantity);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.medicine_arrays));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                med = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        calculatetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               qty =  q.getText().toString();
               int orderc = 0;
               int discp;
               int shippingc = 0;
               int total = 0;
               if(med.equals("Crocin")){
                   orderc = 20*Integer.parseInt(qty);
               } else if (med.equals("Brufen")){
                   orderc = 40*Integer.parseInt(qty);
               } else if (med.equals("Cetrizine")){
                   orderc = 30*Integer.parseInt(qty);
               } else if (med.equals("Saridon")){
                   orderc = 35*Integer.parseInt(qty);
               }

               ov.setText(Integer.toString(orderc));
               int temp = 0;

               if(orderc<200){
                   dv.setText("-");
                   sv.setText("40");
                   temp = orderc+40;
                   tv.setText(Integer.toString(temp));
               } else if (orderc >= 200 && orderc <500){
                   dv.setText("10%");
                   sv.setText("20");
                   temp = (((orderc*90)/100)+20);
                   tv.setText(Integer.toString(temp));
               } else if (orderc>=500){
                   dv.setText("20%");
                   sv.setText("-");
                   temp = ((orderc*80)/100);
                   tv.setText(Integer.toString(temp));
               }

            }
        });


    }

}