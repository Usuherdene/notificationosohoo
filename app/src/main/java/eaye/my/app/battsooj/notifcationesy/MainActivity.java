package eaye.my.app.battsooj.notifcationesy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.click);
        btn.setOnClickListener(v -> {
            createNotif();
        });
    }
    private void createNotif()
    {
        String id = "My_channel_id_01";
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O)
        {
            NotificationChannel channel = manager.getNotificationChannel(id);
            if (channel == null)
            {
                channel = new NotificationChannel(id,"Channel titel", NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("[Ghennel description]");
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100,1000,200,340});
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);

            }
        }
        Intent notificationIntent = new Intent(this,NotificationActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contenIntent = PendingIntent.getActivity(this,0, notificationIntent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, id)
                .setSmallIcon(R.drawable.icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.icon2))
                        .bigLargeIcon(null))
                .setContentTitle("Шинэ мэдэгдэл")
                .setContentText("Сайн уу")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{100,1000,200,340})
                .setAutoCancel(false)
                .setTicker("Мэдэгдэл");
        builder.setContentIntent(contenIntent);
        NotificationManagerCompat m = NotificationManagerCompat.from(getApplicationContext());
        m.notify(1,builder.build());
    }
}
