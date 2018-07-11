package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.data.ShoppingCartItem;

import javax.inject.Inject;
import java.util.List;


public class ShoppingCartViewModel extends ViewModel {

    private final IShoppingCartItemRepository repository;

    @Inject
    public ShoppingCartViewModel(IShoppingCartItemRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Product>> getProducts() {
        return repository.getShoppingCartProducts();
    }

    public void removeProduct(int productId) {
        repository.deleteShoppingCartItem(productId);
    }

}
