package com.samansepahvand.chat.api;

import android.app.Notification;

import com.samansepahvand.chat.model.NotificationModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {



    @POST("notification/Save")
    Call<NotificationModel> SaveNotif(@Body NotificationModel model );


}
