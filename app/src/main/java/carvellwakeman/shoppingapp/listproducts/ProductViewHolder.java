package carvellwakeman.shoppingapp.listproducts;


import android.app.Application;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.view.BaseViewHolder;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;


public class ProductViewHolder extends BaseViewHolder<Product> implements View.OnClickListener {

    @BindView(R.id.imageView_image) ImageView imageView;
    private Product product;

    public ProductViewHolder(ViewDataBinding binding) {
        super(binding);
        ButterKnife.bind(this, binding.getRoot());

        binding.getRoot().setOnClickListener(this);
    }

    @Override
    public void bind(Product object) {
        super.bind(object);
        product = object;

        // Get image
        Picasso.get().load(object.getImageUrl()).into(imageView);
    }


    @Override
    public void onClick(View view) {
        if (product != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("productId", product.getId());
            Navigation.findNavController(view).navigate(R.id.action_listFragment_to_detailFragment, bundle);
        }
    }
}
