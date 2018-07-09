package carvellwakeman.shoppingapp.listproducts;


import android.databinding.ViewDataBinding;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.data.Product;
import carvellwakeman.shoppingapp.view.BaseViewHolder;
import com.bumptech.glide.Glide;


public class ProductViewHolder extends BaseViewHolder<Product> {

    @BindView(R.id.imageView_image) ImageView imageView;

    public ProductViewHolder(ViewDataBinding binding) {
        super(binding);
        ButterKnife.bind(this, binding.getRoot());
    }

    @Override
    public void bind(Product object) {
        super.bind(object);

        // Get image
        Glide.with(binding.getRoot()).load(object.getImageUrl()).into(imageView);
    }
}
