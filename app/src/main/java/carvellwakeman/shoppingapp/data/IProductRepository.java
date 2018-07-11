package carvellwakeman.shoppingapp.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface IProductRepository {

    LiveData<List<Product>> getProducts();

    LiveData<Product> getProduct(int productId);

    void createProduct(Product product);

    void deleteProduct(int productId);

    void deleteAllProducts();
}
