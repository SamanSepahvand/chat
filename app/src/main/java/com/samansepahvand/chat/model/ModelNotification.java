package com.samansepahvand.chat.model;




import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "modelnotification")
public class ModelNotification {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;


    //notificaton section

     String notifTitle;
     String notifBody;

    //data of notification section
     String dataTitle;
     String dataBody;
     String dataKey1;
     String dataKey2;



    //more variable for more help
     String  notifDate;


    public ModelNotification() {
    }

    public String getNotifDate() {
        return notifDate;
    }

    public void setNotifDate(String notifDate) {
        this.notifDate = notifDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNotifTitle() {
        return notifTitle;
    }

    public void setNotifTitle(String notifTitle) {
        this.notifTitle = notifTitle;
    }

    public String getNotifBody() {
        return notifBody;
    }

    public void setNotifBody(String notifBody) {
        this.notifBody = notifBody;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataBody() {
        return dataBody;
    }

    public void setDataBody(String dataBody) {
        this.dataBody = dataBody;
    }

    public String getDataKey1() {
        return dataKey1;
    }

    public void setDataKey1(String dataKey1) {
        this.dataKey1 = dataKey1;
    }

    public String getDataKey2() {
        return dataKey2;
    }

    public void setDataKey2(String dataKey2) {
        this.dataKey2 = dataKey2;
    }
}
