package com.jorge.desafioanotaai.domain.restaurant;

import com.jorge.desafioanotaai.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "restaurants")
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private List<Product> products;

    public Restaurant(RestaurantDTO restaurantDTO) {
        this.title = restaurantDTO.title();
        this.description = restaurantDTO.description();
        this.ownerId = restaurantDTO.ownerId();
    }
}
