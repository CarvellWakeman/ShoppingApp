package carvellwakeman.shoppingapp.listproducts;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.view.CustomBaseAdapter;

import java.util.List;


public class ProductAdapter extends CustomBaseAdapter<Product, ProductViewHolder> {

    ProductAdapter(List<Product> elements, int layout) {
        super(elements, layout);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, layout, parent, false);

        return new ProductViewHolder(binding);
    }

}
