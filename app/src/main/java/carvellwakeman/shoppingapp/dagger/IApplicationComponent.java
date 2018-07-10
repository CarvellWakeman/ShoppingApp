package carvellwakeman.shoppingapp.dagger;


import carvellwakeman.shoppingapp.detailproduct.DetailFragment;
import carvellwakeman.shoppingapp.listproducts.ListFragment;
import carvellwakeman.shoppingapp.settings.SettingsFragment;
import dagger.Component;

import javax.inject.Singleton;


@Singleton
@Component(modules = {RoomModule.class, ViewModelModule.class})
public interface IApplicationComponent {

    void inject(ListFragment fragment);
    void inject(DetailFragment fragment);
    void inject(SettingsFragment fragment);

}
