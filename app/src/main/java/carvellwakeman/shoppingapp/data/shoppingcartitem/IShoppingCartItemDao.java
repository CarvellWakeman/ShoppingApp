package carvellwakeman.shoppingapp.data.shoppingcartitem;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import carvellwakeman.shoppingapp.data.product.Product;

import java.util.List;


/*
 * This DAO (data access object) describes the actions that can be taken on its entity.
 * It is given to Room to in order to help build the database.
 */
@Dao
public interface IShoppingCartItemDao {

    // Get List
    @Query("SELECT p.* FROM Product p INNER JOIN ShoppingCartItem s on s.productId = p.id WHERE s.userId IN (SELECT userId FROM ActiveUser LIMIT 1)")
    LiveData<List<Product>> getShoppingCartProducts();

    // Has Item
    @Query("SELECT EXISTS(SELECT 1 FROM ShoppingCartItem s WHERE s.productId = :productId AND s.userId IN (SELECT userId FROM ActiveUser LIMIT 1) LIMIT 1)")
    LiveData<Boolean> shoppingCartHasProduct(int productId);

    // Insert/Replace Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShoppingCartItem(ShoppingCartItem shoppingCartItem);

    // Delete Item
    @Query("DELETE FROM ShoppingCartItem WHERE productId = :productId")
    void deleteShoppingCartItem(int productId);

}
