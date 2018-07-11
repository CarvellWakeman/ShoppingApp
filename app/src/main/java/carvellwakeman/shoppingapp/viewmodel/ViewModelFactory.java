package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.IShoppingCartItemRepository;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final IProductRepository productRepository;
    private final IShoppingCartItemRepository shoppingCartItemRepository;

    @Inject
    public ViewModelFactory(IProductRepository productRepository, IShoppingCartItemRepository shoppingCartItemRepository) {
        this.productRepository = productRepository;
        this.shoppingCartItemRepository  = shoppingCartItemRepository;
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
            return (T) new SettingsViewModel(productRepository);
        }
        else if (modelClass.isAssignableFrom(ShoppingCartViewModel.class)) {
            return (T) new ShoppingCartViewModel(shoppingCartItemRepository);
        }
        else {
            throw new IllegalArgumentException("ViewModel Class Not Found");
        }
    }

}
