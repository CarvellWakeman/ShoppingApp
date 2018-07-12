package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.Nullable;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.data.user.User;

import javax.inject.Inject;
import java.util.List;


public class ShoppingCartViewModel extends ViewModel {

    private final IUserRepository userRepository;
    private final IShoppingCartItemRepository shoppingCartItemRepository;

    private MutableLiveData<Double> subTotalCost;
    private MutableLiveData<Double> tax;

    @Inject
    public ShoppingCartViewModel(IUserRepository userRepository, IShoppingCartItemRepository shoppingCartItemRepository) {
        this.userRepository = userRepository;
        this.shoppingCartItemRepository = shoppingCartItemRepository;

        tax = new MutableLiveData<>();
        subTotalCost = new MutableLiveData<>();

        tax.setValue(0.0d);
        subTotalCost.setValue(0.0d);
    }

    public LiveData<List<Product>> getProducts() {
        return shoppingCartItemRepository.getShoppingCartProducts();
    }

    public void removeProduct(int productId) {
        shoppingCartItemRepository.deleteShoppingCartItem(productId);
    }

    public void purchaseProducts(int userId) {
        shoppingCartItemRepository.purchaseProducts(userId);
    }

    public LiveData<User> getActiveUser() { return userRepository.getActiveUser(); }


    // State values
    public LiveData<Double> getTax() { return tax; }
    public LiveData<Double> getSubTotalCost() { return subTotalCost; }


    public void calculateSubTotal(List<Product> products) {
        Double cost = 0.0d;
        for (Product p : products) {
            cost += p.getCost();
        }
        subTotalCost.postValue(cost);
    }


}
