package io.github.natanaeldepaulo.api.domain.services;

import io.github.natanaeldepaulo.api.application.models.user.IUserService;
import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.domain.IUploadService;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import io.github.natanaeldepaulo.api.infrastructure.repositories.IUserRepository;
import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.infrastructure.mappers.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public final class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository _userRepository;
    @Autowired
    private IUserMapper _userMapper;
    @Autowired
    private IUploadService _uploadService;
    @Autowired
    PasswordEncoder _passwordEncoder;

    @Override
    public String create(UserDTO request) {
        var profile = Profile.create(request.getProfile());
        request.setProfile(profile);

        var passHash = _passwordEncoder.encode(request.getPassword());
        request.setPassword(passHash);

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
    public User findUserByEmail(String email) {
        var user = _userRepository.findByEmail(email);
        return user;
    }

    @Override
    public String upload(MultipartFile file){
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var imagePath = _uploadService.upload(file);
        user.getProfile().setImageURL(imagePath);
        user.getProfile().setImage(true);
        _userRepository.save(user);
        return imagePath;
    }
}
