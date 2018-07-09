package carvellwakeman.shoppingapp.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface IProductRepository {

    LiveData<List<Product>> getProducts();

    LiveData<Product> getProduct(String productId);

    void createProduct(Product product);

    void deleteProduct(String productId);

}
