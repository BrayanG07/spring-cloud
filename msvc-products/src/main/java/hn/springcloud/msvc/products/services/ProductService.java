package hn.springcloud.msvc.products.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import hn.springcloud.msvc.libs.commons.entities.Category;
import hn.springcloud.msvc.libs.commons.entities.Product;
import hn.springcloud.msvc.products.dto.CreateProductDTO;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(CreateProductDTO productDTO, Category category);
    boolean existsById(Long id);
    
    List<Product> findByPriceBetween(Double min, Double max);
    List<Product> findByPriceLessThan(Double price);
    List<Product> findByPriceLessThanEqual(Double price);
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findByPriceGreaterThanEqual(Double price);
    List<Product> findByNameLike(String name);
    List<Map<String, Object>> findByCategory(Long idCategory);
}
