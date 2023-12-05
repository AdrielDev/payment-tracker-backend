package com.api.paymenttracke.services.user;

import com.api.paymenttracke.dto.user.UserResponseDTO;
import com.api.paymenttracke.models.User;

interface UserServiceInterface {
    
    UserResponseDTO getUserById(final Long id);

    UserResponseDTO createUser(final User user);

    UserResponseDTO updateUser(final Long id, final User user);

    UserResponseDTO partialUpdateUser(final Long id, final User user);

    void deleteUser(final Long id);
}
