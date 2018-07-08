package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import carvellwakeman.shoppingapp.data.IProductRepository;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final IProductRepository repository;

    @Inject
    public ViewModelFactory(IProductRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListViewModel.class)) {
            return (T) new ListViewModel(repository);
        }
        else {
            throw new IllegalArgumentException("ViewModel Class Not Found");
        }
    }

}
