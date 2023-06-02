package br.java.social_network.domain.services.upload;

import br.java.social_network.application.models.infra_interfaces.IUploadService;
import br.java.social_network.domain.entities.User;
import br.java.social_network.application.models.infra_interfaces.IAwsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadService {
    @Autowired
    private IAwsProvider _awsProvider;

    @Override
    public String upload(MultipartFile file){
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var fileName = user.getId() + "." + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        var imagePath = "";

        try {
            imagePath = _awsProvider.upload(file, fileName);
        } catch (Exception e){

        }

        return imagePath;
    }
}
