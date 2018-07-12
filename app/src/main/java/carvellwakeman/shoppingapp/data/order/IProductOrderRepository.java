package carvellwakeman.shoppingapp.data.order;

import android.arch.lifecycle.LiveData;
import carvellwakeman.shoppingapp.data.product.Product;

import java.util.List;

public interface IProductOrderRepository {

    LiveData<List<Product>> getProducts();

}
