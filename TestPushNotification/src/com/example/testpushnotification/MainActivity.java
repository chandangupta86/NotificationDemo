package com.example.testpushnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	int counter = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void sendNotification(View v)
	{
		sendNotification(this);
	}

	
	public void sendNotification(MainActivity ctx)
	{
		counter++;
		Intent notificationIntent = new Intent(ctx, TestActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(ctx,100+counter, notificationIntent,PendingIntent.FLAG_CANCEL_CURRENT);

		NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

		Resources res = getApplicationContext().getResources();
		android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(ctx);
		builder.setContentIntent(contentIntent)
		            .setSmallIcon(R.drawable.ic_launcher)
		            .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_launcher))
		            .setTicker(res.getString(R.string.your_ticker))
		            .setWhen(System.currentTimeMillis())
		            .setAutoCancel(true)
		            .setContentTitle(res.getString(R.string.your_notif_title))
		            .setContentText(res.getString(R.string.your_notif_text));
		Notification n = builder.build();
		n.defaults |= Notification.DEFAULT_SOUND;
		n.defaults |= Notification.DEFAULT_VIBRATE;
 

		nm.notify(100+counter, n);
	}
}
