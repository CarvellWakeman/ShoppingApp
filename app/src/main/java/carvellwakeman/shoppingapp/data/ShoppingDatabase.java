package carvellwakeman.shoppingapp.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Product.class}, version = 1)
public abstract class ShoppingDatabase extends RoomDatabase {

    public abstract IProductDao productDao();

}
