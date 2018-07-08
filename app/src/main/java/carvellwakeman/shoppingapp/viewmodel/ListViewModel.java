package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.IProductRepository;

import javax.inject.Inject;


public class ListViewModel extends ViewModel {

    private final IProductRepository repository;
    private int count = 0;

    @Inject
    public ListViewModel(IProductRepository repository) {
        this.repository = repository;
    }


    public int getCount() { return count; }

    public void incCount() { count += 1; }

}
