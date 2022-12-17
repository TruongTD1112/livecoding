package com.example.redisdemo2.controller;

import com.example.redisdemo2.entity.User;
import com.example.redisdemo2.payload.rp.CreateUserRp;
import com.example.redisdemo2.payload.rp.LoginRp;
import com.example.redisdemo2.payload.rq.CreateUserRq;
import com.example.redisdemo2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public CreateUserRp signup(@RequestBody CreateUserRq rq) {
        return userService.signUp(rq);
    }

    @PostMapping("/login")
    public LoginRp login(@RequestBody CreateUserRq rq) {
        return userService.login(rq);
    }

    @PostMapping("/users")
    public List<CreateUserRp> getUsers(@RequestBody CreateUserRq rq) {
        return userService.listUser(rq);
    }

    @GetMapping("/users/edit")
    public CreateUserRp editUser(CreateUserRp rq) {
        return userService.editUser(rq);
    }
}
