package carvellwakeman.shoppingapp.data.user;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import carvellwakeman.shoppingapp.data.IBaseEntity;
import carvellwakeman.shoppingapp.data.product.Product;


/*
 * This entity is a java class used by Room to build the activeUser table.
 * It is also used in viewModel logic to serve as a data contract to be displayed on the front end.
 * If this application used data-binding, this class could act as a contract for that as well.
 */
@Entity(foreignKeys = {
                @ForeignKey(entity = User.class,
                        parentColumns = "id",
                        childColumns = "userId",
                        onDelete = ForeignKey.CASCADE)
        }, indices = {@Index("userId")})
public class ActiveUser implements IBaseEntity {

    @PrimaryKey
    private int id;
    private int userId;

    ActiveUser(int userId) {
        this.id = 1;
        this.userId = userId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
