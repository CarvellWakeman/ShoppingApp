package carvellwakeman.shoppingapp.data.shoppingcartitem;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import carvellwakeman.shoppingapp.data.IBaseEntity;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.User;


/*
 * This entity is a java class used by Room to build the shoppingCart table.
 * It is also used in viewModel logic to serve as a data contract to be displayed on the front end.
 * This class could act as a contract for data-binding as well.
 */
@Entity(primaryKeys = {"id", "productId", "userId"},
        foreignKeys = {
            @ForeignKey(entity = Product.class,
            parentColumns = "id",
            childColumns = "productId",
            onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "userId",
            onDelete = ForeignKey.CASCADE)
        }, indices = {@Index("productId"), @Index("userId")})
public class ShoppingCartItem implements IBaseEntity {

    private int id;
    private int productId;
    private int userId;

    public ShoppingCartItem(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ShoppingCartItem && this.id == ((ShoppingCartItem)other).id);
    }

}
