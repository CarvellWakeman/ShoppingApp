package carvellwakeman.shoppingapp.listproducts;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.data.IProductDao;

import javax.inject.Inject;


public class ListFragment extends Fragment {

    // This is just to stop dagger2 compiler errors
    @Inject IProductDao productDao;

    // Required empty public constructor
    public ListFragment() {}

    public static ListFragment newInstance() {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
