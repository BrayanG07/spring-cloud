package hn.springcloud.msvc.products.config;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import hn.springcloud.msvc.libs.commons.utils.CustomResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Obtenemos el primer error de validación
        List<ObjectError> fieErrors = ex.getBindingResult().getAllErrors();

        if (!fieErrors.isEmpty()) {
            // Tomamos el primer error de validación
            String errorMessage = fieErrors.get(0).getDefaultMessage();

            // Creamos la respuesta con la estructura deseada
            CustomResponse response = new CustomResponse(
                HttpStatus.BAD_REQUEST.value(),
                errorMessage,
                errorMessage,
                null
            );

            // Retornamos la respuesta con el código de estado 400 (Bad Request)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Si no hay errores de validación, retornamos un error genérico
        CustomResponse response = new CustomResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Error de validación",
            fieErrors.toString(),
            null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomResponse> handleResponseStatusException(ResponseStatusException ex) {
        // Creamos la respuesta con la estructura deseada
        CustomResponse response = new CustomResponse(
            ex.getStatusCode().value(),
            ex.getReason(),
            ex.getMessage(),
            null
        );

        // Retornamos la respuesta con el código de estado correspondiente
        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomResponse> handleGeneralException(Exception ex) {
        // Creamos la respuesta con la estructura deseada
        CustomResponse response = new CustomResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Algo salió mal, intenta nuevamente y si el problema persiste contacta al administrador",
            "Algo salió mal, intenta nuevamente y si el problema persiste contacta al administrador",
            null
        );

        System.out.println("Error: " + ex.getMessage());

        // Retornamos la respuesta con el código de estado 500 (Internal Server Error)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); 
    }

}
