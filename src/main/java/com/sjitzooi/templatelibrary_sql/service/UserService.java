package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class UserService {

    private static final String ERROR_MESSAGE_SERVICE_LAYER= "TemplatePostService:";
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User getById(String id) {
        try{
            return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" getByID: {}",e.getMessage());
            throw e;
        }
    }
}
