package com.example.example4sem2CRUD.service;

import com.example.example4sem2CRUD.model.User;
import com.example.example4sem2CRUD.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public void deleteById(int id){
        userRepository.deletebyId(id);
    }
    public User updateUser(User user){
        userRepository.upDate(user);
        return user;
    }
    public User findById(int id){
        return userRepository.findById(id);
    }

    //public void deleteById(int id)
}
