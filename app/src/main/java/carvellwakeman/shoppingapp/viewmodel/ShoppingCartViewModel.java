package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.product.Product;

import javax.inject.Inject;
import java.util.List;


public class ShoppingCartViewModel extends ViewModel {

    private final IShoppingCartItemRepository repository;

    @Inject
    public ShoppingCartViewModel(IShoppingCartItemRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Product>> getProducts(int userId) {
        return repository.getShoppingCartProducts(userId);
    }

    public void removeProduct(int productId) {
        repository.deleteShoppingCartItem(productId);
    }

}
