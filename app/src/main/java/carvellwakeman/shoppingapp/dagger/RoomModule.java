package carvellwakeman.shoppingapp.dagger;


import android.app.Application;
import android.arch.persistence.room.Room;
import carvellwakeman.shoppingapp.data.*;
import carvellwakeman.shoppingapp.data.product.IProductDao;
import carvellwakeman.shoppingapp.data.product.IProductRepository;
import carvellwakeman.shoppingapp.data.product.ProductRepository;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemDao;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.shoppingcartitem.ShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.user.IUserDao;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.data.user.UserRepository;
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
    IShoppingCartItemRepository provideShoppingCartItemRepository(IProductDao productDao, IShoppingCartItemDao shoppingCartItemDao) {
        return new ShoppingCartItemRepository(productDao, shoppingCartItemDao);
    }

    @Provides
    @Singleton
    IUserRepository provideUserRepository(IUserDao userDao) {
        return new UserRepository(userDao);
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

    @Provides
    @Singleton
    IUserDao provideUserDao() { return database.userDao(); }

    // Database
    @Provides
    @Singleton
    ShoppingDatabase provideShoppingDatabase() {
        return database;
    }

}
