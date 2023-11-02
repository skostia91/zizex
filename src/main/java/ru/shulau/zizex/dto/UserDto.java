package ru.shulau.zizex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.shulau.zizex.model.AbstractBaseEntity;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AbstractBaseEntity {

    private Integer age;
    private String password;
}