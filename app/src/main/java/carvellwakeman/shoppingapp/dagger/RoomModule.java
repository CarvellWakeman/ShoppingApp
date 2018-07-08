package carvellwakeman.shoppingapp.dagger;


import android.app.Application;
import android.arch.persistence.room.Room;
import carvellwakeman.shoppingapp.data.IProductDao;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.ProductRepository;
import carvellwakeman.shoppingapp.data.ShoppingDatabase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;


@Module
public class RoomModule {

    private final String DB_NAME = "shop.db";
    private final ShoppingDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(application, ShoppingDatabase.class, DB_NAME)
                .build();
    }

    // Repositories
    @Provides
    @Singleton
    IProductRepository provideProductRepository(IProductDao productDao) {
        return new ProductRepository(productDao);
    }

    // DAOs
    @Provides
    @Singleton
    IProductDao provideProductDao() {
        return database.productDao();
    }

    // Database
    @Provides
    @Singleton
    ShoppingDatabase provideShoppingDatabase() {
        return database;
    }

}
