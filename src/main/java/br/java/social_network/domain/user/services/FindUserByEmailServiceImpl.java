//package br.java.social_network.domain.services.user;
//
//import br.java.social_network.application.user.services.IUserService;
//import br.java.social_network.domain.user.User;
//import br.java.social_network.infrastructure.repositories.IUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//@Service
//@Qualifier("FindUserByEmailServiceImpl")
//public class FindUserByEmailServiceImpl implements IUserService<String, User> {
//    @Autowired
//    private IUserRepository userRepository;
//    @Override
//    public User execute(String email) {
//        var output = this.userRepository.findByEmail(email);
//        return output;
//    }
//}

