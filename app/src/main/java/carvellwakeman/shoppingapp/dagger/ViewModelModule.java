package carvellwakeman.shoppingapp.dagger;


import android.arch.lifecycle.ViewModelProvider;
import carvellwakeman.shoppingapp.data.IProductRepository;
import carvellwakeman.shoppingapp.data.IShoppingCartItemRepository;
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
    ViewModelProvider.Factory provideViewModelFactory(IProductRepository productRepository, IShoppingCartItemRepository shoppingCartItemRepository){
        return new ViewModelFactory(productRepository, shoppingCartItemRepository);
    }

}
