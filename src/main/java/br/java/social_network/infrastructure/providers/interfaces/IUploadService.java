package br.java.social_network.infrastructure.providers.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String upload(MultipartFile file);
}
