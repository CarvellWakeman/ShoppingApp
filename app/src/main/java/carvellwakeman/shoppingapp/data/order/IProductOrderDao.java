package carvellwakeman.shoppingapp.data.order;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.shoppingcartitem.ShoppingCartItem;

import java.util.List;


/*
 * This DAO (data access object) describes the actions that can be taken on its entity.
 * It is given to Room to in order to help build the database.
 */
@Dao
public interface IProductOrderDao {

    // Get List
    @Query("SELECT p.* FROM Product p INNER JOIN ProductOrder o on o.productId = p.id WHERE o.userId IN (SELECT userId FROM ActiveUser LIMIT 1)")
    LiveData<List<Product>> getOrders();

    // Insert/Replace Item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrder(ProductOrder order);

    // Delete Item
    @Query("DELETE FROM ProductOrder WHERE id = :orderId")
    void deleteOrder(int orderId);

    // Delete All Items
    @Query("DELETE FROM ProductOrder WHERE userId IN (SELECT userId FROM ActiveUser LIMIT 1)")
    void deleteAllOrders();

}
