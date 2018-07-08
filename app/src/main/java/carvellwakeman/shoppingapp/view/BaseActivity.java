package carvellwakeman.shoppingapp.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.listproducts.ListFragment;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        replaceFragment(R.id.activity_list_base, ListFragment.class.getName());
    }

    public void replaceFragment(int containerViewId, String fragClass) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fragClass);

        if (fragment == null) {
            try {
                fragment = Fragment.instantiate(this, fragClass);
            } catch (Exception ex) {
                throw new TypeNotPresentException(fragClass, null);
            }
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment, fragClass);
        transaction.addToBackStack(fragClass);
        transaction.commit();
    }

}
