package carvellwakeman.shoppingapp.view;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.data.user.User;
import carvellwakeman.shoppingapp.selectuser.SelectUserDialogFragment;
import com.bumptech.glide.Glide;

import javax.inject.Inject;
import java.util.HashMap;


public class BaseActivity extends AppCompatActivity implements NavHost, SelectUserDialogFragment.SelectUserInterfaceListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    View navigationHeader;
    ConstraintLayout buttonSelectUser;
    de.hdodenhof.circleimageview.CircleImageView userImage;
    TextView userName;
    TextView userEmail;

    @Inject
    IUserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);

        // Dagger 2 injection
        ((ShoppingApplication) getApplication()).getApplicationComponent().inject(BaseActivity.this);

        // Navigation drawer
        NavigationUI.setupWithNavController(navigationView, this.getNavController());
        NavigationUI.navigateUp(drawerLayout, this.getNavController());

        navigationHeader = navigationView.getHeaderView(0);
        buttonSelectUser = navigationHeader.findViewById(R.id.selectUser);
        userImage = navigationHeader.findViewById(R.id.userImage);
        userName = navigationHeader.findViewById(R.id.userName);
        userEmail = navigationHeader.findViewById(R.id.userEmail);

        // Switch user
        userRepository.getUsers().observe(this, users ->
            buttonSelectUser.setOnClickListener((View v) -> {
                // Get emails from list of users
                // Java 8 streams would be great here, but not supported on API < 24
                DialogFragment dialog = new SelectUserDialogFragment();

                if (users != null && users.size() > 0) {
                    HashMap<String, Integer> userMap = new HashMap<>();
                    for (User u : users) { userMap.put(u.getEmail(), u.getId()); }
                    ((SelectUserDialogFragment) dialog).users = userMap;
                }

                dialog.show(getFragmentManager(), "selectUser");
            })
        );

        // Navigation drawer active user
        userRepository.getActiveUser().observe(this, user -> {
            if (user != null) {
                Glide.with(this).load(user.getImageUrl()).into(userImage);
                userName.setText(user.getName());
                userEmail.setText(user.getEmail());
            }
        });

        // Nav drawer was being opened on startup by NavigationUI
        closeNavDrawer(GravityCompat.START);
    }

    // Toolbar
    public Menu getToolbarMenu() {
        return toolbar.getMenu();
    }

    public void setToolbarNav(int icon, View.OnClickListener action) {
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(action);
    }

    public void setToolbarMenu(int resId, Toolbar.OnMenuItemClickListener listener) {
        toolbar.getMenu().clear();

        toolbar.inflateMenu(resId);
        toolbar.setOnMenuItemClickListener(listener);
    }

    public void setToolbarTitle(int stringRes) {
        toolbar.setTitle(stringRes);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setToolbarSubtitle(String subtitle) {
        toolbar.setSubtitle(subtitle);
    }

    // Nav drawer
    public void openNavDrawer(int gravityCompat) {
        drawerLayout.openDrawer(gravityCompat);
    }

    public void closeNavDrawer(int gravityCompat) {
        drawerLayout.closeDrawer(gravityCompat);
    }

    // User selection
    @Override
    public void onUserSelected(DialogFragment dialog, int userId) {
        userRepository.setActiveUser(userId);
    }

    // Navigation
    @NonNull
    @Override
    public NavController getNavController() {
        return Navigation.findNavController(this, R.id.nav_host_fragment);
    }
}
