package br.java.social_network.application.models.infra_interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String upload(MultipartFile file);
}
