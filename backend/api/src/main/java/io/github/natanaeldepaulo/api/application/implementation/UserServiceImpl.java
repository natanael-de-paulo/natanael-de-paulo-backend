package io.github.natanaeldepaulo.api.application.implementation;

import io.github.natanaeldepaulo.api.application.dto.UserRequest;
import io.github.natanaeldepaulo.api.application.IUserService;
import io.github.natanaeldepaulo.api.application.dto.UserResponse;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import io.github.natanaeldepaulo.api.domain.interfaces.IUserRepository;
import io.github.natanaeldepaulo.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public final class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository _userRepository;

    @Override
    public String create(UserRequest request) {
        var profile = new Profile(request.profile);
        var user = new User(request.name, request.email, request.password, profile);

        _userRepository.save(user);
        return user.getId().toString();
    }

    @Override
    public Optional<UserResponse> findUserById(String id) {
        UUID _id = UUID.fromString(id);
        var query = _userRepository.findById(_id);
        var user = query.map(u -> new UserResponse(
                query.get().getId(),
                query.get().getName(),
                query.get().getEmail(),
                query.get().getProfile()
        ));
        return user;
    }
}
