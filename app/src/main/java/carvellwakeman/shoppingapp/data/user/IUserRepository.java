package carvellwakeman.shoppingapp.data.user;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface IUserRepository {

    LiveData<List<User>> getUsers();

    LiveData<User> getUser(int userId);

    void createUser(User user);

    void deleteUser(int userId);

    void deleteAllUsers();

    LiveData<User> getActiveUser();

    void setActiveUser(int userId);
}
