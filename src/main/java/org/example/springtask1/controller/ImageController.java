package org.example.springtask1.controller;

import org.example.springtask1.service.ImageService;
import org.example.springtask1.service.additional.BookError;
import org.example.springtask1.service.additional.BookUploadResult;
import org.example.springtask1.service.additional.CoverDownloadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cover")
public class ImageController {

    private final ImageService service;

    @Autowired
    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping("/download")
    public ResponseEntity<String> downloadCovers() {

        CoverDownloadResult result = service.downloadCovers();

        return ResponseEntity.ok(result.toString());
    }
}