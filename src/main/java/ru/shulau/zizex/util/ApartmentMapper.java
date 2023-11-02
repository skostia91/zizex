package ru.shulau.zizex.util;

import ru.shulau.zizex.dto.ApartmentDto;
import ru.shulau.zizex.dto.ApartmentResponse;
import ru.shulau.zizex.model.Apartment;
import ru.shulau.zizex.model.User;

public class ApartmentMapper {

    public static ApartmentResponse getResponse(Apartment apartment, User user) {
        return ApartmentResponse.builder()
                .address(apartment.getAddress())
                .ownerName(user.getName())
                .build();
    }

    public static ApartmentDto getTo(Apartment apartment) {
        return ApartmentDto.builder()
                .address(apartment.getAddress())
                .build();
    }
}