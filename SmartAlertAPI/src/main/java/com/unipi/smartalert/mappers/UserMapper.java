package com.unipi.smartalert.mappers;

import com.unipi.smartalert.dtos.UserDTO;
import com.unipi.smartalert.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO mapToUserCredentialsDTO(User user) {
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
