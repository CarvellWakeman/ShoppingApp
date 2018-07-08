package carvellwakeman.shoppingapp.data;


import javax.inject.Inject;


public class ProductRepository {

    private final IProductDao productDao;

    @Inject
    public ProductRepository(IProductDao productDao) {
        this.productDao = productDao;
    }


}
