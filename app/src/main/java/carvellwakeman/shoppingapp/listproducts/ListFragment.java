package carvellwakeman.shoppingapp.listproducts;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.view.BaseActivity;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.ListProductsViewModel;


public class ListFragment extends BaseFragment<ListProductsViewModel> {

    @BindView(R.id.rec_products) RecyclerView recyclerView;


    // Required empty public constructor
    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger 2 injection
        ((ShoppingApplication)getActivity().getApplication())
                .getApplicationComponent()
                .inject(ListFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        // Toolbar
        BaseActivity activity = (BaseActivity) getActivity();

        if (activity != null) {
            activity.setToolbarNav(R.drawable.menu, (View v) -> activity.openNavDrawer(GravityCompat.START));
            activity.setToolbarMenu(R.menu.fragment_list_options, (MenuItem item) -> {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(getContext(), "Search not implemented", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_cart:
                        Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(R.id.shoppingCartFragment);
                        break;
                    default:
                        break;
                }
                return true;
            });
            activity.setToolbarTitle(R.string.app_name);
        }

        // RecyclerView boilerplate
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        // Subscribe recyclerview adapter to viewModel LiveData
        viewModel.getProducts().observe(this, products -> {
            if (recyclerView.getAdapter() == null) {
                recyclerView.setAdapter(new ProductAdapter(products, R.layout.item_product));
            }
            else {
                ((ProductAdapter)recyclerView.getAdapter()).updateElements(products);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
