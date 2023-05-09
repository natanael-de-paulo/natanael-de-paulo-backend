package io.github.natanaeldepaulo.api.application.models.infraInterfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IAwsProvider {
    String upload(MultipartFile file, String fileName) throws Exception;
}
