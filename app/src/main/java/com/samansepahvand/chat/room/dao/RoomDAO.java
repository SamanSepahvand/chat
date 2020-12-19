package com.samansepahvand.chat.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.samansepahvand.chat.model.ModelNotification;

import java.util.List;


@Dao
public interface RoomDAO {


    @Insert
    public void Insert(ModelNotification... reminders);

    @Update
    public void Update(ModelNotification... reminders);



    @Query("Delete from modelnotification")
    public void DeleteAll();

    @Delete
    public void Delete(ModelNotification reminders);

    @Query("Select * from modelnotification order by id")
    public List<ModelNotification> orderThetable();

    @Query("Select * from modelnotification")
    public List<ModelNotification> getAll();





}
