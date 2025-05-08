package org.example.springtask1.service;

import net.coobird.thumbnailator.Thumbnails;
import org.example.springtask1.persistence.repository.*;
import org.example.springtask1.service.additional.BookCoverView;
import org.example.springtask1.service.additional.CoverDownloadResult;
import org.example.springtask1.service.additional.CoverError;
import org.example.springtask1.service.additional.CoverErrorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
//@AllArgsConstructor
public class ImageService {

    private final BookRepository bookRepository;

    @Autowired
    public ImageService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public CoverDownloadResult downloadCovers() {
        List<BookCoverView> coverImgList = bookRepository.findAllBy();

        CoverDownloadResult result = new CoverDownloadResult();
        int i = 0;

        for (BookCoverView bookCoverView : coverImgList) {

            if(i > 1000) {
                return result;
            }

            System.out.println(i++);

            String title = bookCoverView.getTitle();
            String url = bookCoverView.getCoverImg();
            Long id = bookCoverView.getId();

            try {
                if (!isImageUrlValid(url)) {
                    result.getCoverErrors().add(new CoverError(id, title, CoverErrorState.INVALID_URL));
                    continue;
                }

                String baseDir = "cover-images";
                String coverFolder = baseDir + "/covers";
                String thumbnailFolder = baseDir + "/thumbnails";
                Files.createDirectories(Paths.get(coverFolder));
                Files.createDirectories(Paths.get(thumbnailFolder));


                String fileName = slugify(title) + "-" + id + ".jpg";
                Path imagePath = Paths.get(coverFolder, fileName);
                Path thumbnailPath = Paths.get(thumbnailFolder, fileName + "-thumb.jpg");

                try {
                    downloadImage(url, imagePath);
                }
                catch (IOException e) {
                    result.getCoverErrors().add(new CoverError(id, title, CoverErrorState.DOWNLOAD_ERROR));
                    continue;
                }

                try {
                    createThumbnail(imagePath, thumbnailPath);
                }
                catch (IOException e) {
                    result.getCoverErrors().add(new CoverError(id, title, CoverErrorState.MAKE_THUMBNAIL_ERROR));
                    continue;
                }

            }
            catch (IOException e) {
                result.getCoverErrors().add(new CoverError(id, title, CoverErrorState.IO_ERROR));
            }
        }

        return result;
    }


    public void downloadImage(String imageUrl, Path savePath) throws IOException {
        URL url = new URL(imageUrl);
        try (InputStream in = url.openStream()) {
            Files.copy(in, savePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public void createThumbnail(Path originalImage, Path thumbnailPath) throws IOException {
        Thumbnails.of(originalImage.toFile())
                .size(150, 200)
                .toFile(thumbnailPath.toFile());
    }

    public boolean isImageUrlValid(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode >= 200 && responseCode < 400);
        }
        catch (IOException e) {
            return false;
        }
    }

    public String slugify(String input) {
        return input
                .toLowerCase()                                 // Convert to lowercase
                .replaceAll("[^a-z0-9\\s-]", "")               // Remove all non-alphanumeric chars except space and dash
                .replaceAll("\\s+", "-")                       // Replace spaces with hyphens
                .replaceAll("-+", "-")                         // Collapse multiple dashes
                .replaceAll("^-|-$", "");                      // Trim dashes at the start or end
    }


}
