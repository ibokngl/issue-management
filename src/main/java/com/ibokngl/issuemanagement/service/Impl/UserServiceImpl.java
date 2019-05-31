package com.ibokngl.issuemanagement.service.Impl;

import com.ibokngl.issuemanagement.entity.User;
import com.ibokngl.issuemanagement.repository.UserRepository;
import com.ibokngl.issuemanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        if (user.getEmail()==null){
            throw new IllegalArgumentException("Username cannot be null");
        }
        return userRepository.save(user);
    }

    @Override
    public User getById(Long Id) {
        return userRepository.getOne(Id);
    }

    @Override
    public Page<User> getAllPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
