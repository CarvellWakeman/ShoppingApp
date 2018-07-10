package carvellwakeman.shoppingapp.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import carvellwakeman.shoppingapp.R;
import carvellwakeman.shoppingapp.listproducts.ListFragment;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //replaceFragment(R.id.activity_list_base, ListFragment.class.getName(), "ListProducts", false);
    }

//    public void replaceFragment(int containerViewId, String fragClass, String name, boolean addToBackStack) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentByTag(fragClass + name);
//
//        if (fragment == null) {
//            try {
//                fragment = Fragment.instantiate(this, fragClass);
//            } catch (Exception ex) {
//                throw new TypeNotPresentException(fragClass, null);
//            }
//        }
//
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(containerViewId, fragment, fragClass + name);
//        if (addToBackStack) { transaction.addToBackStack(fragClass + name); }
//        transaction.commit();
//    }

}
