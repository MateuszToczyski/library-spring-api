package com.library.controller.security;

import com.library.security.JwtAuthenticationRequest;
import com.library.security.JwtTokenGenerator;
import com.library.security.JwtTokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AuthController {

    private final JwtTokenGenerator tokenGenerator;

    @RequestMapping(method = RequestMethod.GET, value = "token")
    public JwtTokenResponse getToken(@RequestBody JwtAuthenticationRequest request) {
        return tokenGenerator.generateToken(request);
    }
}
