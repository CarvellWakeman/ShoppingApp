package carvellwakeman.shoppingapp.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import carvellwakeman.shoppingapp.R;


public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);

        // Navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

    // Toolbar
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                navigationView.setCheckedItem(item.getItemId());
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.listFragment);
                break;
            case R.id.nav_cart:
                Toast.makeText(this, "Shopping cart not implemented", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_deals:
                Toast.makeText(this, "Deals not implemented", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_orders:
                Toast.makeText(this, "Orders not implemented", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                navigationView.setCheckedItem(item.getItemId());
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.settingsFragment);
                break;
            case R.id.nav_support:
                Toast.makeText(this, "Support not implemented", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }


        closeNavDrawer(GravityCompat.START);

        return false;
    }
}
