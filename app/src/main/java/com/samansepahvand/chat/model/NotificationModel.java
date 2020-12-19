package com.samansepahvand.chat.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("NotifTitle")
    @Expose
    private  String NotifTitle;

    @SerializedName("NotifBody")
    @Expose
    private  String NotifBody;

    public NotificationModel(String notifTitle, String notifBody) {
        NotifTitle = notifTitle;
        NotifBody = notifBody;
    }

    public String getNotifTitle() {
        return NotifTitle;
    }

    public void setNotifTitle(String notifTitle) {
        NotifTitle = notifTitle;
    }

    public String getNotifBody() {
        return NotifBody;
    }

    public void setNotifBody(String notifBody) {
        NotifBody = notifBody;
    }
}
