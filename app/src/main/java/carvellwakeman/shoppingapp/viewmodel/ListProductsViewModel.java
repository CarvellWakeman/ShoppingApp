package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.product.IProductRepository;
import carvellwakeman.shoppingapp.data.product.Product;

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

}
