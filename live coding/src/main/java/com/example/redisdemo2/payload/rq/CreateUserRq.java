package com.example.redisdemo2.payload.rq;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRq {

    private Integer id;
    private String username;
    private String password;
    private String fullName;
    private Integer role;
}
