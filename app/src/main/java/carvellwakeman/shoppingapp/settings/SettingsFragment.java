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
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.User;
import carvellwakeman.shoppingapp.view.BaseActivity;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SettingsFragment extends BaseFragment<SettingsViewModel> {

    @BindView(R.id.selectUser)
    ConstraintLayout buttonSelectUser;
    @BindView(R.id.buttonAddProducts) Button buttonAddProducts;
    @BindView(R.id.buttonAddUsers) Button buttonAddUsers;
    @BindView(R.id.buttonDeleteProducts) Button buttonDeleteProducts;
    @BindView(R.id.buttonDeleteUsers) Button buttonDeleteUsers;

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
            SimpleProduct(String name, String url, String desc) { this.name = name; this.desc = desc; this.url = url; }
        }

        Random random = new Random();
        List<SimpleProduct> products = new ArrayList<>();
        products.add(new SimpleProduct("EKEDALEN Chair", "https://i.imgur.com/rf0un9p.jpg", "The upholstered seat and curved backrest make you want to stay seated at the table for a while. Choose between different covers and machine wash when needed – and why not have several at home for variety?"));
        products.add(new SimpleProduct("KULLABERG Chair", "https://i.imgur.com/8BumTCZ.jpg", "A desk chair inspired by old-fashioned industrial-style chairs, complete with modern functions."));
        products.add(new SimpleProduct("STEFAN Chair", "https://i.imgur.com/dj5pQmk.jpg", "Solid wood is a durable natural material."));
        products.add(new SimpleProduct("VIVO Dual LCD LED Monitor Desk Mount Stand", "https://i.imgur.com/adebgzu.png", "Heavy Duty Fully Adjustable fits 2/Two Screens up to 27\""));
        products.add(new SimpleProduct("2010 Buick Regal", "https://i.imgur.com/7vEeodV.jpg", "It's a car with many moving pieces and some of them work."));
        products.add(new SimpleProduct("Graphics Card FRQ-1090 11GbPs Streamer 2.3", "https://i.imgur.com/lVaIFYs.jpg", "Who would win in a fight: A beefy 2000 Cuda core monstrosity with the latest shader model, more vRam than your motherboard, dual fans and support for up to six monitors OR a bunch of silly virtual coins? Only time will tell."));
        products.add(new SimpleProduct("XBOX Station 5 Controller", "https://i.imgur.com/tmxqC1k.jpg", "Hand interface device used primarily by twelve year olds to destroy you in online video games. Comes in any color you want as long as it's white."));

        List<User> users = new ArrayList<>();
        users.add(new User("Zach Lerew", "lerew.zach@gmail.com", "https://i.imgur.com/g808qGJ.jpg"));
        users.add(new User("Giraffe", "giraffe86@yahoo.com", "https://i.imgur.com/cSBzhQI.jpg"));
        users.add(new User("Cow", "milkinator@gmail.com", "https://i.imgur.com/sNfFZnV.jpg"));

        // Products
        buttonAddProducts.setOnClickListener((View v) -> {
            for (SimpleProduct p : products) {
                viewModel.addProduct(new Product(p.name, p.desc, p.url, random.nextInt(100), random.nextDouble() * 300.0d, random.nextInt(100), random.nextFloat() * 10.0f, random.nextFloat() * 50.0f, random.nextFloat() * 50.0f, random.nextFloat() * 50.0f));
            }
        });

        buttonDeleteProducts.setOnClickListener((View v) -> {
            viewModel.deleteAllProducts();
        });

        // Users
        buttonAddUsers.setOnClickListener((View v) -> {
            for (User u : users) {
                viewModel.addUser(u);
            }
        });

        buttonDeleteUsers.setOnClickListener((View v) -> {
            viewModel.deleteAllUsers();
        });


        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
