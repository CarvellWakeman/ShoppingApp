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
public class ProductRepository implements IProductRepository {

    // Local Room Database
    private final IProductDao productDao;

    @Inject
    public ProductRepository(IProductDao productDao) {
        this.productDao = productDao;
    }

    // Get Items
    @Override
    public LiveData<List<Product>> getProducts() {
        return productDao.getProducts();
    }

    // Get Item
    @Override
    public LiveData<Product> getProduct(String productId) {
        return productDao.getProduct(productId);
    }

    // Insert Item
    @Override
    public void createProduct(Product product) {
        AsyncTask.execute(() -> productDao.insertProduct(product));
    }

    // Delete Item
    @Override
    public void deleteProduct(Product product) {
        AsyncTask.execute(() -> productDao.deleteProduct(product));
    }

}
