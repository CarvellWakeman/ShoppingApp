package carvellwakeman.shoppingapp.dagger;


import android.arch.lifecycle.ViewModelProvider;
import carvellwakeman.shoppingapp.data.order.IProductOrderRepository;
import carvellwakeman.shoppingapp.data.product.IProductRepository;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.viewmodel.ViewModelFactory;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;


@Module
public class ViewModelModule {

    public ViewModelModule() {}

    // View Model Factory
    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(IUserRepository userRepository,
                                                      IProductRepository productRepository,
                                                      IShoppingCartItemRepository shoppingCartItemRepository,
                                                      IProductOrderRepository productOrderRepository) {
        return new ViewModelFactory(userRepository, productRepository, shoppingCartItemRepository, productOrderRepository);
    }

}
