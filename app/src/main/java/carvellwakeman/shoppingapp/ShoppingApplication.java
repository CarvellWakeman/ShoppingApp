package carvellwakeman.shoppingapp;


import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import carvellwakeman.shoppingapp.dagger.DaggerIApplicationComponent;
import carvellwakeman.shoppingapp.dagger.IApplicationComponent;
import carvellwakeman.shoppingapp.dagger.RoomModule;
import carvellwakeman.shoppingapp.data.product.IProductDao;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.IUserDao;
import carvellwakeman.shoppingapp.data.user.User;
import carvellwakeman.shoppingapp.settings.SettingsFragment;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ShoppingApplication extends Application {

    private final String PREF_KEY = "ShoppingAppPrefs";
    private final String FIRST_RUN_KEY = "HasFirstRun";

    private IApplicationComponent applicationComponent;

    private SharedPreferences sharedPreferences;

    @Inject IProductDao productDao; // Dagger 2 cannot inject into private fields
    @Inject IUserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerIApplicationComponent
                .builder()
                .roomModule(new RoomModule(this))
                .build();

        // Dagger 2 injection
        applicationComponent.inject(ShoppingApplication.this);

        // Get shared preferences
        sharedPreferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

        if (!sharedPreferences.getBoolean(FIRST_RUN_KEY, false)) {
           loadData();
           SharedPreferences.Editor editor = sharedPreferences.edit();
           editor.putBoolean(FIRST_RUN_KEY, true);
           editor.apply();
        }
    }

    public IApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void loadData() {
        Random random = new Random();
        List<SimpleProduct> products = new ArrayList<>();
        products.add(new SimpleProduct("EKEDALEN Chair", "https://i.imgur.com/rf0un9p.jpg", "The upholstered seat and curved backrest make you want to stay seated at the table for a while. Choose between different covers and machine wash when needed – and why not have several at home for variety?"));
        products.add(new SimpleProduct("KULLABERG Chair", "https://i.imgur.com/8BumTCZ.jpg", "A desk chair inspired by old-fashioned industrial-style chairs, complete with modern functions."));
        products.add(new SimpleProduct("STEFAN Chair", "https://i.imgur.com/dj5pQmk.jpg", "Solid wood is a durable natural material."));
        products.add(new SimpleProduct("VIVO Dual LCD LED Monitor Desk Mount Stand", "https://i.imgur.com/adebgzu.png", "Heavy Duty Fully Adjustable fits 2/Two Screens up to 27\""));
        products.add(new SimpleProduct("2010 Buick Regal", "https://i.imgur.com/7vEeodV.jpg", "It's a car with many moving pieces and some of them work."));
        products.add(new SimpleProduct("Graphics Card FRQ-1090 11GbPs Streamer 2.3", "https://i.imgur.com/lVaIFYs.jpg", "Who would win in a fight: A beefy 2000 Cuda core monstrosity with the latest shader model, more vRam than your motherboard, dual fans and support for up to six monitors OR a bunch of silly virtual coins? Only time will tell."));
        products.add(new SimpleProduct("XBOX Station 5 Controller", "https://i.imgur.com/tmxqC1k.jpg", "Hand interface device used primarily by twelve year olds to destroy you in online video games. Comes in any color you want as long as it's white."));

        List<User> users = new ArrayList<>();
        users.add(new User("Zach Lerew", "lerew.zach@gmail.com", "https://i.imgur.com/g808qGJ.jpg"));
        users.add(new User("Giraffe", "giraffe86@yahoo.com", "https://i.imgur.com/cSBzhQI.jpg"));
        users.add(new User("Cow", "milkinator@gmail.com", "https://i.imgur.com/sNfFZnV.jpg"));

        // Add new data
        AsyncTask.execute(() -> {
            for (SimpleProduct p : products) {
                productDao.insertProduct(new Product(p.name, p.desc, p.url, random.nextInt(100), random.nextDouble() * 300.0d, random.nextInt(100), random.nextFloat() * 10.0f, random.nextFloat() * 50.0f, random.nextFloat() * 50.0f, random.nextFloat() * 50.0f));
            }

            for (User u : users) {
                userDao.insertUser(u);
            }
        });
    }

    class SimpleProduct {
        String name;
        String desc;
        String url;
        SimpleProduct(String name, String url, String desc) { this.name = name; this.desc = desc; this.url = url; }
    }

}
