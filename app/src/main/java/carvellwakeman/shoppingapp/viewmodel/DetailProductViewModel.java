package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import carvellwakeman.shoppingapp.data.product.IProductRepository;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.shoppingcartitem.ShoppingCartItem;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.data.user.User;

import javax.inject.Inject;


public class DetailProductViewModel extends ViewModel {

    private final IUserRepository userRepository;
    private final IProductRepository productRepository;
    private final IShoppingCartItemRepository shoppingCartItemRepository;

    @Inject
    public DetailProductViewModel(IUserRepository userRepository, IProductRepository productRepository, IShoppingCartItemRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.shoppingCartItemRepository = shoppingCartRepository;
    }

    public LiveData<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public LiveData<Boolean> shoppingCartHasProduct(int productId) {
            return shoppingCartItemRepository.shoppingCartHasProduct(productId);
    }

    public void addShoppingCartItem(int userId, int productId) {
        shoppingCartItemRepository.createShoppingCartItem(new ShoppingCartItem(userId, productId));
    }

    public LiveData<User> getActiveUser() { return userRepository.getActiveUser(); }

}
