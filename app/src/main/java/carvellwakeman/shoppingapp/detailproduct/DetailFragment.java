package carvellwakeman.shoppingapp.detailproduct;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.BR;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.view.BaseActivity;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.DetailProductViewModel;
import com.bumptech.glide.Glide;


public class DetailFragment extends BaseFragment<DetailProductViewModel> {

    @BindView(R.id.imageView_image) ImageView productImage;
    @BindView(R.id.button_addToCart) Button buttonAddToCart;


    // Required empty public constructor
    public DetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger 2 injection
        ((ShoppingApplication) getActivity().getApplication()).getApplicationComponent().inject(DetailFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false);
        ButterKnife.bind(this, binding.getRoot());

        // Toolbar
        BaseActivity activity = (BaseActivity) getActivity();

        if (activity != null) {
            activity.setToolbarNav(R.drawable.arrow_left, (View v) -> Navigation.findNavController(activity, R.id.nav_host_fragment).navigateUp());
            activity.setToolbarMenu(R.menu.fragment_details_options, (MenuItem item) -> {
                switch (item.getItemId()) {
                    case R.id.action_cart:
                        Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(R.id.shoppingCartFragment);
                        break;
                    default:
                        break;
                }
                return true;
            });
        }

        // Get bundle arguments
        Bundle args = getArguments();
        if (args != null) {
            final int productId = args.getInt("productId");
            final int userId = ((ShoppingApplication)getActivity().getApplication()).getUser();

            viewModel.getProduct(productId).observe(this, product -> {
                if (activity != null && product != null) {
                    activity.setToolbarTitle(product.getName());
                    binding.setVariable(BR.item, product);
                    binding.executePendingBindings();

                    // Get image
                    Glide.with(this).load(product.getImageUrl()).into(productImage);
                }
            });

            viewModel.shoppingCartHasProduct(userId, productId).observe(this, hasProduct -> {
                if (hasProduct != null && hasProduct) {
                    buttonAddToCart.setEnabled(false);
                    buttonAddToCart.setText(R.string.action_inCart);
                }
            });

            // Add to cart button
            buttonAddToCart.setOnClickListener( (View v) -> {
                viewModel.addShoppingCartItem(userId, productId);
                buttonAddToCart.setEnabled(false);
                buttonAddToCart.setText(R.string.action_inCart);
            });
        }

        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_details_options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
