package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.Product;

import javax.inject.Inject;
import java.util.List;


public class ListProductsViewModel extends ViewModel {

    private final IProductRepository repository;

    private LiveData<List<Product>> products;

    @Inject
    public ListProductsViewModel(IProductRepository repository) {
        this.repository = repository;
        this.products = repository.getProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        repository.createProduct(product);
        products = repository.getProducts();
    }

    public void removeProduct(int productId) {
        repository.deleteProduct(productId);
        products = repository.getProducts();
    }

}
