package com.example.ToDoAppSpring.controller;

import static com.example.ToDoAppSpring.security.PasswordEncrypt.encrypt;

import com.example.ToDoAppSpring.controller.dto.CheckUserRequest;
import com.example.ToDoAppSpring.controller.dto.CheckUserResponse;
import com.example.ToDoAppSpring.controller.dto.NewUserResponse;
import com.example.ToDoAppSpring.document.User;
import com.example.ToDoAppSpring.controller.dto.NewUserRequest;
import com.example.ToDoAppSpring.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/newuser")
    public ResponseEntity<NewUserResponse> newUser(@RequestBody NewUserRequest request) {
        var userDb = userRepository.findByUsername(request.username());

        if (userDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        User user = new User(request.username(), encrypt(request.password()),request.email());
        userRepository.save(user);
        NewUserResponse newUserResponse = new NewUserResponse("Cadastro efetuado com sucesso!");
        return ResponseEntity.ok(newUserResponse);
    }

    @PostMapping(value = "/checkuser")
    public ResponseEntity<CheckUserResponse> checkUser(@RequestBody CheckUserRequest request) {
        var userDb = userRepository.findByUsername(request.username());

        if (!userDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        User user = userDb.get();
        String storedPassword = user.getPassword();

        if (!encrypt(request.password()).equals(storedPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Senha incorreta");
        }

        CheckUserResponse checkUserResponse = new CheckUserResponse("Usuário encontrado!", user.getId());
        return ResponseEntity.ok(checkUserResponse);
    }
}

