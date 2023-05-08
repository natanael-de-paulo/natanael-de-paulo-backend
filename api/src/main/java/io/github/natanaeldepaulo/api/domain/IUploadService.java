package io.github.natanaeldepaulo.api.domain;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
    String upload(MultipartFile file, String fileName);
}
