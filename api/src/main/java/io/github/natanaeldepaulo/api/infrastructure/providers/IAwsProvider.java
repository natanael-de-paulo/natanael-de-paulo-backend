package io.github.natanaeldepaulo.api.infrastructure.providers;

import org.springframework.web.multipart.MultipartFile;

public interface IAwsProvider {
    String upload(MultipartFile file, String fileName) throws Exception;
}
