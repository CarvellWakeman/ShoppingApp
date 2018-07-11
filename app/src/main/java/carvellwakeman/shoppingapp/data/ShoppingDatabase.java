package carvellwakeman.shoppingapp.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import carvellwakeman.shoppingapp.data.product.IProductDao;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemDao;
import carvellwakeman.shoppingapp.data.shoppingcartitem.ShoppingCartItem;
import carvellwakeman.shoppingapp.data.user.IUserDao;
import carvellwakeman.shoppingapp.data.user.User;


/*
 * This is an extension of a Room database object.
 * It specifies the tables (entities) which will be created in the database.
 */
@Database(entities = {Product.class, ShoppingCartItem.class, User.class}, exportSchema = false, version = 7)
public abstract class ShoppingDatabase extends RoomDatabase {

    public abstract IProductDao productDao();
    public abstract IShoppingCartItemDao shoppingCartItemDao();
    public abstract IUserDao userDao();


//    public static MigrationContainer migrations;
//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("");
//        }
//    };
//    static {
//        migrations.addMigrations(MIGRATION_1_2);
//    }
}
