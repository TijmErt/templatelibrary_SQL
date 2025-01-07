package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DgsQuery
    public List<User> getAllUsers() {
        try{
            return userService.findAll();
        }
        catch(Exception e){
            log.error("Error while fetching Users: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch Users: " + e.getMessage());
        }
    }


    @DgsQuery
    public User getUser(@InputArgument String id) {
        try{
            return userService.getById(id);
        }
        catch(Exception e){
            log.error("Error while fetching User: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch User: " + e.getMessage());
        }
    }
}
