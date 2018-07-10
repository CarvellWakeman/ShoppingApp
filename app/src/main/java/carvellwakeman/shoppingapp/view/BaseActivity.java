package carvellwakeman.shoppingapp.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import androidx.navigation.Navigation;
import carvellwakeman.shoppingapp.R;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

}
