package com.pragma.powerup.application.validation;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.exception.InvalidRequestException;

import static com.pragma.powerup.application.util.Validate.notIsString;

public class DishValidation {
    private DishValidation(){
    }

    public static void dishRequestDtoValidation(DishRequestDto dishRequestDto){
        if(dishRequestDto==null){
            throw new InvalidRequestException("Datos no validados");
        }
        if(notIsString(dishRequestDto.getName())){
            throw new InvalidRequestException("El nombre es obligatorio");
        }
        if(dishRequestDto.getCategory()==null){
            throw new InvalidRequestException("La categoria es obligatoria");
        }
        if(notIsString(dishRequestDto.getDescription())){
            throw new InvalidRequestException("La descripcion es obligatoria");
        }
        if(dishRequestDto.getPrice()==null){
            throw new InvalidRequestException("El precio es obligatorio");
        }
        if(dishRequestDto.getRestaurant()==null){
            throw new InvalidRequestException("El restaurante es obligatorio");
        }
        if(notIsString(dishRequestDto.getUrlImage())){
            throw new InvalidRequestException("La URL de la imagen es obligatoria");
        }
    }

}
