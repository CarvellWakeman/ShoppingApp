package carvellwakeman.shoppingapp.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;


/*
 * This DAO (data access object) describes the actions that can be taken on its entity.
 * It is given to Room to in order to help build the database.
 */
@Dao
public interface IProductDao {

    // Get List
    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getProducts();

    // Get Item
    @Query("SELECT * FROM Product WHERE id = :productId")
    LiveData<Product> getProduct(int productId);

    // Insert/Replace Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    // Delete Item
    @Query("DELETE FROM Product WHERE id = :productId")
    void deleteProduct(int productId);

    // Delete all items
    @Query("DELETE FROM Product")
    void deleteAllProducts();

    // Change quantity
    @Query("UPDATE Product SET quantity = quantity + :quantity WHERE id = :productId")
    void addProductQuantity(int productId, int quantity);

}
