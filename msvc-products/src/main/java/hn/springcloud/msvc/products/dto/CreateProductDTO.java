package hn.springcloud.msvc.products.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDTO {
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres")
    private String name;

    @NotNull(message = "El precio del producto no puede estar vacío")
    @DecimalMin(value = "0.0", message = "El precio del producto debe ser mayor que 0", inclusive = true)
    @Digits(integer = 10, fraction = 2, message = "El precio del producto debe ser un número válido con hasta 2 decimales")
    private Double price;

    private String description;

    @NotNull(message = "La categoria no puede estar vacía")
    @Min(value = 1, message = "El id de la categoria debe ser un valor mayor a 0")
    private Long idCategory;

    @NotBlank(message = "El parametro createdBy no puede estar vacío")
    @Size(min = 3, max = 50, message = "El parametro createdBy debe tener entre 3 y 50 caracteres")
    private String createdBy;
}
