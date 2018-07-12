package carvellwakeman.shoppingapp.settings;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.dagger.IApplicationComponent;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.User;
import carvellwakeman.shoppingapp.view.BaseActivity;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SettingsFragment extends BaseFragment<SettingsViewModel> {

    @BindView(R.id.buttonReset) Button buttonReset;

    // Required empty public constructor
    public SettingsFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger 2 injection
        ((ShoppingApplication) getActivity().getApplication()).getApplicationComponent().inject(SettingsFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, binding.getRoot());

        // Toolbar
        BaseActivity activity = (BaseActivity) getActivity();

        if (activity != null) {
            activity.setToolbarNav(R.drawable.menu, (View v) -> activity.openNavDrawer(GravityCompat.START));
            activity.setToolbarMenu(R.menu.empty_options, null);
            activity.setToolbarTitle(R.string.menu_settings);
        }

        buttonReset.setOnClickListener((View v) -> {
            loadData();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void loadData() {
        // Delete old data
        viewModel.deleteAllUsers();
        viewModel.deleteAllProducts();

        // Load new data
        ((ShoppingApplication)getActivity().getApplication()).loadData();
    }
}
