package com.api.paymenttracke.services.user;

import com.api.paymenttracke.models.User;

interface UserServiceInterface {
    
    User getUserById(final Long id);

    User createUser(final User user);

    User updateUser(final Long id, final User user);

    User partialUpdateUser(final Long id, final User user);

    boolean deleteUser(final Long id);
}
