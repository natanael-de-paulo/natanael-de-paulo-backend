package br.java.social_network.domain.user.services;

import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.application.user.controllers.request.UserRequestDTO;
import br.java.social_network.domain.user.embedded.Profile;
import br.java.social_network.domain.user.User;
import br.java.social_network.infrastructure.exception.HandleEntityAlreadyExistsException;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@Qualifier("CreateUserServiceImpl")
public class CreateUserServiceImpl implements IUserService<UserRequestDTO, String> {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String execute(UserRequestDTO request) {
        try{
            var query = this.userRepository.findByEmail(request.email());

            if(!Objects.isNull(query)) throw new Exception("Email already exists");

            var profile = Profile.builder()
                    .name(request.profile().name())
                    .image(request.profile().image(), request.profile().imageURL());

            var passHash = this.passwordEncoder.encode(request.password());

            var user = User.builder()
                    .email(request.email())
                    .password(passHash)
                    .profile(profile);

            this.userRepository.insert(user);
            return user.getId().toString();
        } catch (Exception e){
            throw new HandleEntityAlreadyExistsException(e.getMessage());
        }
    }
}
