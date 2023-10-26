package com.sap.ose.projetose.services;

import com.sap.ose.projetose.dtos.UserDto;
import com.sap.ose.projetose.models.User;
import com.sap.ose.projetose.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(User::toDto).toList();
    }

    public User getUserById(long uploaderId) {
        return userRepository.getReferenceById(uploaderId);
    }
}
