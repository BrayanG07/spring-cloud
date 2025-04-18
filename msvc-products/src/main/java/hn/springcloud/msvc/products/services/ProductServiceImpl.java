package hn.springcloud.msvc.products.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import hn.springcloud.msvc.libs.commons.entities.Category;
import hn.springcloud.msvc.libs.commons.entities.Product;
import hn.springcloud.msvc.products.dto.CreateProductDTO;
import hn.springcloud.msvc.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final Environment environment;
    

    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
    }

    @Override
    public List<Product> findAll() {
        return ((List<Product>) productRepository.findAll()).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        });
    }

    @Override
    public Product save(CreateProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);
        product.setCreatedBy(productDTO.getCreatedBy());

        Product productCreated = productRepository.save(product);
        productCreated.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return productCreated;
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> findByPriceBetween(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).toList();
    }

    @Override
    public List<Product> findByPriceLessThan(Double price) {
        return productRepository.findByPriceLessThan(price).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).toList();
    }

    @Override
    public List<Product> findByPriceLessThanEqual(Double price) {
        return productRepository.findByPriceLessThanEqual(price).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).toList();
    }

    @Override
    public List<Product> findByPriceGreaterThan(Double price) {
        return productRepository.findByPriceGreaterThan(price).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).toList();
    }

    @Override
    public List<Product> findByPriceGreaterThanEqual(Double price) {
        return productRepository.findByPriceGreaterThanEqual(price).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).toList();
    }

    @Override
    public List<Product> findByNameLike(String name) {
        return productRepository.findByNameLike(name).stream().map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).toList();
    }

    @Override
    public List<Map<String, Object>> findByCategory(Long idCategory) {
        List<Map<String, Object>> products = productRepository.findByCategory(idCategory).stream().map(product -> {
            Map<String, Object> productMap = Map.of(
                "id", product.getId(),
                "name", product.getName(),
                "description", product.getDescription(),
                "price", product.getPrice(),
                "category", product.getCategory(),
                "createdBy", product.getCreatedBy(),
                "port", Integer.parseInt(environment.getProperty("local.server.port"))
            );
            return productMap;
        }).toList();
        return products;
    }

}
