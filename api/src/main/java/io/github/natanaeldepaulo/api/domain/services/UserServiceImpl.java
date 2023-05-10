package io.github.natanaeldepaulo.api.domain.services;

import io.github.natanaeldepaulo.api.application.models.user.IUserService;
import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.application.models.user.UserRequest;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.application.models.infraInterfaces.IUploadService;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import io.github.natanaeldepaulo.api.infrastructure.repositories.IUserRepository;
import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.application.models.user.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.UUID;

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
    public String create(UserRequest request) {
        var query = _userRepository.findByEmail(request.getEmail());

        if(query != null ) throw new RuntimeException("Email already exists");

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
        var userLogged = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var imagePath = _uploadService.upload(file);
        userLogged.getProfile().setImageURL(imagePath);
        userLogged.getProfile().setImage(true);
        _userRepository.save(userLogged);
        return imagePath;
    }

    @Override
    public String followUser(String userId) {
        var userLogged = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var userLoggedEntity = _userRepository.findById(userLogged.getId());
        var handleUser = _userRepository.findById(ConvertFormatId.toUUID(userId));

        if (handleUser.isEmpty()) throw new RuntimeException("User not found");


        var listFollowersUserLoggedEntity = userLoggedEntity.get().getProfile().getFollowing();
        var userLoggedId = userLoggedEntity.get().getId();
        var handleUserId = handleUser.get().getId();

        if (!listFollowersUserLoggedEntity.contains(handleUserId)) {
            addFollower(handleUserId, userLoggedId, handleUser.get(), userLoggedEntity.get());
            return "You follow";
        }

        removeFollower(handleUserId, userLoggedId, handleUser.get(), userLoggedEntity.get());
        return "You unfollowed";


    }

    private void removeFollower(UUID handleUserId, UUID userLoggedId, User handleUserEntity, User userLoggedEntity) {
        handleUserEntity.getProfile().getFollowers().remove(userLoggedId);
        userLoggedEntity.getProfile().getFollowing().remove(handleUserId);
        _userRepository.saveAll(Arrays.asList(handleUserEntity, userLoggedEntity));
    }

    private void addFollower(UUID handleUserId, UUID userLoggedId, User handleUserEntity, User userLoggedEntity){
        handleUserEntity.getProfile().getFollowers().add(userLoggedId);
        userLoggedEntity.getProfile().getFollowing().add(handleUserId);
        _userRepository.saveAll(Arrays.asList(handleUserEntity, userLoggedEntity));
    }
}
