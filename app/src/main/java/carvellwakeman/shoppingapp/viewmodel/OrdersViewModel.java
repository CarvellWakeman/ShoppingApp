package carvellwakeman.shoppingapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import carvellwakeman.shoppingapp.data.order.IProductOrderRepository;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.shoppingcartitem.IShoppingCartItemRepository;
import carvellwakeman.shoppingapp.data.user.IUserRepository;
import carvellwakeman.shoppingapp.data.user.User;

import javax.inject.Inject;
import java.util.List;


public class OrdersViewModel extends ViewModel {

    private final IProductOrderRepository orderRepository;

    private LiveData<List<Product>> products;

    @Inject
    public OrdersViewModel(IProductOrderRepository orderRepository) {
        this.orderRepository = orderRepository;

        products = orderRepository.getProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

}
