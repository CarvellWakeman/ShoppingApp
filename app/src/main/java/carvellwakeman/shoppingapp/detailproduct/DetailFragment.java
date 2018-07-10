package carvellwakeman.shoppingapp.detailproduct;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.*;
import android.widget.Toast;
import androidx.navigation.Navigation;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.BR;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.view.BaseActivity;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.DetailProductViewModel;


public class DetailFragment extends BaseFragment<DetailProductViewModel> {

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
                        Toast.makeText(getContext(), "Shopping cart not implemented", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            });
        }

        // Get bundle arguments
        int productId = getArguments().getInt("productId");

        viewModel.getProduct(productId).observe(this, product -> {
            activity.setToolbarTitle(product.getName());
            binding.setVariable(BR.item, product);
            binding.executePendingBindings();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_details_options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
