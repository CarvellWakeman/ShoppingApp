package carvellwakeman.shoppingapp.listproducts;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.view.BaseFragment;
import carvellwakeman.shoppingapp.viewmodel.ListViewModel;


public class ListFragment extends BaseFragment<ListViewModel> {

    @BindView(R.id.textView) TextView textView;
    @BindView(R.id.button) Button button;


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

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);


        textView.setText(String.valueOf(viewModel.getCount()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.incCount();
                textView.setText(String.valueOf(viewModel.getCount()));
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}
