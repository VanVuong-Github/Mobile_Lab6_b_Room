package com.example.lab6_b_room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDAO {
    @Query("SELECT * FROM location")
    List<Location> getAll();

    @Insert
    void addLocation(Location location);

    @Delete
    void deleteLocation(Location location);

    @Update
    void updateLocation(Location location);

    @Query("DELETE FROM location")
    void deleteAll();
}
