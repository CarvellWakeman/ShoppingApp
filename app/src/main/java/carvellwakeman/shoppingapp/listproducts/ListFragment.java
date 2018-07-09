package carvellwakeman.shoppingapp.listproducts;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
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
import carvellwakeman.shoppingapp.utils.DiffUtilCallback;
import carvellwakeman.shoppingapp.utils.SwipeDeleteCallback;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.view.CustomBaseAdapter;
import carvellwakeman.shoppingapp.viewmodel.ListProductsViewModel;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;


public class ListFragment extends BaseFragment<ListProductsViewModel> {

    //@BindView(R.id.layout_product) FrameLayout productLayout;
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

        // Subscribe recyclerview adapter to viewModel LiveData
        viewModel.getProducts().observe(this, products -> {
            if (recyclerView.getAdapter() == null) {
                recyclerView.setAdapter(new CustomBaseAdapter(products, R.layout.item_product));
            }
            else {
                ((CustomBaseAdapter<Product>)recyclerView.getAdapter()).updateElements(products);
            }
        });

        // Recyclerview Swipe to delete
        SwipeDeleteCallback sdcb = new SwipeDeleteCallback(getActivity(), ItemTouchHelper.LEFT) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.removeProduct(viewHolder.getAdapterPosition());
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(sdcb);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        // (TEMP) Add new product button
        button.setOnClickListener((View v) ->
            viewModel.addProduct(new Product(Calendar.getInstance().getTime().toString(), "C", 4, 1.0f, 1.0f, 1.0f, 1.0f))
        );


        // Inflate the layout for this fragment
        return view;
    }

}
