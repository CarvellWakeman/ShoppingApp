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

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIApplicationComponent
                .builder()
                .roomModule(new RoomModule(this))
                .build();
    }

    public IApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
