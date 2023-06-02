package br.java.social_network.domain.services.user;

import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.entities.User;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@Qualifier("FollowUserServiceImpl")
public class FollowUserServiceImpl implements IUserService<String, String> {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public String execute(String userId) {
        var userLogged = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var userLoggedEntity = this.userRepository.findById(userLogged.getId());
        var handleUser = this.userRepository.findById(ConvertFormatId.toUUID(userId));

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
        this.userRepository.saveAll(Arrays.asList(handleUserEntity, userLoggedEntity));
    }

    private void addFollower(UUID handleUserId, UUID userLoggedId, User handleUserEntity, User userLoggedEntity){
        handleUserEntity.getProfile().getFollowers().add(userLoggedId);
        userLoggedEntity.getProfile().getFollowing().add(handleUserId);
        this.userRepository.saveAll(Arrays.asList(handleUserEntity, userLoggedEntity));
    }
}
