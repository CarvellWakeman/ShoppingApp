package carvellwakeman.shoppingapp.listproducts;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.*;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ListFragment extends BaseFragment<ListProductsViewModel> {

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rec_products) RecyclerView recyclerView;
    @BindView(R.id.button) Button button;


    // Required empty public constructor
    public ListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Dagger 2 injection
        ((ShoppingApplication)getActivity().getApplication())
                .getApplicationComponent()
                .inject(ListFragment.this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.createViewModel();

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);

        // Toolbar
        toolbar.inflateMenu(R.menu.fragment_list_options);
        toolbar.setNavigationOnClickListener( (View v) -> drawerLayout.openDrawer(GravityCompat.START) );
        toolbar.setOnMenuItemClickListener( (MenuItem item) -> {
            switch (item.getItemId()) {
                case R.id.action_search:
                    Log.d("LFRG", "Search");
                    break;
                default:
                    break;
            }
            return true;
        });

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
        class SimpleProduct {
            String name;
            String desc;
            String url;
            public SimpleProduct(String name, String url, String desc) { this.name = name; this.desc = desc; this.url = url; }
        }
        Random random = new Random();
        List<SimpleProduct> products = new ArrayList<>();
        products.add(
            new SimpleProduct("EKEDALEN Chair",
                      "https://www.ikea.com/us/en/images/products/ekedalen-chair-brown__0516603_PE640439_S4.JPG",
                      "The upholstered seat and curved backrest make you want to stay seated at the table for a while. Choose between different covers and machine wash when needed – and why not have several at home for variety?")
        );
        products.add(
                new SimpleProduct("KULLABERG Chair",
                        "https://www.ikea.com/us/en/images/products/kullaberg-swivel-chair-black__0410020_PE577748_S4.JPG",
                        "A desk chair inspired by old-fashioned industrial-style chairs, complete with modern functions.")
        );
        products.add(
                new SimpleProduct("STEFAN Chair",
                        "https://www.ikea.com/us/en/images/products/stefan-chair-black__0122106_PE278491_S4.JPG",
                        "Solid wood is a durable natural material.")
        );
        products.add(
                new SimpleProduct("VIVO Dual LCD LED Monitor Desk Mount Stand",
                        "https://images-na.ssl-images-amazon.com/images/I/81O%2Bbh3YQRL._SL1500_.jpg",
                        "Heavy Duty Fully Adjustable fits 2/Two Screens up to 27\"")
        );
        products.add(
                new SimpleProduct("1994 Honda Accord",
                        "http://i.bnet.com/blogs/94_honda_accord.jpg",
                        "A very old and unreliable car. Comes with new brakepads on the front, and the left rear tail light is out.")
        );


        button.setOnClickListener((View v) -> {
                int idx = random.nextInt(products.size());
                SimpleProduct p = products.get(idx);
                viewModel.addProduct(new Product(p.name, p.desc, p.url,
                        random.nextInt(100),
                        random.nextDouble() * 300.0d,
                        random.nextFloat() * 10.0f,
                        random.nextFloat() * 50.0f,
                        random.nextFloat() * 50.0f,
                        random.nextFloat() * 50.0f));

                recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
            }
        );


        // Inflate the layout for this fragment
        return view;
    }

}
