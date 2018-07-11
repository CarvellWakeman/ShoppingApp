package carvellwakeman.shoppingapp.data.user;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


/*
 * This DAO (data access object) describes the actions that can be taken on its entity.
 * It is given to Room to in order to help build the database.
 */
@Dao
public interface IUserDao {

    // Get List
    @Query("SELECT * FROM User")
    LiveData<List<User>> getUsers();

    // Get Item
    @Query("SELECT * FROM User WHERE id = :userId")
    LiveData<User> getUser(int userId);

    // Insert/Replace Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    // Delete Item
    @Query("DELETE FROM User WHERE id = :userId")
    void deleteUser(int userId);

    // Delete all items
    @Query("DELETE FROM User")
    void deleteAllUsers();

}
