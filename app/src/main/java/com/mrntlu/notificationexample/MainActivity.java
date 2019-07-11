package com.mrntlu.notificationexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager=(NotificationManager) MainActivity.this.getSystemService(NOTIFICATION_SERVICE);

        findViewById(R.id.button).setOnClickListener(view -> sendNotification());

        findViewById(R.id.button2).setOnClickListener(view -> sendNotificationBigText());

        findViewById(R.id.button3).setOnClickListener(view -> sendNotificationLargeImage());

        findViewById(R.id.button4).setOnClickListener(view -> {
                    sendNotificationWithAction();
        });


    }

    public NotificationManager notificationEssentials(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = "Test";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("123",name,importance);
            channel.setDescription(description);
            channel.enableVibration(true); //This means when notification is posted to this channel it should vibrate.
            channel.enableLights(true); //This means when notification is posted to this channel it should display lights.
            channel.setLightColor(R.color.colorAccent); //It sets the light color for notification posted to this channel.

            notificationManager.createNotificationChannel(channel);
        }

        return notificationManager;
    }

    public void sendNotification(){
        notificationEssentials();

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this, "123")
                    .setContentTitle("Test")
                    .setContentText("Hi this is first notification") //The content that will be shown on notification
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT) //Sets the priority of notification , if the priority is high then more likely to interrupt the user.
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }

    public void sendNotificationBigText(){
        notificationEssentials();

        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this, "123")
                    .setContentTitle("Test")
                    .setContentText("Hi this is first notification")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setSmallIcon(R.drawable.ic_launcher_foreground) // The logo that will be on status bar
                    .setContentIntent(pendingIntent)
                    .setStyle(new NotificationCompat.BigTextStyle() //Adds a large text expandeable
                            .bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. " +
                                    "Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. " +
                                    "Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos."))
                    .setAutoCancel(true); //When pressed on notification menu whether disapper or not

        notificationManager.notify(1, builder.build());
    }

    public void sendNotificationLargeImage(){
        notificationEssentials();

        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"123")
                    .setContentTitle("Test")
                    .setContentText("Hi this is first notification")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setSmallIcon(R.drawable.ic_notification_24dp)
                    .setContentIntent(pendingIntent)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.notification_image))
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture( BitmapFactory.decodeResource(this.getResources(),
                                    R.drawable.notification_image))
                            .bigLargeIcon(null))
                    .setAutoCancel(false);

        notificationManager.notify(1, builder.build());
    }

    public void sendNotificationLockScreen(){
        //Create Notification Channel
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "Test";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("123",name,importance);
            channel.setDescription(description);
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.setLightColor(R.color.colorAccent);

            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"123")
                .setContentTitle("Test")
                .setContentText("Hi this is first notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notification_24dp)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),R.drawable.notification_image))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture( BitmapFactory.decodeResource(this.getResources(),
                                R.drawable.notification_image))
                        .bigLargeIcon(null))
                .setAutoCancel(false);

        notificationManager.notify(1,builder.build());

    }

    public void sendNotificationWithAction(){
        notificationEssentials();

        Intent intent = new Intent(this, SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Intent viewIntent = new Intent(this, ThirdActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"123")
                .setContentTitle("Test")
                .setContentText("Hi this is first notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_notification_24dp)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.drawable.notification_image))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture( BitmapFactory.decodeResource(this.getResources(),
                                R.drawable.notification_image))
                        .bigLargeIcon(null))
                .addAction(R.drawable.ic_launcher_foreground , "View" , viewPendingIntent)
                .setAutoCancel(false);


        notificationManager.notify(1, builder.build());
    }

    @Override
    protected void onPause() {
        super.onPause();
        sendNotificationLockScreen();
    }
}
