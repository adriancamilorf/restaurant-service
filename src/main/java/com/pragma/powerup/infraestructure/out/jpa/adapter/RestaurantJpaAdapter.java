package com.pragma.powerup.infraestructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infraestructure.exception.OwnerInvalid;
import com.pragma.powerup.infraestructure.exception.OwnerNotFound;
import com.pragma.powerup.infraestructure.exception.RestaurantAlreadyExist;
import com.pragma.powerup.infraestructure.feign.user.RoleServiceRequest;
import com.pragma.powerup.infraestructure.feign.user.dto.response.role.RoleByUserIdResponseDto;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final RoleServiceRequest roleServiceRequest;
    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        RoleByUserIdResponseDto role = roleServiceRequest.getRoleByUserId(restaurantModel.getOwnerId());
        if(role==null){
            throw new OwnerInvalid();
        }
        if(!role.getName().equals("OWNER")){
            throw new OwnerNotFound();
        }
        if(restaurantRepository.findByName(restaurantModel.getName()).isPresent()){
            throw new RestaurantAlreadyExist();
        }
        if(restaurantRepository.findByNit(restaurantModel.getNit()).isPresent()){
            throw new RestaurantAlreadyExist();
        }
        restaurantRepository.save(restaurantEntityMapper.toRestaurantEntity(restaurantModel));
    }
}
