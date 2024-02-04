package com.jorge.desafioanotaai.services;

import com.jorge.desafioanotaai.domain.product.Product;
import com.jorge.desafioanotaai.domain.product.exceptions.ProductNotFoundException;
import com.jorge.desafioanotaai.domain.restaurant.Restaurant;
import com.jorge.desafioanotaai.domain.restaurant.RestaurantDTO;
import com.jorge.desafioanotaai.domain.restaurant.exceptions.RestaurantNotFoundException;
import com.jorge.desafioanotaai.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final ProductService productService;
    private final RestaurantRepository repository;

    public RestaurantService(ProductService productService, RestaurantRepository repository) {
        this.productService = productService;
        this.repository = repository;
    }

    public Restaurant insert(RestaurantDTO restaurantDTO) {
        List<Product> products = productService.getProductsByIds(restaurantDTO.productsId());
        Restaurant restaurant = new Restaurant(restaurantDTO);
        restaurant.setProducts(products);
        return repository.save(restaurant);
    }

    public Restaurant update(String id, RestaurantDTO restaurantDTO) {
        Restaurant restaurant = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if (restaurantDTO.productsId() != null) {
            for (String productId : restaurantDTO.productsId()) {
                Product product = productService.getById(productId).orElseThrow(ProductNotFoundException::new);

                // Check if the product is not already in the restaurant's products
                if (restaurant.getProducts().stream().noneMatch(existingProduct -> existingProduct.getId().equals(productId))) {
                    restaurant.getProducts().add(product);
                }
            }
        }

        if (!restaurantDTO.title().isEmpty()) restaurant.setTitle(restaurantDTO.title());
        if (!restaurantDTO.description().isEmpty()) restaurant.setDescription(restaurantDTO.description());
        if (!restaurantDTO.ownerId().isEmpty()) restaurant.setOwnerId(restaurantDTO.ownerId());

        return this.repository.save(restaurant);
    }

    public void delete(String id){
        Restaurant restaurant = this.repository.findById(id).orElseThrow(RestaurantNotFoundException::new);
        this.repository.delete(restaurant);
    }

    public List<Restaurant> getAll(){
        return this.repository.findAll();
    }
}
