package cinema.controllers;

import cinema.exceptions.AuthenticationException;
import cinema.model.dto.UserRequestRegistraitionDto;
import cinema.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserRequestRegistraitionDto userDto)
            throws AuthenticationException {
        authenticationService.register(userDto.getEmail(), userDto.getPassword());
    }
}
