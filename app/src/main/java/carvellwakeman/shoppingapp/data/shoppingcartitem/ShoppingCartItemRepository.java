package carvellwakeman.shoppingapp.data.shoppingcartitem;


import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import carvellwakeman.shoppingapp.data.order.IProductOrderDao;
import carvellwakeman.shoppingapp.data.order.ProductOrder;
import carvellwakeman.shoppingapp.data.product.IProductDao;
import carvellwakeman.shoppingapp.data.product.Product;

import javax.inject.Inject;
import java.util.List;


/*
 * The repository exists as an abstraction layer between a DAO (data access object) and application logic (viewModel)
 * A repository can gather information from a local DB, a remote API, device preferences, or any other source.
 * This repository is implemented from an interface to increase testability.
 */
public class ShoppingCartItemRepository implements IShoppingCartItemRepository {

    // Local Room Database
    private final IProductDao productDao;
    private final IShoppingCartItemDao shoppingCartItemDao;
    private final IProductOrderDao orderDao;

    @Inject
    public ShoppingCartItemRepository(IProductDao productDao, IShoppingCartItemDao shoppingCartItemDao, IProductOrderDao orderDao) {
        this.productDao = productDao;
        this.shoppingCartItemDao = shoppingCartItemDao;
        this.orderDao = orderDao;
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
        AsyncTask.execute(() -> productDao.addProductQuantity(shoppingCartItem.getProductId(), -1));
    }

    @Override
    public void deleteShoppingCartItem(int productId) {
        AsyncTask.execute(() -> shoppingCartItemDao.deleteShoppingCartItem(productId));
        AsyncTask.execute(() -> productDao.addProductQuantity(productId, 1));
    }

    @Override
    public void purchaseProducts(int userId) {
        AsyncTask.execute(() -> {
            List<Product> products = shoppingCartItemDao.getShoppingCartProductsSync();

            for (Product p : products) {
                orderDao.insertOrder(new ProductOrder(userId, p.getId(), System.currentTimeMillis()));
            }

            shoppingCartItemDao.deleteUserItems();
        });
    }
}
