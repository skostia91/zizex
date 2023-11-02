package ru.shulau.zizex.util;

import ru.shulau.zizex.dto.ProfileDto;
import ru.shulau.zizex.model.User;

import java.util.stream.Collectors;

public class ProfileMapper {

    public static ProfileDto getProfile(User user) {
        return ProfileDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .properties(user.getProperty()
                        .stream()
                        .map(ApartmentMapper::getTo)
                        .collect(Collectors.toList()))
                .residence(user.getResidence() == null ?
                        null :  ApartmentMapper.getTo(user.getResidence()))
                .build();
    }
}