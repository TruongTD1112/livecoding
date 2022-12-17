package com.example.redisdemo2.payload.rp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRp {

    private Integer id;
    private String username;

    @JsonIgnore
    private String password;

    private String fullName;
    private Integer role;   // 1-user, 2-admin
    private Date createDate;
    private Date editedDate;
    private String status;
}
