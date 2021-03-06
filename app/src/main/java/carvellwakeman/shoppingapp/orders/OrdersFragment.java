package carvellwakeman.shoppingapp.orders;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.BR;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.utils.SwipeDeleteCallback;
import carvellwakeman.shoppingapp.view.BaseActivity;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.view.CustomBaseAdapter;
import carvellwakeman.shoppingapp.viewmodel.OrdersViewModel;
import carvellwakeman.shoppingapp.viewmodel.ShoppingCartViewModel;


public class OrdersFragment extends BaseFragment<OrdersViewModel> {

    @BindView(R.id.rec_products)
    RecyclerView recyclerView;

    // Required empty public constructor
    public OrdersFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger 2 injection
        ((ShoppingApplication) getActivity().getApplication()).getApplicationComponent().inject(OrdersFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_orders, container, false);
        binding.setLifecycleOwner(this);
        ButterKnife.bind(this, binding.getRoot());

        // Data binding
        binding.setVariable(BR.viewModel, viewModel);

        BaseActivity activity = (BaseActivity) getActivity();

        if (activity != null) {
            // Toolbar
            activity.setToolbarNav(R.drawable.arrow_left, (View v) -> activity.getNavController().navigateUp());
            activity.setToolbarMenu(R.menu.empty_options, null);
            activity.setToolbarTitle(R.string.menu_orders);

            // RecyclerView boilerplate
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

            // Subscribe recyclerview adapter to viewModel LiveData
            viewModel.getProducts().observe(this, products -> {
                if (products != null) {
                    binding.setVariable(BR.productsCount, products.size());

                    if (recyclerView.getAdapter() == null) {
                        recyclerView.setAdapter(new OrdersAdapter(products, R.layout.item_order));
                    }
                    else {
                        ((OrdersAdapter) recyclerView.getAdapter()).updateElements(products);
                    }
                }
            });
        }

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
