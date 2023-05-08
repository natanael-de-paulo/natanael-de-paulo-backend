package io.github.natanaeldepaulo.api.domain;

import io.github.natanaeldepaulo.api.infrastructure.providers.IAwsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements IUploadService {
    @Autowired
    private IAwsProvider _awsProvider;

    @Override
    public String upload(MultipartFile file, String fileName){
        var imagePath = "";

        try {
            imagePath = _awsProvider.upload(file, fileName);
        } catch (Exception e){

        }

        return imagePath;
    }
}
