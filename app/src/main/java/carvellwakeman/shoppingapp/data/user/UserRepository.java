package carvellwakeman.shoppingapp.data.user;


import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import javax.inject.Inject;
import java.util.List;


/*
 * The repository exists as an abstraction layer between a DAO (data access object) and application logic (viewModel)
 * A repository can gather information from a local DB, a remote API, device preferences, or any other source.
 * This repository is implemented from an interface to increase testability.
 */
public class UserRepository implements IUserRepository {

    // Local Room Database
    private final IUserDao userDao;

    @Inject
    public UserRepository(IUserDao userDao) {
        this.userDao = userDao;
    }

    // Get Items
    public LiveData<List<User>> getUsers() {
        return userDao.getUsers();
    }

    // Get Item
    public LiveData<User> getUser(int userId) {
        return userDao.getUser(userId);
    }

    // Insert Item
    public void createUser(User user) {
        AsyncTask.execute(() -> userDao.insertUser(user));
    }

    // Delete Item
    public void deleteUser(int userId) {
        AsyncTask.execute(() -> userDao.deleteUser(userId));
    }

    public void deleteAllUsers() { AsyncTask.execute(() -> userDao.deleteAllUsers()); }
}
