package carvellwakeman.shoppingapp.listproducts;


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
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.utils.SwipeDeleteCallback;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.view.CustomBaseAdapter;
import carvellwakeman.shoppingapp.viewmodel.ListProductsViewModel;

import java.util.Calendar;


public class ListFragment extends BaseFragment<ListProductsViewModel> {

    @BindView(R.id.rec_products) RecyclerView recyclerView;
    @BindView(R.id.button) Button button;


    // Required empty public constructor
    public ListFragment() {}

    public ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((ShoppingApplication)getActivity().getApplication())
                .getApplicationComponent()
                .inject(ListFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

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

        // Recyclerview Swipe to delete
        SwipeDeleteCallback sdcb = new SwipeDeleteCallback(getActivity(), ItemTouchHelper.LEFT) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter instanceof CustomBaseAdapter) {
                    CustomBaseAdapter customAdapter = (CustomBaseAdapter)adapter;
                    viewModel.removeProduct((int)customAdapter.getItemId(viewHolder.getAdapterPosition()));
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(sdcb);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        // (TEMP) Add new product button
        button.setOnClickListener((View v) ->
            viewModel.addProduct(new Product(
                    "EKEDALEN Chair",
                    "The upholstered seat and curved backrest make you want to stay seated at the table for a while. Choose between different covers and machine wash when needed – and why not have several at home for variety?",
                    "https://www.ikea.com/us/en/images/products/ekedalen-chair-brown__0516603_PE640439_S4.JPG",
                    85,
                    49.0d,
                    6.7f,
                    41.0f, 43.0f, 46.0f)
            )
        );


        // Inflate the layout for this fragment
        return view;
    }

}
