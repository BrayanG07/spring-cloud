package hn.springcloud.msvc.products.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import hn.springcloud.msvc.libs.commons.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
    // where x.price between ?1 and ?2
    List<Product> findByPriceBetween(Double min, Double max);

    // where x.price < ?1
    List<Product> findByPriceLessThan(Double price);

    // where x.price <= ?1
    List<Product> findByPriceLessThanEqual(Double price);

    // where x.price > ?1
    List<Product> findByPriceGreaterThan(Double price);

    // where x.price >= ?1
    List<Product> findByPriceGreaterThanEqual(Double price);

    // where x.name like %?1%
    List<Product> findByNameLike(String name);

    @Query(
        value = "select p.id, p.name, p.price, p.description from tbl_products where p.id_category = ?1",
        nativeQuery = true
    )
    List<Product> findByCategory(Long idCategory);
}
