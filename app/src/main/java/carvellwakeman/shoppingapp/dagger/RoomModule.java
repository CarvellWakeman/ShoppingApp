package carvellwakeman.shoppingapp.dagger;


import android.app.Application;
import android.arch.persistence.room.Room;
import carvellwakeman.shoppingapp.data.*;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;


@Module
public class RoomModule {

    private final String DB_NAME = "shop.db";
    private final ShoppingDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(application, ShoppingDatabase.class, DB_NAME)
                //.allowMainThreadQueries() // TODO: WARNING - DEBUGGING ONLY
                .fallbackToDestructiveMigration()
                .build();
    }

    // Repositories
    @Provides
    @Singleton
    IProductRepository provideProductRepository(IProductDao dao) {
        return new ProductRepository(dao);
    }

    @Provides
    @Singleton
    IShoppingCartItemRepository provideShoppingCartItemRepository(IShoppingCartItemDao dao) {
        return new ShoppingCartItemRepository(dao);
    }


    // DAOs
    @Provides
    @Singleton
    IProductDao provideProductDao() {
        return database.productDao();
    }

    @Provides
    @Singleton
    IShoppingCartItemDao provideShoppingCartItemDao() {
        return database.shoppingCartItemDao();
    }

    // Database
    @Provides
    @Singleton
    ShoppingDatabase provideShoppingDatabase() {
        return database;
    }

}
