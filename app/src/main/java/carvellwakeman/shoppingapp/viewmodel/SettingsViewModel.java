package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.product.IProductRepository;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.data.user.User;

import javax.inject.Inject;


public class SettingsViewModel extends ViewModel {

    private final IProductRepository productRepository;
    private final IUserRepository userRepository;

    @Inject
    public SettingsViewModel(IProductRepository productRepository, IUserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void addProduct(Product product) {
        productRepository.createProduct(product);
    }

    public void deleteAllProducts() {
        productRepository.deleteAllProducts();
    }

    public void addUser(User user) { userRepository.createUser(user); }

    public void deleteAllUsers() { userRepository.deleteAllUsers(); }

}
