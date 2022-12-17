package com.example.redisdemo2.service;

import com.example.redisdemo2.entity.User;
import com.example.redisdemo2.payload.rp.CreateUserRp;
import com.example.redisdemo2.payload.rp.LoginRp;
import com.example.redisdemo2.payload.rq.CreateUserRq;
import com.example.redisdemo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public CreateUserRp signUp(CreateUserRq rq) {
        Optional<User> byUsername = userRepo.findByUsername(rq.getUsername());
        if (byUsername.isPresent()) {
            CreateUserRp rp = new CreateUserRp();
            rp.setStatus("exists user");
            return rp;
        }
        User user = User.builder()
                .username(rq.getUsername())
                .password(rq.getPassword())
                .fullName(rq.getFullName())
                .role(rq.getRole())
                .createDate(new Date())
                .build();
        userRepo.save(user);
        CreateUserRp rp = new CreateUserRp();
        rp.setUsername(rq.getUsername());
        rp.setPassword(rq.getPassword());
        rp.setFullName(rq.getFullName());
        rp.setRole(rq.getRole());
        rp.setCreateDate(user.getCreateDate());
        rp.setStatus("success");
        return rp;
    }

    public LoginRp login(CreateUserRq rq) {
        LoginRp loginRp = new LoginRp();
        try {
            Optional<User> userOpt = userRepo.findByUsername(rq.getUsername());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                if (rq.getPassword().equals(user.getPassword())) {
                    loginRp.setStatus("Wrong password");
                    return loginRp;
                }
                loginRp.setStatus("success");
                return loginRp;
            } else {
                loginRp.setStatus("failed");
            }
        } catch (Exception ex) {
            loginRp.setStatus("failed");
        }
        return loginRp;
    }

    public List<CreateUserRp> listUser(CreateUserRq rq) {
        Optional<User> userOpt = userRepo.findByUsername(rq.getUsername());
        if (userOpt.isEmpty()) {
            return null;
        }
        User user = userOpt.get();
        if (!user.getPassword().equals(rq.getPassword())) {
            return null;
        }
        if (user.getRole() != 2) {
            return null;
        }
        return userRepo.findAll().stream().map(t -> {
            return CreateUserRp.builder()
                    .id(t.getId())
                    .username(t.getUsername())
                    .fullName(t.getFullName())
                    .createDate(t.getCreateDate())
                    .editedDate(t.getEditedDate())
                    .build();
        }).collect(Collectors.toList());
    }

    public CreateUserRp editUser(CreateUserRp rq) {
        CreateUserRp rp = new CreateUserRp();
        if (rq.getRole() != 2) {
            rp.setStatus("no permission");
        }
        Optional<User> userOpt = userRepo.findById(rq.getId());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(rq.getUsername());
            user.setPassword(rq.getPassword());
            user.setFullName(rq.getFullName());
            user.setEditedDate(new Date());
            userRepo.save(user);
            rp.setUsername(rq.getUsername());
            rp.setPassword(rq.getPassword());
            rp.setFullName(rq.getFullName());
            rp.setCreateDate(user.getCreateDate());
            rp.setEditedDate(user.getEditedDate());
            rp.setStatus("success");
        } else {
            rp.setStatus("no exist user");
        }
        return rp;
    }

    public CreateUserRp deleteUser(CreateUserRq rq) {
        CreateUserRp rp = new CreateUserRp();
        if (rq.getRole() != 2) {
            rp.setStatus("no permission");
        }
        userRepo.deleteById(rq.getId());
        rp.setStatus("success");
        return rp;
    }
}
