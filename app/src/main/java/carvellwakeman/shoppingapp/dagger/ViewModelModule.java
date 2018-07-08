package carvellwakeman.shoppingapp.dagger;


import android.arch.lifecycle.ViewModelProvider;
import carvellwakeman.shoppingapp.data.IProductRepository;
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
    ViewModelProvider.Factory provideViewModelFactory(IProductRepository repository){
        return new ViewModelFactory(repository);
    }

}
