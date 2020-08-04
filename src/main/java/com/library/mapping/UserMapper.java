package com.library.mapping;


import com.library.domain.User;
import com.library.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getJoinDate(),
                userDto.getPassword(), userDto.getEmail());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getJoinDate(),
                user.getPassword(), user.getEmail());

    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
