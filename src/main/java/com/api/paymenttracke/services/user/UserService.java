package com.api.paymenttracke.services.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.api.paymenttracke.dto.user.UserResponseDTO;
import com.api.paymenttracke.exception.ResourceNotFoundException;
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

    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::mapUserToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, id));
    }

    @Override
    public UserResponseDTO createUser(User user) {
        final User createdUser = userRepository.save(user);
        return mapUserToDTO(createdUser);
    }

    public UserResponseDTO updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, id));

        existingUser.setId(id);
        User updatedUser = userRepository.save(user);

        return mapUserToDTO(updatedUser);
    }

    @Override
    public UserResponseDTO partialUpdateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, id));

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

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(User.class, id);
        }
        userRepository.deleteById(id);
    }

    private UserResponseDTO mapUserToDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
