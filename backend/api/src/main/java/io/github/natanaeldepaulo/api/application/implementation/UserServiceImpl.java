package io.github.natanaeldepaulo.api.application.implementation;

import io.github.natanaeldepaulo.api.application.specification.UserRequest;
import io.github.natanaeldepaulo.api.application.IUserService;
import io.github.natanaeldepaulo.api.application.specification.UserResponse;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import io.github.natanaeldepaulo.api.domain.interfaces.IUserRepository;
import io.github.natanaeldepaulo.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository _userRepository;

    @Override
    public String create(UserRequest request) {
        var profile = Profile.create(request.profile);
        request.setProfile(profile);
        var user = User.create(request.name, request.email, request.password, request.profile);
        _userRepository.insert(user);
        return user.getId().toString();
    }


    @Override
    public UserResponse findUserById(String id) {
        var query = _userRepository.findById(ConvertFormatId.toUUID(id));
        return new UserResponse(query.get());
    }

    @Override
    public User findUserByEmail(String email) {
        var query = _userRepository.findByEmail(email);
        return query;
    }
}
