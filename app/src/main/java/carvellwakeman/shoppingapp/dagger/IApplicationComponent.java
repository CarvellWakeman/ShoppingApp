package carvellwakeman.shoppingapp.dagger;


import carvellwakeman.shoppingapp.listproducts.ListFragment;
import dagger.Component;

import javax.inject.Singleton;


@Singleton
@Component(modules = {RoomModule.class})
public interface IApplicationComponent {

    void inject(ListFragment listFragment);

}
