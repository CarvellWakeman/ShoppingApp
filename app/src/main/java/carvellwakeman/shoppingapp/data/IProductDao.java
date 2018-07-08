package carvellwakeman.shoppingapp.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;


/*
 * This DAO (data access object) describes the actions that can be taken on the Product entity.
 * It is given to Room to in order to help build the database.
 */
@Dao
public interface IProductDao {

    // Get List
    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getProducts();

    // Get Item
    @Query("SELECT * FROM Product WHERE id = :productId")
    LiveData<Product> getProduct(String productId);

    // Insert/Replace Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    // Delete Item
    @Delete
    void deleteProduct(Product product);

}
