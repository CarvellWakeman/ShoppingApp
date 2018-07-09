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

    @Inject
    public ListProductsViewModel(IProductRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Product>> getProducts() {
        return repository.getProducts();
    }

    public LiveData<Product> getProduct(int productId) {
        return repository.getProduct(productId);
    }

    public void addProduct(Product product) {
        repository.createProduct(product);
    }

    public void removeProduct(int productId) {
        repository.deleteProduct(productId);
    }

    public int countProducts() {
        return repository.countProducts();
    }

}
