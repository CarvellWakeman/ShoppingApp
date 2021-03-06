package carvellwakeman.shoppingapp.dagger;


import carvellwakeman.shoppingapp.ShoppingApplication;
import carvellwakeman.shoppingapp.detailproduct.DetailFragment;
import carvellwakeman.shoppingapp.listproducts.ListFragment;
import carvellwakeman.shoppingapp.orders.OrdersFragment;
import carvellwakeman.shoppingapp.settings.SettingsFragment;
import carvellwakeman.shoppingapp.shoppingcart.ShoppingCartFragment;
import carvellwakeman.shoppingapp.view.BaseActivity;
import dagger.Component;

import javax.inject.Singleton;


@Singleton
@Component(modules = {RoomModule.class, ViewModelModule.class})
public interface IApplicationComponent {

    void inject(ShoppingApplication application);

    void inject(BaseActivity activity);
    void inject(ListFragment fragment);
    void inject(DetailFragment fragment);
    void inject(SettingsFragment fragment);
    void inject(ShoppingCartFragment fragment);
    void inject(OrdersFragment fragment);

}
