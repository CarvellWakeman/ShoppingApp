package carvellwakeman.shoppingapp;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import carvellwakeman.shoppingapp.dagger.DaggerIApplicationComponent;
import carvellwakeman.shoppingapp.dagger.IApplicationComponent;
import carvellwakeman.shoppingapp.dagger.RoomModule;
import carvellwakeman.shoppingapp.data.user.User;


public class ShoppingApplication extends Application {

    private IApplicationComponent applicationComponent;

    private Integer userId;

    // Shared preferences
    private SharedPreferences sharedPreferences;
    private final String PREF_KEY = "shoppingAppPrefs";
    private final String USER_KEY = "currentUserId";

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIApplicationComponent
                .builder()
                .roomModule(new RoomModule(this))
                .build();

        // Initially load user
        sharedPreferences = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        userId = sharedPreferences.getInt(USER_KEY, -1);
    }

    public IApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setUser(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(USER_KEY, userId);
        editor.apply();

        this.userId = userId;
    }

    public Integer getUser() { return userId; }

}
