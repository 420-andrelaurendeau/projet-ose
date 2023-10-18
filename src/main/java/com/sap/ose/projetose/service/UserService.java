package com.sap.ose.projetose.service;

import com.sap.ose.projetose.dto.UserDto;
import com.sap.ose.projetose.models.User;
import com.sap.ose.projetose.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(User::toUserDto).toList();
    }

    public User getUserById(long uploaderId) {
        return userRepository.getReferenceById(uploaderId);
    }
}
