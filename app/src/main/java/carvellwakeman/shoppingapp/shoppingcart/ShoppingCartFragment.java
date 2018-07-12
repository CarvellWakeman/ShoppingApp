package carvellwakeman.shoppingapp.shoppingcart;


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
import carvellwakeman.shoppingapp.viewmodel.ShoppingCartViewModel;


public class ShoppingCartFragment extends BaseFragment<ShoppingCartViewModel> {

    @BindView(R.id.rec_products)
    RecyclerView recyclerView;
    @BindView(R.id.button_checkout)
    Button buttonCheckout;

    // Required empty public constructor
    public ShoppingCartFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger 2 injection
        ((ShoppingApplication) getActivity().getApplication()).getApplicationComponent().inject(ShoppingCartFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shoppingcart, container, false);
        binding.setLifecycleOwner(this);
        ButterKnife.bind(this, binding.getRoot());

        // Data binding
        binding.setVariable(BR.viewModel, viewModel);

        BaseActivity activity = (BaseActivity) getActivity();

        if (activity != null) {
            // Toolbar
            activity.setToolbarNav(R.drawable.arrow_left, (View v) -> activity.getNavController().navigateUp());
            activity.setToolbarMenu(R.menu.empty_options, null);
            activity.setToolbarTitle(R.string.menu_cart);

            // RecyclerView boilerplate
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

            // Subscribe recyclerview adapter to viewModel LiveData
            viewModel.getProducts().observe(this, products -> {
                viewModel.calculateSubTotal(products);

                if (recyclerView.getAdapter() == null) {
                    recyclerView.setAdapter(new ShoppingCartItemAdapter(products, R.layout.item_shopping_cart_product));
                }
                else {
                    ((ShoppingCartItemAdapter) recyclerView.getAdapter()).updateElements(products);
                }
            });

            // Recyclerview Swipe to delete
            SwipeDeleteCallback sdcb = new SwipeDeleteCallback(getActivity(), ItemTouchHelper.LEFT) {
                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    RecyclerView.Adapter adapter = recyclerView.getAdapter();
                    if (adapter instanceof CustomBaseAdapter) {
                        CustomBaseAdapter customAdapter = (CustomBaseAdapter) adapter;
                        viewModel.removeProduct((int) customAdapter.getItemId(viewHolder.getAdapterPosition()));
                    }
                }
            };

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(sdcb);
            itemTouchHelper.attachToRecyclerView(recyclerView);

            // Checkout
            viewModel.getActiveUser().observe(this, user -> {
                if (user != null) {
                    buttonCheckout.setOnClickListener((View v) -> {
                        buttonCheckout.setEnabled(false);
                        viewModel.purchaseProducts(user.getId());
                    });
                }
            });
        }

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
