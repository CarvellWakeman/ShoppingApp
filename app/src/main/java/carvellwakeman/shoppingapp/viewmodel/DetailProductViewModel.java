package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.Product;

import javax.inject.Inject;


public class DetailProductViewModel extends ViewModel {

    private final IProductRepository repository;

    @Inject
    public DetailProductViewModel(IProductRepository repository) {
        this.repository = repository;
    }

    public LiveData<Product> getProduct(int productId) {
        return repository.getProduct(productId);
    }

}
