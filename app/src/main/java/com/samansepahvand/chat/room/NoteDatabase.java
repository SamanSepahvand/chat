package com.samansepahvand.chat.room;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.samansepahvand.chat.model.ModelNotification;
import com.samansepahvand.chat.room.dao.RoomDAO;


/**
 * Created by Pavneet_Singh on 12/31/17.
 */

@Database(entities = { ModelNotification.class }, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class NoteDatabase extends RoomDatabase {

    public abstract RoomDAO getNoteDao();

    private static NoteDatabase noteDB;

    // synchronized is use to avoid concurrent access in multithred environment
    public static /*synchronized*/ NoteDatabase getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }


    private static NoteDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                NoteDatabase.class,
                "Table1").allowMainThreadQueries().build();
    }


    public  void cleanUp(){
        noteDB = null;
    }
}