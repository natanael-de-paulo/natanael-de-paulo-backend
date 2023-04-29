package io.github.natanaeldepaulo.api.application.services;

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
    public String create(CreateUserRequest request) {
        var profile = new Profile(request.profile);
        var user = new User(request.name, request.email, request.password, profile);

        _userRepository.save(user);
        return user.getId().toString();
    }

//    @Override
//    public Optional<UserResponse> getUserById(String _id) {
//        Optional<UserAccount> query = _userRepository.findById(_id);
//        UserResponse user = new UserResponse(query.get().get_id(), query.get().getName(), query.get().getEmail());
//        return Optional.of(user);
//    }
}
