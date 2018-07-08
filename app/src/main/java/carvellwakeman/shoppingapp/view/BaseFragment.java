package carvellwakeman.shoppingapp.view;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;


public abstract class BaseFragment<T extends ViewModel> extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    protected T viewModel;

    public void createViewModel() {
        viewModel = (T) ViewModelProviders.of(this, viewModelFactory).get(viewModel.getClass());
    }

}
