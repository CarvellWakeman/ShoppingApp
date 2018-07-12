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

    LiveData<List<Product>> products;
    int productsCount;

    @Inject
    public OrdersViewModel(IProductOrderRepository orderRepository) {
        this.orderRepository = orderRepository;

        products = orderRepository.getProducts();
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public int getProductsCount() {
        return productsCount;
    }
    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

}
