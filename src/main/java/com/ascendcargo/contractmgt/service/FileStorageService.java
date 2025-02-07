package com.ascendcargo.contractmgt.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileStorageService {

    @Value("${file.storage.location}")
    private String storagePath;

    public String storeFile(MultipartFile file) throws IOException {
        Path uploadDir = Paths.get(storagePath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String filename = generateFilename(file.getOriginalFilename());
        Path filePath = uploadDir.resolve(filename);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        return filename;
    }

    private String generateFilename(String originalName) {
        return UUID.randomUUID() + "_" + cleanFilename(originalName);
    }

    private String cleanFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    public Resource loadFile(String filename) {
        try {
            Path filePath = Paths.get(storagePath).resolve(filename);
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("File loading error: " + filename, e);
        }
    }
}

