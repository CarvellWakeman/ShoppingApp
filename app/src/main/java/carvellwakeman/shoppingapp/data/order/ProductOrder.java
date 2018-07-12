package carvellwakeman.shoppingapp.data.order;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import carvellwakeman.shoppingapp.data.IBaseEntity;
import carvellwakeman.shoppingapp.data.product.Product;
import carvellwakeman.shoppingapp.data.user.User;

import java.time.OffsetDateTime;


/*
 * This entity is a java class used by Room to build the shoppingCart table.
 * It is also used in viewModel logic to serve as a data contract to be displayed on the front end.
 * This class could act as a contract for data-binding as well.
 */
@Entity(foreignKeys = {
            @ForeignKey(entity = Product.class,
            parentColumns = "id",
            childColumns = "productId",
            onDelete = ForeignKey.SET_NULL),
            @ForeignKey(entity = User.class,
            parentColumns = "id",
            childColumns = "userId",
            onDelete = ForeignKey.CASCADE)
        }, indices = {@Index("productId"), @Index("userId")})
public class ProductOrder implements IBaseEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int productId;
    private int userId;
    private long date;

    public ProductOrder(int userId, int productId, long date) {
        this.userId = userId;
        this.productId = productId;
        this.date = date;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ProductOrder && this.id == ((ProductOrder)other).id);
    }

}
