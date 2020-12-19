package com.samansepahvand.chat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.samansepahvand.chat.R;
import com.samansepahvand.chat.api.APIClient;
import com.samansepahvand.chat.api.APIInterface;
import com.samansepahvand.chat.model.NotificationModel;
import com.samansepahvand.chat.room.NoteDatabase;
import com.samansepahvand.chat.room.dao.RoomDAO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private  static final String TAG="firebase";



    TextView txtInfo;
    TextView txtToken;

    Button btnShowNotification;


    APIClient apiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        initView();
        getToken();

        FirebaseMessaging.getInstance().subscribeToTopic("all");



        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationModel model=new NotificationModel("hellop","world");

                sendMessage(model);


            }
        });


        btnShowNotification.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DeleteAllNotification();
                return false;
            }
        });
    }

    private void sendMessage(NotificationModel model){

        APIInterface apiInterface=APIClient.getClient().create(APIInterface.class);

        Call<NotificationModel> call= apiInterface.SaveNotif(model);
        call.enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {

                if (response.isSuccessful()) {
                    Log.e(TAG, "isSuccessful: "+response.body().getNotifBody());
                }else {
                    Log.e(TAG, "onResponse" +response.body());
                }


            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });


    }




    private void DeleteAllNotification() {
//        AppDatabase appDatabase = AppDatabase.geAppdatabase(getApplicationContext());
//        RoomDAO dao = appDatabase.getRoomDAO();
//
        NoteDatabase appDatabase = NoteDatabase.getInstance(this);
        RoomDAO dao = appDatabase.getNoteDao();

        dao.DeleteAll();
        Toast.makeText(this, "All Data Deleted Success!", Toast.LENGTH_SHORT).show();
    }





    private void initView(){
        txtInfo=findViewById(R.id.txt_info);
        txtToken=findViewById(R.id.txt_token);
        btnShowNotification=findViewById(R.id.btn_show_notif);
    }
    private void getToken(){

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.e(TAG, newToken);

            txtToken.setText(newToken);
        });

    }
}