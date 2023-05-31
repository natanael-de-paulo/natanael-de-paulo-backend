package br.java.social_network.application.models.infraInterfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String upload(MultipartFile file);
}
