package com.example.ToDoAppSpring;

import static com.example.ToDoAppSpring.security.PasswordEncrypt.decrypt;
import static com.example.ToDoAppSpring.security.PasswordEncrypt.encrypt;

import com.example.ToDoAppSpring.controller.ToDoController;
import com.example.ToDoAppSpring.controller.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoAppSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ToDoAppSpringApplication.class, args);
	}

	@Autowired
	private UserController userController;
	@Autowired
	private ToDoController toDoController;


	@Override
	public void run(String... args) throws Exception {

	}
}
