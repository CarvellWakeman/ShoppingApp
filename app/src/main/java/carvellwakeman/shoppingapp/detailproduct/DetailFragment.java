package carvellwakeman.shoppingapp.detailproduct;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.BR;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.DetailProductViewModel;


public class DetailFragment extends BaseFragment<DetailProductViewModel> {

    @BindView(R.id.toolbar) Toolbar toolbar;

    // Required empty public constructor
    public DetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger 2 injection
        ((ShoppingApplication)getActivity().getApplication())
                .getApplicationComponent()
                .inject(DetailFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_details, container, false);
        ButterKnife.bind(this, binding.getRoot());

        // Toolbar
        toolbar.setNavigationOnClickListener( (View view) -> Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigateUp() );

        // Get bundle arguments
        int productId = getArguments().getInt("productId");

        viewModel.getProduct(productId).observe(this, product -> {
            //toolbar.setTitle(product.getName());
            binding.setVariable(BR.item, product);
            binding.executePendingBindings();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
