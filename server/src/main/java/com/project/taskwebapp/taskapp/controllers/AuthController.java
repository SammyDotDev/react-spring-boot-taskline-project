package com.project.taskwebapp.taskapp.controllers;

import com.project.taskwebapp.taskapp.dto.apiResponse.ApiResponseDto;
import com.project.taskwebapp.taskapp.dto.users.AuthSigninDto;
import com.project.taskwebapp.taskapp.dto.users.UserDto;
import com.project.taskwebapp.taskapp.models.User;
import com.project.taskwebapp.taskapp.exceptions.EmailException;
import com.project.taskwebapp.taskapp.exceptions.InvalidPasswordException;
import com.project.taskwebapp.taskapp.services.AuthService;
import com.project.taskwebapp.taskapp.utils.interfaces.functions.ToDto;
import com.project.taskwebapp.taskapp.utils.interfaces.functions.ToObject;
import jakarta.validation.Valid;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*"
)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final ToDto toDto;
    private final ToObject toObject;

    public AuthController(AuthService authService, ToDto toDto, ToObject toObject) {
        this.authService = authService;
        this.toDto = toDto;
        this.toObject = toObject;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto signup(@RequestBody User user){
        try{
            return new ApiResponseDto("Sign in successful", true, toDto.toAuthDto(authService.addNewUser(user)));
        }catch(ConstraintViolationException e){
            throw new EmailException("Email already exists");
        }
    }

    @PostMapping("/signin")
    public ApiResponseDto signin(@Valid @RequestBody AuthSigninDto authSigninDDto){
        User user = toObject.toUser(authSigninDDto);


        User userByEmail = authService.findByEmail(user.getEmail());
        UserDto userDto = new UserDto(userByEmail.getId(), userByEmail.getUsername(), userByEmail.getEmail(), userByEmail.getProjects());
        ApiResponseDto apiResponse =  new ApiResponseDto("Sign in successful", true, userDto);

        if(userByEmail.getPassword().equals(user.getPassword())){
            return apiResponse;
        }

        throw new InvalidPasswordException("Invalid password");
    }



}

