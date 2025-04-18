package hn.springcloud.msvc.products.services;

import java.util.Optional;

import hn.springcloud.msvc.libs.commons.entities.Category;

public interface CategoryService {
    Optional<Category> findById(Long id);
}
