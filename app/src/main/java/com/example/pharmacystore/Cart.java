package com.example.pharmacystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cart extends AppCompatActivity {
    Button checkoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        checkoutbtn = (Button) findViewById(R.id.checkoutbtn);
        checkoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Congratulation, Your order has been placed";
                String title = "Order Placed";

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String CHANNEL_ID = "my_channel_01";
                    CharSequence name = "my_channel";
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    notificationManager.createNotificationChannel(notificationChannel);


                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Cart.this, CHANNEL_ID).setSmallIcon(R.drawable.ic_cart).setContentTitle(title).setContentText(message).setAutoCancel(true);

                    Intent intent = new Intent(Cart.this,
                            Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //intent.putExtra("message",message)

                    PendingIntent pendingIntent = PendingIntent.getActivity(Cart.this,
                            0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    builder.setContentIntent(pendingIntent);

                    notificationManager.notify(0, builder.build());
                }

            }
        });

    }
}