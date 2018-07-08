package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.Product;

import javax.inject.Inject;


public class ListViewModel extends ViewModel {

    private final IProductRepository repository;
    private MutableLiveData<Integer> count;

    @Inject
    public ListViewModel(IProductRepository repository) {
        this.repository = repository;
        this.count = new MutableLiveData<>();
        this.count.setValue(0);
    }


    public LiveData<Product> getProduct(String productId) { return repository.getProduct(productId); }

    public void addProduct() {
        repository.createProduct(new Product("a", "ProductName", "Product Description", 2, 1.0f, 1.0f, 1.0f, 1.0f));
    }

}
