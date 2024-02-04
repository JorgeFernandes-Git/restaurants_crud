package com.jorge.desafioanotaai.controllers;

import com.jorge.desafioanotaai.domain.restaurant.Restaurant;
import com.jorge.desafioanotaai.domain.restaurant.RestaurantDTO;
import com.jorge.desafioanotaai.services.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Restaurant> insert(@RequestBody RestaurantDTO restaurantDTO) {
        Restaurant newRestaurant = this.service.insert(restaurantDTO);
        return ResponseEntity.ok().body(newRestaurant);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAll() {
        List<Restaurant> restaurants = this.service.getAll();
        return ResponseEntity.ok().body(restaurants);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable("id") String id, @RequestBody RestaurantDTO restaurantDTO) {
        Restaurant updateRestaurant = this.service.update(id, restaurantDTO);
        return ResponseEntity.ok().body(updateRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurant> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
