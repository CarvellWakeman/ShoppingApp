package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.product.IProductRepository;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.shoppingcartitem.ShoppingCartItem;

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

    public LiveData<Boolean> shoppingCartHasProduct(int userId, int productId) {
            return shoppingCartItemRepository.shoppingCartHasProduct(userId, productId);
    }

    public void addShoppingCartItem(int userId, int productId) {
        shoppingCartItemRepository.createShoppingCartItem(new ShoppingCartItem(userId, productId));
    }

}
