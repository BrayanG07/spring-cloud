package hn.springcloud.msvc.products.repositories;

import org.springframework.data.repository.CrudRepository;

import hn.springcloud.msvc.libs.commons.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
