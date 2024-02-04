package com.jorge.desafioanotaai.domain.restaurant;

import java.util.List;

public record RestaurantDTO(String title, String description, String ownerId, List<String> productsId) {
}
