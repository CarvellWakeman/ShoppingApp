package carvellwakeman.shoppingapp.data;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;


@Dao
public interface IProductDao {

    // Get List
    @Query("SELECT * FROM products")
    LiveData<List<Product>> getProducts();

    // Get Item
    @Query("SELECT * FROM products WHERE productId = :productId")
    LiveData<Product> getProduct(String productId);

    // Insert/Replace Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertProduct(Product product);

    // Delete Item
    @Delete
    Long deleteProduct(Product product);

}
