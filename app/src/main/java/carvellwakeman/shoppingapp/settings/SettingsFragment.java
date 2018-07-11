package carvellwakeman.shoppingapp.settings;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.view.BaseActivity;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SettingsFragment extends BaseFragment<SettingsViewModel> {

    @BindView(R.id.buttonAdd)
    Button buttonAdd;
    @BindView(R.id.buttonDeleteAll) Button buttonDeleteAll;

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


        // (TEMP) Add new product button
        class SimpleProduct {
            String name;
            String desc;
            String url;

            public SimpleProduct(String name, String url, String desc) {
                this.name = name;
                this.desc = desc;
                this.url = url;
            }
        }
        Random random = new Random();
        List<SimpleProduct> products = new ArrayList<>();
        products.add(new SimpleProduct("EKEDALEN Chair", "https://i.imgur.com/YEv0vrF.jpg", "The upholstered seat and curved backrest make you want to stay seated at the table for a while. Choose between different covers and machine wash when needed – and why not have several at home for variety?"));
        products.add(new SimpleProduct("KULLABERG Chair", "https://i.imgur.com/cPjOz8g.jpg", "A desk chair inspired by old-fashioned industrial-style chairs, complete with modern functions."));
        products.add(new SimpleProduct("STEFAN Chair", "https://i.imgur.com/xAxuR2r.jpg", "Solid wood is a durable natural material."));
        products.add(new SimpleProduct("VIVO Dual LCD LED Monitor Desk Mount Stand", "https://i.imgur.com/Lz0UR7q.jpg", "Heavy Duty Fully Adjustable fits 2/Two Screens up to 27\""));
        products.add(new SimpleProduct("2010 Buick Regal", "https://i.imgur.com/kiXpolz.jpg", "It's a car with many moving pieces and some of them work."));


        buttonAdd.setOnClickListener((View v) -> {
            for (SimpleProduct p : products) {
                viewModel.addProduct(new Product(p.name, p.desc, p.url, random.nextInt(100), random.nextDouble() * 300.0d, random.nextInt(100), random.nextFloat() * 10.0f, random.nextFloat() * 50.0f, random.nextFloat() * 50.0f, random.nextFloat() * 50.0f));
            }
        });

        buttonDeleteAll.setOnClickListener((View v) -> {
            viewModel.deleteAllProducts();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
