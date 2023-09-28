package com.pragma.powerup.infraestructure.out.jpa.adapter;

import static org.mockito.Mockito.*;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infraestructure.exception.OwnerInvalid;
import com.pragma.powerup.infraestructure.exception.OwnerNotFound;
import com.pragma.powerup.infraestructure.exception.RestaurantAlreadyExist;
import com.pragma.powerup.infraestructure.exception.RestaurantNotFoundException;
import com.pragma.powerup.infraestructure.feign.user.RoleServiceRequest;
import com.pragma.powerup.infraestructure.feign.user.dto.response.role.RoleByUserIdResponseDto;
import com.pragma.powerup.infraestructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infraestructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infraestructure.out.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class RestaurantJpaAdapterTest {

    private IRestaurantRepository restaurantRepository;
    private RoleServiceRequest roleServiceRequest;
    private RestaurantJpaAdapter restaurantJpaAdapter;

    private IRestaurantEntityMapper restaurantEntityMapper;

    @BeforeEach
    public void setUp() {
        restaurantRepository = mock(IRestaurantRepository.class);
        IRestaurantEntityMapper restaurantEntityMapper = mock(IRestaurantEntityMapper.class);
        roleServiceRequest = mock(RoleServiceRequest.class);
        restaurantJpaAdapter = new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper, roleServiceRequest);
    }

    @Test
    void saveRestaurant_WithValidData_ShouldSaveToRepository() {
        RestaurantModel restaurantModel = RestaurantModel.builder()
                .address("av 2 #1-2")
                .phone("3137623509")
                .nit("1234a")
                .name("delicias")
                .ownerId(2L)
                .urlLogo("logo.com")
                .build();
        RoleByUserIdResponseDto role = new RoleByUserIdResponseDto();
        role.setName("OWNER");

        when(roleServiceRequest.getRoleByUserId(restaurantModel.getOwnerId())).thenReturn(role);
        when(restaurantRepository.findByName(restaurantModel.getName())).thenReturn(Optional.empty());
        when(restaurantRepository.findByNit(restaurantModel.getNit())).thenReturn(Optional.empty());

        restaurantJpaAdapter.saveRestaurant(restaurantModel);
        verify(restaurantRepository, times(1)).save(any());
    }

    @Test
    void saveRestaurantWhitOwnerInvalid() {
        RestaurantModel restaurantModel = RestaurantModel.builder()
                .address("av 2 #1-2")
                .phone("3137623509")
                .nit("1234a")
                .name("delicias")
                .ownerId(1L)
                .urlLogo("logo.com")
                .build();

        when(roleServiceRequest.getRoleByUserId(restaurantModel.getOwnerId())).thenReturn(null);

        Assertions.assertThrows(OwnerInvalid.class, () -> restaurantJpaAdapter.saveRestaurant(restaurantModel));
    }

    @Test
    void saveRestaurant_WithOwnerNotFound_ShouldThrowOwnerNotFoundException() {
        RestaurantModel restaurantModel = RestaurantModel.builder()
                .address("av 2 #1-2")
                .phone("3137623509")
                .nit("1234a")
                .name("delicias")
                .ownerId(1L)
                .urlLogo("logo.com")
                .build();
        RoleByUserIdResponseDto role = RoleByUserIdResponseDto.builder()
                .name("CLIENT")
                .build();

        when(roleServiceRequest.getRoleByUserId(restaurantModel.getOwnerId())).thenReturn(role);
        Assertions.assertThrows(OwnerNotFound.class, () -> restaurantJpaAdapter.saveRestaurant(restaurantModel));
    }

    @Test
    void saveRestaurantWithDuplicateName() {
        RestaurantModel restaurantModel = RestaurantModel.builder()
                .address("av 2 #1-2")
                .phone("3137623509")
                .nit("1234a")
                .name("delicias")
                .ownerId(1L)
                .urlLogo("logo.com")
                .build();
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        when(roleServiceRequest.getRoleByUserId(anyLong())).thenReturn(
                RoleByUserIdResponseDto.builder()
                        .name("OWNER")
                        .build()
        );
        when(restaurantRepository.findByName(restaurantModel.getName())).thenReturn(Optional.of(restaurantEntity));

        Assertions.assertThrows(RestaurantAlreadyExist.class, () -> restaurantJpaAdapter.saveRestaurant(restaurantModel));
    }

    @Test
    void saveRestaurantWithDuplicateNit() {
        RestaurantModel restaurantModel = RestaurantModel.builder()
                .address("av 2 #1-2")
                .phone("3137623509")
                .nit("1234a")
                .name("delicias")
                .ownerId(2L)
                .urlLogo("logo.com")
                .build();
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        when(restaurantRepository.findByNit(restaurantModel.getNit())).thenReturn(Optional.of(restaurantEntity));
        when(roleServiceRequest.getRoleByUserId(anyLong())).thenReturn(
                RoleByUserIdResponseDto.builder()
                        .name("OWNER")
                        .build()
        );
        Assertions.assertThrows(RestaurantAlreadyExist.class, () -> restaurantJpaAdapter.saveRestaurant(restaurantModel));
    }

    @Test
    void testIsOwnerOfRestaurant() {

        Long restaurantId = 1L;
        Long userId = 2L;

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setOwnerId(userId);
        when(restaurantRepository.findById(restaurantId)).thenReturn(java.util.Optional.of(restaurantEntity));

        boolean isOwner = restaurantJpaAdapter.isOwnerOfRestaurant(restaurantId, userId);

        Assertions.assertTrue(isOwner);

        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void testIsOwnerOfRestaurant_RestaurantNotFound() {

        Long restaurantId = 1L;
        Long userId = 2L;

        when(restaurantRepository.findById(restaurantId)).thenReturn(java.util.Optional.empty());

        Assertions.assertThrows(RestaurantNotFoundException.class, () -> restaurantJpaAdapter.isOwnerOfRestaurant(restaurantId, userId));

        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

    @Test
    void testIsOwnerOfRestaurant_UserIsNotOwner() {

        Long restaurantId = 1L;
        Long userId = 2L;

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setOwnerId(3L);
        when(restaurantRepository.findById(restaurantId)).thenReturn(java.util.Optional.of(restaurantEntity));

        boolean isOwner = restaurantJpaAdapter.isOwnerOfRestaurant(restaurantId, userId);
        Assertions.assertFalse(isOwner);

        verify(restaurantRepository, times(1)).findById(restaurantId);
    }

}
