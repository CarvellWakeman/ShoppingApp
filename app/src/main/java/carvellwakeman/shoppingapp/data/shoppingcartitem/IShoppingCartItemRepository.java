package carvellwakeman.shoppingapp.data.shoppingcartitem;

import android.arch.lifecycle.LiveData;
import carvellwakeman.shoppingapp.data.product.Product;

import java.util.List;

public interface IShoppingCartItemRepository {

    LiveData<List<Product>> getShoppingCartProducts();

    LiveData<Boolean> shoppingCartHasProduct(int productId);

    void createShoppingCartItem(ShoppingCartItem item);

    void deleteShoppingCartItem(int productId);

}
