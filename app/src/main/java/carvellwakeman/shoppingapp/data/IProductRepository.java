package carvellwakeman.shoppingapp.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface IProductRepository {

    LiveData<List<Product>> getProducts();

    LiveData<Product> getProduct(String productId);

    Long createProduct(Product product);

    int deleteProduct(Product product);

}