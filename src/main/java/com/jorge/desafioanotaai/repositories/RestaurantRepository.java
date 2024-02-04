package com.jorge.desafioanotaai.repositories;

import com.jorge.desafioanotaai.domain.restaurant.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository  extends MongoRepository<Restaurant, String> {
}
