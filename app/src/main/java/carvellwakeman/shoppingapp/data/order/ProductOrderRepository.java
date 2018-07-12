package carvellwakeman.shoppingapp.data.order;


import android.arch.lifecycle.LiveData;
import carvellwakeman.shoppingapp.data.product.Product;

import javax.inject.Inject;
import java.util.List;


/*
 * The repository exists as an abstraction layer between a DAO (data access object) and application logic (viewModel)
 * A repository can gather information from a local DB, a remote API, device preferences, or any other source.
 * This repository is implemented from an interface to increase testability.
 */
public class ProductOrderRepository implements IProductOrderRepository {

    // Local Room Database
    private final IProductOrderDao orderDao;

    @Inject
    public ProductOrderRepository(IProductOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public LiveData<List<Product>> getProducts() {
        return orderDao.getOrders();
    }

}
