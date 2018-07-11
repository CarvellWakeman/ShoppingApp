package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.data.ShoppingCartItem;

import javax.inject.Inject;


public class DetailProductViewModel extends ViewModel {

    private final IProductRepository productRepository;
    private final IShoppingCartItemRepository shoppingCartItemRepository;

    @Inject
    public DetailProductViewModel(IProductRepository productRepository, IShoppingCartItemRepository shoppingCartRepository) {
        this.productRepository = productRepository;
        this.shoppingCartItemRepository = shoppingCartRepository;
    }

    public LiveData<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public LiveData<Boolean> shoppingCartHasProduct(int productId) {
            return shoppingCartItemRepository.shoppingCartHasProduct(productId);
    }

    public void addShoppingCartItem(int productId) {
        shoppingCartItemRepository.createShoppingCartItem(new ShoppingCartItem(productId));
    }

}
