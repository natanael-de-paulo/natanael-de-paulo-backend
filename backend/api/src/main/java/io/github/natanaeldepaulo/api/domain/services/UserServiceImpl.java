package io.github.natanaeldepaulo.api.domain.services;

import io.github.natanaeldepaulo.api.application.models.user.IUserService;
import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import io.github.natanaeldepaulo.api.infrastructure.repositories.IUserRepository;
import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.infrastructure.mappers.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository _userRepository;
    @Autowired
    private IUserMapper _userMapper;

    @Override
    public String create(UserDTO request) {
        var profile = Profile.create(request.getProfile());
        request.setProfile(profile);
        var user = User.create(request);
        _userRepository.insert(user);
        return user.getId().toString();
    }

    @Override
    public UserDTO findUserById(String userId) {
        var user = _userRepository.findById(ConvertFormatId.toUUID(userId));
        return _userMapper.toDTO(user.get());
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        var user = _userRepository.findByEmail(email);
        return _userMapper.toDTO(user);
    }
}
