package com.pragma.powerup.infraestructure.exceptionhandler;

import com.pragma.powerup.infraestructure.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "message";

    @ExceptionHandler(RestaurantAlreadyExist.class)
    public ResponseEntity<Map<String, String>> handleRestaurantAlreadyExistException(RestaurantAlreadyExist restaurantAlreadyExist) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, "Ya se encuentra registrado un restaurante con ese nombre"));
    }
    @ExceptionHandler(OwnerNotFound.class)
    public ResponseEntity<Map<String, String>> handleOwnerNotFoundException(OwnerNotFound ownerNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, "Dueño no encontrado."));
    }
    @ExceptionHandler(OwnerInvalid.class)
    public ResponseEntity<Map<String, String>> handleOwnerInvalidException(OwnerInvalid ownerInvalid) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "Ingrese un dueño valido."));
    }
    @ExceptionHandler(DishAlreadyExist.class)
    public ResponseEntity<Map<String, String>> handleDishAlreadyExistException(DishAlreadyExist dishAlreadyExist) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, "El plato ya se encuentra registrado"));
    }
    @ExceptionHandler(NotDishFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotDishFoundException(NotDishFoundException notDishFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, "El plato no existe"));
    }
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantNotFoundFoundException(RestaurantNotFoundException restaurantNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, "El restaurante no existe"));
    }
    @ExceptionHandler(InvalidDishRestaurantException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDishRestaurantException(InvalidDishRestaurantException restaurantNotFound) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, "El plato seleccionado no pertenece al restaurante"));
    }
}
