package carvellwakeman.shoppingapp.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


/*
 * This is an extension of a Room database object.
 * It specifies the tables (entities) which will be created in the database.
 */
@Database(entities = {Product.class}, exportSchema = false, version = 1)
public abstract class ShoppingDatabase extends RoomDatabase {

    public abstract IProductDao productDao();

}
