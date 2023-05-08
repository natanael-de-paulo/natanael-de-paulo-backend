package io.github.natanaeldepaulo.api.infrastructure.providers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class AwsProviderImpl implements IAwsProvider {
    @Autowired
    private AmazonS3 _amazonS3;

    @Override
    public String upload(MultipartFile multipartFile, String fileName) throws Exception{
        var imagePath = "";
        try {
            var convertedFile = convertMultiPartToFile(multipartFile);
            _amazonS3.putObject(new PutObjectRequest("demo-bucket", fileName, convertedFile).withCannedAcl(CannedAccessControlList.PublicRead));
            imagePath = "http://s3.localhost.localstack.cloud:4566"+"/demo-bucket/" + fileName;
            convertedFile.delete();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return imagePath;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        var convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        var fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}

