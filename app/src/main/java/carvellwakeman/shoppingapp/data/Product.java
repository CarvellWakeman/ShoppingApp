package carvellwakeman.shoppingapp.data;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.Bindable;
import android.support.annotation.NonNull;


/*
 * This entity is a java class used by Room to build the product table.
 * It is also used in viewModel logic to serve as a data contract to be displayed on the front end.
 * If this application used data-binding, this class could act as a contract for that as well.
 */
@Entity
public class Product implements IBaseEntity{

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private Integer quantity;
    private Double cost;
    private Integer rating;
    private Float weight;
    private Float length;
    private Float width;
    private Float height;


    public Product(String name, String description, String imageUrl, Integer quantity, Double cost, Integer rating, Float weight, Float length, Float width, Float height) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;

        this.quantity = quantity;
        this.cost = cost;
        this.rating = rating;

        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }


    @Override
    public boolean equals(Object product) {
        return (product instanceof Product) && this.id == ((Product)product).id;
    }
}
