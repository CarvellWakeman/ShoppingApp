package carvellwakeman.shoppingapp.data;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;


/*
 * This is an extension of a Room database object.
 * It specifies the tables (entities) which will be created in the database.
 */
@Database(entities = {Product.class, ShoppingCartItem.class}, exportSchema = false, version = 6)
public abstract class ShoppingDatabase extends RoomDatabase {

    public abstract IProductDao productDao();
    public abstract IShoppingCartItemDao shoppingCartItemDao();


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
