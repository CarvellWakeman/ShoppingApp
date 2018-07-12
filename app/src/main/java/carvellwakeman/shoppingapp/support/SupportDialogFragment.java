package carvellwakeman.shoppingapp.support;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.view.BaseActivity;


public class SupportDialogFragment extends Fragment {

    @BindView(R.id.linearLayout_Github) LinearLayout githubLink;
    @BindView(R.id.linearLayout_linkedin) LinearLayout linkedInLink;
    @BindView(R.id.linearLayout_mysite) LinearLayout mySiteLink;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Context context = getActivity();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.fragment_support, null);

        ButterKnife.bind(this, view);


        BaseActivity activity = (BaseActivity) getActivity();

        if (activity != null) {
            // Toolbar
            activity.setToolbarNav(R.drawable.arrow_left, (View v) -> activity.getNavController().navigateUp());
            activity.setToolbarMenu(R.menu.empty_options, null);
            activity.setToolbarTitle(R.string.menu_about);
        }

        // Link to github
        githubLink.setOnClickListener((View v) -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getString(R.string.link_github)));
            startActivity(i);
        });

        // Link to linkedin
        linkedInLink.setOnClickListener((View v) -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getString(R.string.link_linkedin)));
            startActivity(i);
        });

        // Link to my site
        mySiteLink.setOnClickListener((View v) -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getString(R.string.link_mysite)));
            startActivity(i);
        });

        return view;
    }

}
