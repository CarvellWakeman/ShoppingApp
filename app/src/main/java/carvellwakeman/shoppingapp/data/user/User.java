package carvellwakeman.shoppingapp.data.user;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import carvellwakeman.shoppingapp.data.IBaseEntity;


/*
 * This entity is a java class used by Room to build the user table.
 * It is also used in viewModel logic to serve as a data contract to be displayed on the front end.
 * If this application used data-binding, this class could act as a contract for that as well.
 */
@Entity
public class User implements IBaseEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String imageUrl;


    public User(String name, String email, String imageUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }


    @NonNull public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof User) && this.id == ((User)other).id;
    }
}
