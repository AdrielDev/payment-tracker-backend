package com.api.paymenttracke.services.user;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.paymenttracke.dto.user.UserResponseDTO;
import com.api.paymenttracke.models.User;
import com.api.paymenttracke.repositories.UserRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(final UserRepository userRepository,
            final ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> mapUserToDTO(user));
    }

    @Override
    public UserResponseDTO createUser(User user) {
        final User createdUser = userRepository.save(user);
        return mapUserToDTO(createdUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            final User updatedUser = userRepository.save(user);
            return mapUserToDTO(updatedUser);
        }
        return null;
    }

    @Override
    public UserResponseDTO partialUpdateUser(Long id, User user) {
        final Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            if (StringUtils.isNotBlank(user.getName())) {
                existingUser.setName(user.getName());
            }
            if (StringUtils.isNotBlank(user.getEmail())) {
                existingUser.setEmail(user.getEmail());
            }
            if (StringUtils.isNotBlank(user.getPassword())) {
                existingUser.setPassword(user.getPassword());
            }
            if (StringUtils.isNotBlank(user.getPhoneNumber())) {
                existingUser.setPhoneNumber(user.getPhoneNumber());
            }

            final User partiallyUpdatedUser = userRepository.save(existingUser);
            return mapUserToDTO(partiallyUpdatedUser);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UserResponseDTO mapUserToDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
