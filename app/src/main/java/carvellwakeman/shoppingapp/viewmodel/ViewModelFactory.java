package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import carvellwakeman.shoppingapp.data.product.IProductRepository;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.user.IUserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final IProductRepository productRepository;
    private final IShoppingCartItemRepository shoppingCartItemRepository;
    private final IUserRepository userRepository;

    @Inject
    public ViewModelFactory(IUserRepository userRepository, IProductRepository productRepository, IShoppingCartItemRepository shoppingCartItemRepository) {
        this.productRepository = productRepository;
        this.shoppingCartItemRepository  = shoppingCartItemRepository;
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListProductsViewModel.class)) {
            return (T) new ListProductsViewModel(productRepository);
        }
        else if (modelClass.isAssignableFrom(DetailProductViewModel.class)) {
            return (T) new DetailProductViewModel(productRepository, shoppingCartItemRepository);
        }
        else if (modelClass.isAssignableFrom(SettingsViewModel.class)) {
            return (T) new SettingsViewModel(productRepository, userRepository);
        }
        else if (modelClass.isAssignableFrom(ShoppingCartViewModel.class)) {
            return (T) new ShoppingCartViewModel(shoppingCartItemRepository);
        }
        else {
            throw new IllegalArgumentException("ViewModel Class Not Found");
        }
    }

}
