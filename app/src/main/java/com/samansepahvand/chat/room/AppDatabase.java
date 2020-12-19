package com.samansepahvand.chat.room;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.samansepahvand.chat.room.dao.RoomDAO;


@TypeConverters({DateTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE = null;

    public abstract RoomDAO getRoomDAO();

    public static AppDatabase geAppdatabase(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"users")
                    .allowMainThreadQueries()
                   // .addMigrations(FROM_1_TO_2)
                    .build();
        }

        return INSTANCE;

    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
