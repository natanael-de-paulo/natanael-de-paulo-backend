package br.java.social_network.domain.user.services;

import br.java.social_network.infrastructure.providers.interfaces.IUploadService;
import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.domain.user.User;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("UploadFileServiceImpl")
public class UploadFileServiceImpl implements IUserService<MultipartFile, String> {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUploadService uploadService;

    @Override
    public String execute(MultipartFile file){
        var userLogged = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var imagePath = this.uploadService.upload(file);
        userLogged.getProfile().setImageURL(imagePath);
        userLogged.getProfile().setImage(true);
        this.userRepository.save(userLogged);
        return imagePath;
    }
}
