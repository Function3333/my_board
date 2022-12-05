package project.myBoard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import project.myBoard.entity.Image;
import project.myBoard.entity.Post;
import project.myBoard.repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    @Value("${file.dir}")
    private static String storeDir;

    @Transactional
    public void saveImage(List<MultipartFile> multipartFiles, Post post){
        if(!multipartFiles.isEmpty()) {
            for(MultipartFile multipartFile : multipartFiles) {
                Image image = new Image().createImage(createStoreName(), multipartFile.getOriginalFilename(), post);
                imageRepository.save(image);

                try {
                    multipartFile.transferTo(new File(getFullPath(image.getStoreName())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<UrlResource> findPostImages(List<Image> postImages) {
        List<UrlResource> images = new ArrayList<>();

        for(Image image : postImages) {
            UrlResource urlResource = getImage(image.getStoreName());
            images.add(urlResource);
        }

        return images;
    }

    public UrlResource getImage(String storeFileName) {
        try {
            return new UrlResource("file:" + getFullPath(storeFileName));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createStoreName() {
        return UUID.randomUUID().toString();
    }

    public String getFullPath(String fileName) {
        return storeDir + fileName;
    }
}
