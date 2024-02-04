package com.jorge.desafioanotaai.domain.product;

import com.jorge.desafioanotaai.domain.category.Category;

public record ProductDTO(String title, String description, String ownerId, Integer price, String categoryId) {
}
