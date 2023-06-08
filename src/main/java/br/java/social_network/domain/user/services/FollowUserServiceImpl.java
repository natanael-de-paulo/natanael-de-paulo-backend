package br.java.social_network.domain.user.services;

import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.user.User;
import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Service
@Qualifier("FollowUserServiceImpl")
public class FollowUserServiceImpl implements IUserService<String, String> {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public String execute(String userId) {
      try{
          var userLogged = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
          var userLoggedEntity = this.userRepository.findById(userLogged.getId());
          var handleUser = this.userRepository.findById(ConvertFormatId.toUUID(userId));

          if (Objects.isNull(handleUser)) throw new Exception("User not found");


          var listFollowersUserLoggedEntity = userLoggedEntity.get().getProfile().getFollowing();
          var userLoggedId = userLoggedEntity.get().getId();
          var handleUserId = handleUser.get().getId();

          if (!listFollowersUserLoggedEntity.contains(handleUserId)) {
              addFollower(handleUserId, userLoggedId, handleUser.get(), userLoggedEntity.get());
              return "You follow";
          }

          removeFollower(handleUserId, userLoggedId, handleUser.get(), userLoggedEntity.get());
          return "You unfollowed";
      } catch (Exception e) {
          throw new HandleNotFoundException(e.getMessage());
      }
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
