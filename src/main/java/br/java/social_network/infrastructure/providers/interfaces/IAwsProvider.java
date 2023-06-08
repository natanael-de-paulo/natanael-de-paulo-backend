package br.java.social_network.infrastructure.providers.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IAwsProvider {
    String upload(MultipartFile file, String fileName) throws Exception;
}
