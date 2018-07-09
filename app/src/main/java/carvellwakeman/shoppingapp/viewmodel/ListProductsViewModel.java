package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.Product;

import javax.inject.Inject;


public class ListProductsViewModel extends ViewModel {

    private final IProductRepository repository;
    private MutableLiveData<Integer> count;

    @Inject
    public ListProductsViewModel(IProductRepository repository) {
        this.repository = repository;
        this.count = new MutableLiveData<>();
        this.count.setValue(0);
    }

    public LiveData<Product> getProductA() { return repository.getProduct("a"); }
    public LiveData<Product> getProduct(String productId) { return repository.getProduct(productId); }

    public void addProduct(Product product) {
        repository.createProduct(new Product("a", "ProductName", "Product Description", 2, 1.0f, 1.0f, 1.0f, 1.0f));
    }

    public void removeProduct(String productId) {
        repository.deleteProduct(productId);
    }

}
