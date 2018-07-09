package carvellwakeman.shoppingapp.listproducts;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.databinding.ItemProductBinding;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.ListProductsViewModel;


public class ListFragment extends BaseFragment<ListProductsViewModel> {

    //@BindView(R.id.rec_products) RecyclerView recyclerView;
    @BindView(R.id.layout_product) FrameLayout productLayout;
    //@BindView(R.id.button) Button button;
    //@BindView(R.id.button2) Button button2;


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

        //View view = inflater.inflate(R.layout.fragment_list, container, false);

        ItemProductBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_product, container, false);
        binding.setProduct(new Product("A", "B", "C", 4, 1.0f, 1.0f, 1.0f, 1.0f));

        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        //ListFragmentBinding binding = setContentView(inflater, R.layout.fragment_list, container, false);
        //binding.setVariable(BR.product, new Product("A", "B", "C", 4, 1.0f, 1.0f, 1.0f, 1.0f));

        //viewModel.getProduct("a").observe(this, product -> {
        //    if (product != null) {
        //        textView.setText(String.valueOf(product.getQuantity()));
        //    }
        //});

//        button.setOnClickListener((View v) ->
//            viewModel.addProduct(new Product("a", "B", "C", 4, 1.0f, 1.0f, 1.0f, 1.0f))
//        );
//
//        button2.setOnClickListener((View v) ->
//                viewModel.removeProduct("a")
//        );


        // Inflate the layout for this fragment
        return view;
    }

}
