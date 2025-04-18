package hn.springcloud.msvc.products.controllers;

import org.springframework.web.bind.annotation.RestController;

import hn.springcloud.msvc.libs.commons.entities.Category;
import hn.springcloud.msvc.libs.commons.utils.CustomResponse;
import hn.springcloud.msvc.products.dto.CreateProductDTO;
import hn.springcloud.msvc.products.services.CategoryService;
import hn.springcloud.msvc.products.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse> getAllProducts() {
        return ResponseEntity.ok(new CustomResponse(
            HttpStatus.OK.value(), 
            "Productos obtenidos correctamente", 
            "Productos obtenidos correctamente",
            productService.findAll()));
    }

    @GetMapping("/detail/{idProduct}")
    public ResponseEntity<CustomResponse> getProductById(@RequestParam("idProduct") Long idProduct) {
        CustomResponse response;

        if (!this.productService.existsById(idProduct)) {
            response = new CustomResponse(
                HttpStatus.NOT_FOUND.value(), 
                "Producto no encontrado", 
                "Producto no encontrado",
                null);
            
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Si el producto existe, se obtiene y se devuelve la respuesta
        return ResponseEntity.ok(new CustomResponse(
            HttpStatus.OK.value(), 
            "Producto obtenido correctamente", 
            "Producto obtenido correctamente",
            productService.findById(idProduct).get()));
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse> createProduct(@RequestBody @Valid CreateProductDTO productDTO) {
        CustomResponse response;

        Optional<Category> category = categoryService.findById(productDTO.getIdCategory());
        if (!category.isPresent()) {
            response = new CustomResponse(
                HttpStatus.NOT_FOUND.value(), 
                "Categoría no encontrada", 
                "Categoría no encontrada",
                null);
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(
            HttpStatus.CREATED.value(), 
            "Producto creado correctamente", 
            "Producto creado correctamente",
            productService.save(productDTO, category.get())));
    }
    
    @GetMapping("/price/between/{min}/{max}")
    public ResponseEntity<CustomResponse> getMethodName(@RequestParam ("min") Double min, @RequestParam("max") Double max) {
        return ResponseEntity.ok(new CustomResponse(
            HttpStatus.OK.value(), 
            "Busqueda procesada correctamente", 
            "Busqueda procesada correctamente",
            productService.findByPriceBetween(min, max)));
    }
    
    @GetMapping("/price/menor/{price}")
    public ResponseEntity<CustomResponse> getPriceByLessThan(@RequestParam("price") Double price) {
        return ResponseEntity.ok(new CustomResponse(
            HttpStatus.OK.value(), 
            "Busqueda procesada correctamente", 
            "Busqueda procesada correctamente",
            productService.findByPriceLessThan(price)));
        
    }
    
}
