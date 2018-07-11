package carvellwakeman.shoppingapp.data;


import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import javax.inject.Inject;
import java.util.List;


/*
 * The repository exists as an abstraction layer between a DAO (data access object) and application logic (viewModel)
 * A repository can gather information from a local DB, a remote API, device preferences, or any other source.
 * This repository is implemented from an interface to increase testability.
 */
public class ShoppingCartItemRepository implements IShoppingCartItemRepository {

    // Local Room Database
    private final IShoppingCartItemDao shoppingCartItemDao;

    @Inject
    public ShoppingCartItemRepository(IShoppingCartItemDao shoppingCartItemDao) {
        this.shoppingCartItemDao = shoppingCartItemDao;
    }

    @Override
    public LiveData<List<Product>> getShoppingCartProducts() {
        return shoppingCartItemDao.getShoppingCartProducts();
    }

    @Override
    public LiveData<Boolean> shoppingCartHasProduct(int productId) {
        return shoppingCartItemDao.shoppingCartHasProduct(productId);
    }

    @Override
    public void createShoppingCartItem(ShoppingCartItem shoppingCartItem) {
        AsyncTask.execute(() -> shoppingCartItemDao.insertShoppingCartItem(shoppingCartItem));
    }

    @Override
    public void deleteShoppingCartItem(int productId) {
        AsyncTask.execute(() -> shoppingCartItemDao.deleteShoppingCartItem(productId));
    }

}
