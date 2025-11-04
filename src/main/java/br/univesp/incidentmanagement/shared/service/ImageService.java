package br.univesp.incidentmanagement.shared.service;

import br.univesp.incidentmanagement.shared.exception.ImageStorageException;
import br.univesp.incidentmanagement.shared.exception.InvalidImageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageService {
    private final Path root;
    private final String baseUrl;

    public ImageService(
            @Value("${students.images.path}") String path,
            @Value("${app.base.url}") String baseUrl
    ) {
        this.root = Paths.get(path).toAbsolutePath().normalize();
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    }

    public String uploadImage(Long id, MultipartFile image, String oldImageUrl) {
        if(image.isEmpty() || !Objects.requireNonNull(image.getContentType()).startsWith("image/")) {
            throw new InvalidImageException("Arquivo inválido. Apenas imagens são permitidas!");
        }
        try {
            if(!Files.exists(root)) {
                Files.createDirectories(root);
            }
            deleteImage(oldImageUrl);
            String fileName = String.format("student_%d_%d.jpg", id, System.currentTimeMillis());
            Path filePath = root.resolve(fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return String.format("%s/uploads/students/%s", baseUrl, fileName);
        } catch(IOException e) {
            throw new ImageStorageException("Erro ao salvar a imagem!", e);
        }
    }

    private void deleteImage(String imageUrl) {
        if(imageUrl == null || imageUrl.isBlank()) {
            return;
        }
        try{
            int uploadsIndex = imageUrl.lastIndexOf("/uploads/students/");
            if(uploadsIndex != -1) {
                String filename = imageUrl.substring(uploadsIndex + "/uploads/students/".length());
                Path oldFilePath = root.resolve(filename);
                if(Files.exists(oldFilePath)) {
                    Files.delete(oldFilePath);
                }
            }
        } catch(IOException e) {
            throw new ImageStorageException("Erro ao excluir a imagem antiga!", e);
        }
    }
}