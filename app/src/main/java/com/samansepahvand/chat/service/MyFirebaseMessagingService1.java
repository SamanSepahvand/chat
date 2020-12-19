package com.samansepahvand.chat.service;//package com.samansepahvand.pushe.service;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.util.Log;
//
//import androidx.core.app.NotificationCompat;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//import com.samansepahvand.pushe.R;
//import com.samansepahvand.pushe.model.ModelNotification;
//import com.samansepahvand.pushe.room.AppDatabase;
//import com.samansepahvand.pushe.room.NoteDatabase;
//import com.samansepahvand.pushe.room.dao.RoomDAO;
//import com.samansepahvand.pushe.ui.ActivityOpenItem;
//import com.samansepahvand.pushe.ui.ActivityUpdate;
//import com.samansepahvand.pushe.ui.MainActivity;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class MyFirebaseMessagingService1 extends FirebaseMessagingService {
//
//    private static final String TAG = "firebase";
//    private AppDatabase appDatabase1;
//    private NoteDatabase appDatabase;
//    private Notification notification;
//    private NotificationManager notificationManager;
//
//    private PendingIntent pendingIntent;
//    private Intent intent;
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        saveNotificationToRoom(remoteMessage);
//
//        Log.e(TAG, "onMessageReceived  tempTitle: " + remoteMessage.getNotification().getTitle());
//        Log.e(TAG, "onMessageReceived  tempBody: " + remoteMessage.getNotification().getBody());
//
//
//        if (remoteMessage.getNotification().getBody() != null) {
//
//
//            String tempTitle, tempBody, tempType;
//            tempBody = remoteMessage.getNotification().getBody();
//            tempTitle = remoteMessage.getNotification().getTitle();
//            tempType = remoteMessage.getData().get("key_2");
//
//
//            getNotification(tempTitle, tempBody, tempType);
//
//
//        }
//    }
//
//    private void saveNotificationToRoom(RemoteMessage remote) {
//
//        appDatabase = NoteDatabase.getInstance(this);
//        RoomDAO roomDAO = appDatabase.getNoteDao();
//
//        ModelNotification model = new ModelNotification();
//
//        //notification
//        model.setNotifTitle(remote.getNotification().getTitle());
//        model.setNotifBody(remote.getNotification().getBody());
//
//        //data
//        model.setDataBody(remote.getData().get("body"));
//        model.setDataTitle(remote.getData().get("title"));
//        model.setDataKey1(remote.getData().get("key_1"));
//        model.setDataKey2(remote.getData().get("key_2"));
//
//        //helper
//        model.setNotifDate(currentDateAndTime());
//        roomDAO.Insert(model);
//
//    }
//
//    private String currentDateAndTime() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
//        String currentDateandTime = sdf.format(new Date());
//        return currentDateandTime;
//    }
//
//    private void sendNotification(String messageBody) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle("FCM Message")
//                .setContentText(messageBody)
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//    }
//
//    public void getNotification(String title, String body, String type) {
//        String tempType = null;
//        if (type != null) {
//            tempType = type.toLowerCase();
//
//            if (tempType.equals("update")) {
//                intent = new Intent(MyFirebaseMessagingService1.this, ActivityUpdate.class);
//                pendingIntent = PendingIntent.getActivity(MyFirebaseMessagingService1.this,
//                        1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//            } else if (tempType.equals("openitem")) {
//                intent = new Intent(MyFirebaseMessagingService1.this, ActivityOpenItem.class);
//                intent.putExtra("title", title);
//                intent.putExtra("body", body);
//                pendingIntent = PendingIntent.getActivity(MyFirebaseMessagingService1.this,
//                        1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//            } else if (tempType.equals("info")) {
//                intent = new Intent(MyFirebaseMessagingService1.this, MainActivity.class);
//                intent.putExtra("title", title);
//                intent.putExtra("body", body);
//                pendingIntent = PendingIntent.getActivity(MyFirebaseMessagingService1.this,
//                        1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//            }
//        } else {
//            intent = new Intent(MyFirebaseMessagingService1.this, MainActivity.class);
//            pendingIntent = PendingIntent.getActivity(MyFirebaseMessagingService1.this,
//                    1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        }
//
////        Intent intent = new Intent(MyFirebaseMessagingService.this, MainActivity.class);
////        PendingIntent pendingIntent = PendingIntent.getActivity(MyFirebaseMessagingService.this,
////                1, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//
//        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String channelID = "1";
//            String channelName = "news";
//            String channelDesc = "news description";
//            NotificationChannel notificationChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
//            notificationChannel.setDescription(channelDesc);
//            notificationChannel.enableLights(true);
//            notificationChannel.setSound(null, null);
//            notificationChannel.setLightColor(Color.GREEN);
//
//            RingtoneManager.getRingtone(this,
//                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).play();
//            notificationManager.createNotificationChannel(notificationChannel);
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setVibrate(new long[]{100, 500, 500, 500, 500})
//                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                    .setContentIntent(pendingIntent);
//            notification = builder.build();
//            notificationManager.notify(0, notification);
//        } else {
//            String channeld = "saman";
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channeld)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle(title)
//                    .setContentText(body)
//                    .setVibrate(new long[]{100, 500, 500, 500, 500})
//                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//                    .setContentIntent(pendingIntent);
//            notification = builder.getNotification();
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForeground(0, notification);
//
//        } else {
//            notificationManager.notify(0, notification);
//        }
//
//
//        //CancelNotification(this,notifID);
//
//    }
//
//
//    public static void CancelNotification(Context ctx, int notifyId) {
//        String s = Context.NOTIFICATION_SERVICE;
//        NotificationManager mNM = (NotificationManager) ctx.getSystemService(s);
//        mNM.cancel(notifyId);
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //appDatabase.cleanUp();
//    }
//}
//
