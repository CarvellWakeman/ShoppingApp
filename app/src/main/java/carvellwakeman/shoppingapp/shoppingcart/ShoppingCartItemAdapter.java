package carvellwakeman.shoppingapp.shoppingcart;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.listproducts.ProductViewHolder;
import carvellwakeman.shoppingapp.view.CustomBaseAdapter;

import java.util.List;


public class ShoppingCartItemAdapter extends CustomBaseAdapter<Product, ShoppingCartItemViewHolder> {

    ShoppingCartItemAdapter(List<Product> elements, int layout) {
        super(elements, layout);
    }

    @NonNull
    @Override
    public ShoppingCartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, layout, parent, false);

        return new ShoppingCartItemViewHolder(binding);
    }

}
