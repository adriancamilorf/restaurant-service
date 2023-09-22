package com.pragma.powerup.application.validation;

import com.pragma.powerup.application.dto.request.RestaurantDto;
import com.pragma.powerup.application.exception.InvalidRequestException;
import static com.pragma.powerup.application.util.Validate.notIsString;
import static com.pragma.powerup.application.util.Validate.onlyNumber;
import static com.pragma.powerup.application.util.Validate.isPhoneValid;

public class RestaurantValidation {

    private RestaurantValidation(){
    }

    public static void restaurantDtoValidate(RestaurantDto restaurantDto){
        if(restaurantDto==null){
            throw new InvalidRequestException("Datos no validados");
        }
        if(notIsString(restaurantDto.getPhone()) || !isPhoneValid(restaurantDto.getPhone()) ){
            throw new InvalidRequestException("El numero de telefono el obligatorio");
        }
        if(notIsString(restaurantDto.getName()) ||  onlyNumber(restaurantDto.getName())){
            throw new InvalidRequestException("El nombre es obligatorio");
        }
        if(notIsString(restaurantDto.getAddress())){
            throw new InvalidRequestException("la direccion es obligatoria");
        }
        if(notIsString(restaurantDto.getNit()) || !onlyNumber(restaurantDto.getNit())){
            throw new InvalidRequestException("El NIT es obligatorio");
        }
        if(notIsString(restaurantDto.getUrlLogo())){
            throw new InvalidRequestException("La URL del logo es obligatoria");
        }
        if(restaurantDto.getOwnerId() == null  ){
            throw new InvalidRequestException("El dueño es obligatorio");
        }
    }

}
