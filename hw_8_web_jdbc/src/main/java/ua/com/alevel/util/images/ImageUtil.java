package ua.com.alevel.util.images;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public final class ImageUtil {

    private ImageUtil() { }

    public static String uploadAndGetImageUrl(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path path = Paths.get("image/" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            String imageUrl = path.toFile().getAbsolutePath();
            System.out.println("imageUrl = " + imageUrl);
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}