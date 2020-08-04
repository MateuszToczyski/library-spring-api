package com.library.controller.administration;

import com.library.domain.Borrow;
import com.library.domain.UserDto;
import com.library.exception.UserNotFoundException;
import com.library.mapping.UserMapper;
import com.library.service.UserDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("administration/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    private final UserDbService dbService;
    private final UserMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getUsers() {
        return mapper.mapToUserDtoList(dbService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public UserDto getUser(@PathVariable Long id) {
         return mapper.mapToUserDto(dbService.getUser(id).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return mapper.mapToUserDto(dbService.saveUser(mapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return mapper.mapToUserDto(dbService.saveUser(mapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteUser(@PathVariable Long id) {
        dbService.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{userId}")
    public List<Borrow> getBorrowHistory(@PathVariable Long userId) {
        return dbService.getBorrowHistory(userId);
    }
}
