package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getById(String id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }
}
