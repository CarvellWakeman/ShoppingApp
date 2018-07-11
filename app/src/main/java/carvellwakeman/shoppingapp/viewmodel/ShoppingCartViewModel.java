package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.data.user.User;

import javax.inject.Inject;
import java.util.List;


public class ShoppingCartViewModel extends ViewModel {

    private final IUserRepository userRepository;
    private final IShoppingCartItemRepository shoppingCartItemRepository;

    @Inject
    public ShoppingCartViewModel(IUserRepository userRepository, IShoppingCartItemRepository shoppingCartItemRepository) {
        this.userRepository = userRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;
    }

    public LiveData<List<Product>> getProducts() {
        return shoppingCartItemRepository.getShoppingCartProducts();
    }

    public void removeProduct(int productId) {
        shoppingCartItemRepository.deleteShoppingCartItem(productId);
    }

    public LiveData<User> getActiveUser() { return userRepository.getActiveUser(); }
}
